package balsa.authentication

import grails.transaction.Transactional
import grails.util.Holders

import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper

import balsa.Profile
import balsa.authorityControl.Institution
import balsa.security.Approval
import balsa.security.BalsaUser
import balsa.security.Role
import balsa.security.Terms
import balsa.security.UserRole

class BalsaUserDetailsContextMapper extends LdapUserDetailsMapper {
	def config = Holders.grailsApplication.config

	@Override
	@Transactional
	public UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
		// check for existing user object, build user and profile if not present
		// get authorities from local user object
		def localUser = BalsaUser.findByUsername(username)
		if (localUser == null) { // user successfully authenticated against AD but does not have a BALSA user object, so create one for them
			localUser = new BalsaUser(username: username, profile: new Profile(fullname: ctx.getStringAttribute("displayName"), emailAddress: ctx.getStringAttribute("mail")))
			def institution = Institution.findByNameOrNickname(ctx.getStringAttribute("department"))
			if (institution != null) {
				localUser.profile.addToInstitutions(institution)
			}
			localUser.save(flush: true)
			UserRole.create(localUser, Role.findByAuthority('ROLE_USER'), true) // give new user basic access by default
		}
		
		// HCP users may already have agreed to Open Access terms or been granted Restricted Access approval, or they may have done so since last login
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equalsIgnoreCase('ROLE_Phase2OpenUsers') || authority.getAuthority().equalsIgnoreCase('BalsaOpenAccess')) {
				def openAccessTerms = Terms.findByTitle(config.HCP.openAccess.title)
				if (!localUser.agreedTerms?.contains(openAccessTerms)) {
					localUser.addToAgreedTerms(openAccessTerms).save(flush: true)
				}
			}
			if (authority.getAuthority().equalsIgnoreCase('Restricted Data Use Terms')) {
				def restrictedAccessApproval = Approval.findByTitle(config.HCP.restrictedAccess.title)
				if (!localUser.grantedApprovals?.contains(restrictedAccessApproval)) {
					localUser.addToGrantedApprovals(restrictedAccessApproval).save(flush: true)
				}
			}
		}
		
		authorities.clear() // remove roles that actually refer to HCP Open Access and Restricted Access
		def roles = localUser.getAuthorities()
		for (Role role : roles) { // add roles that refer to roles in BALSA
			authorities.add(new SimpleGrantedAuthority(role.authority))
		}
		
		// create LdapUserDetails and then decorate with BalsaUserDetails
		new BalsaUserDetails(localUser.id, localUser.enabled, super.mapUserFromContext(ctx, username, authorities))
	}

	@Override // maps minimal information to create new user objects in LDAP
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		ctx.setAttributeValues("objectClass", ["top", "person", "organizationalPerson", "user"].toArray())
		
		ctx.setAttributeValue("cn", user.getUsername())
		ctx.setAttributeValue("uid", user.getUsername())
		ctx.setAttributeValue("sAMAccountName", user.getUsername())
		ctx.setAttributeValue("name", user.getUsername())
		
		String newQuotedPassword = "\"" + user.getPassword() + "\""
		byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE")
		ctx.setAttributeValue("unicodePwd", newUnicodePassword)
		ctx.setAttributeValue("userAccountControl", "66048")
	}
}