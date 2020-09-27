package balsa.authentication

import java.util.Collection;

import grails.plugin.springsecurity.userdetails.GrailsUser

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.ldap.userdetails.LdapUserDetails

// decorator pattern
class BalsaUserDetails extends GrailsUser implements LdapUserDetails {
	final Object id
	boolean enabled
	private LdapUserDetails ldapUserDetails
	
	BalsaUserDetails(Object localId, boolean enabled, LdapUserDetails ldapUserDetails) {
		super(" ", "", false, false, false, false, [], null)
		this.id = localId
		this.enabled = enabled
		this.ldapUserDetails = ldapUserDetails
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ldapUserDetails.getAuthorities()
	}
	@Override
	public String getPassword() {
		ldapUserDetails.getPassword()
	}
	@Override
	public String getUsername() {
		ldapUserDetails.getUsername()
	}
	@Override
	public boolean isAccountNonExpired() {
		ldapUserDetails.isAccountNonExpired()
	}
	@Override
	public boolean isAccountNonLocked() {
		ldapUserDetails.isAccountNonLocked()
	}
	@Override
	public boolean isCredentialsNonExpired() {
		ldapUserDetails.isCredentialsNonExpired()
	}
	@Override
	public boolean isEnabled() {
		this.enabled
	}
	@Override
	public String getDn() {
		ldapUserDetails.getDn()
	}
}
