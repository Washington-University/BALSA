package balsa.security

import balsa.Profile
import balsa.Study
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.beans.factory.annotation.Autowired

class BalsaUser {
//
//	@Autowired
//	SpringSecurityService springSecurityService

	String id
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	Profile profile
	
	static hasMany = [agreedTerms: Terms, grantedApprovals: Approval, ownedApprovals: Approval, soughtApprovals: Approval, studies: Study]
	static mappedBy = [studies: "owners", grantedApprovals: "approvals", ownedApprovals: "owners"]
	
	static constraints = {
		username blank: false, unique: true
		password nullable: true, blank: false, password: true
	}

	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		username type: "text", index: 'balsa_username_index'
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

//	def beforeInsert() {
//		encodePassword()
//	}
//
//	def beforeUpdate() {
//		if (isDirty('password')) {
//			encodePassword()
//		}
//	}
//
//	protected void encodePassword() {
//		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
//	}
//
//	static transients = ['springSecurityService']
}
