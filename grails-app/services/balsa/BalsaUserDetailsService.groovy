package balsa

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.gorm.transactions.Transactional

import java.lang.reflect.Field
import java.util.Collection;

import javax.naming.NamingException
import javax.naming.directory.BasicAttribute
import javax.naming.directory.DirContext
import javax.naming.directory.ModificationItem

import org.springframework.ldap.core.ContextExecutor
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DistinguishedName
import org.springframework.ldap.core.LdapTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager

@Transactional
class BalsaUserDetailsService {
	def userDetailsService
	def ldapUsernameMapper
	def ldapUserDetailsMapper
	def userCache
	
	/*
	 * This horrible violation of coding principles brought to you by the Spring LDAP plugin for Grails
	 * not allowing extensibility and using private instead of protected member variables and methods.
	 * Unless I want to write my own LDAP service from the ground up, I have to pillage the existing 
	 * userDetailsService for its template connection to Active Directory.
	 */
	LdapTemplate getTemplate() {
		Field templateField = LdapUserDetailsManager.class.getDeclaredField("template")
		templateField.setAccessible(true)
		(LdapTemplate) templateField.get(userDetailsService)
	}

	/*
	 * The Spring LDAP library's fails to provide a changePassword method that works with Active Directory,
	 * so I had to create my own.
	 */
	def changePassword(String username, String newPassword) {
		DistinguishedName dn = ldapUsernameMapper.buildDn(username)
		String newQuotedPassword = "\"" + newPassword + "\""
		byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE")
		
		final ModificationItem[] passwordChange = new ModificationItem[1]
		passwordChange[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword))
		
		getTemplate().modifyAttributes(dn, passwordChange);
	}
	
	/* This ridiculous method is necessary because we have to check two different folders for existing users,
	 * and for some reason Spring Security's LDAP library assumes you'll only ever check in a single directory.
	 * To get around this, we use the one LdapUserDetailsManager method that accepts a DN instead of a username
	 * string. We check it for both DN patterns, with an object returned meaning that the user already exists,
	 * and a thrown error meaning the user was not found for that DN pattern.
	 */
	boolean userExists(String username) {
		def conf = SpringSecurityUtils.securityConfig
		for (dnPattern in conf.ldap.authenticator.dnPatterns) {
			def dn = new DistinguishedName(dnPattern.replace("{0}", username))
			try {
				DirContextAdapter userCtx = userDetailsService.loadUserAsContext(dn, username)
				List<GrantedAuthority> authorities = userDetailsService.getUserAuthorities(dn, username);
				return true
			}
			catch(UsernameNotFoundException e) { /* an exception means the user wasn't found, and we don't care */ }
		}
		return false
	}
	
	/* 
	 * Passthrough method so RegisterController doesn't have both userDetailsService and balsaUserDetailsService to keep track of
	 */
	def createUser(User user) {
		userDetailsService.createUser(user)
	}
	
	/*
	 * Another stupid method, this time courtesy of the Spring LDAP plugin for Grails using .toUrl() instead of .toString() on user dns being added to
	 * groups.
	 */
	def addAuthority(String username, String authority) {
		def conf = SpringSecurityUtils.securityConfig
		DistinguishedName userDn = ldapUsernameMapper.buildDn(username)
		DistinguishedName groupDn = new DistinguishedName(conf.ldap.authorities.groupSearchBase)
		groupDn.add('ou', 'Balsa') // The BalsaOpenAccess group (which is this method is used for) is in a sub-ou for some stupid reason.
        groupDn.add('cn', authority)
		
		// doesn't seem to work without this executeReadWrite wrapper
		getTemplate().executeReadWrite(new ContextExecutor() {
			public Object executeWithContext(DirContext ctx) throws NamingException {
				ModificationItem addGroup = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute(conf.ldap.rememberMe.detailsManager.groupMemberAttributeName, userDn.toString()));
				final ModificationItem[] authorityChange = new ModificationItem[1]
				authorityChange[0] = addGroup
				ctx.modifyAttributes(groupDn, authorityChange);
				return null;
			}
		});
	}
	
	def reauthenticate(final String username) {
		UserDetails userDetails = this.loadUserByUsername(username)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(), userDetails.getAuthorities()))
		userCache.removeUserFromCache(username)
	}
	
	/* Another method necessitated by Spring Security's LDAP library assuming you'll only ever want to use on DN pattern. */
	public UserDetails loadUserByUsername(String username) {
		def conf = SpringSecurityUtils.securityConfig
		for (dnPattern in conf.ldap.authenticator.dnPatterns) {
			def dn = new DistinguishedName(dnPattern.replace("{0}", username))
			try {
				DirContextAdapter userCtx = userDetailsService.loadUserAsContext(dn, username)
				List<GrantedAuthority> authorities = userDetailsService.getUserAuthorities(dn, username)
				return ldapUserDetailsMapper.mapUserFromContext(userCtx, username, authorities)
			}
			catch(UsernameNotFoundException e) { /* an exception means the user wasn't found, and we don't care */ }
		}
	}
	
	public void deleteUser(String username) {
		DistinguishedName dn = ldapUsernameMapper.buildDn(username);
		template.unbind(dn, true);
	}
}
