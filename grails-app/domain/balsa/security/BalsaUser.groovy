package balsa.security

import balsa.Profile
import balsa.Study

class BalsaUser {

	transient springSecurityService

	String id
	String username
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	Profile profile
	
	static hasMany = [agreedTerms: Terms, grantedApprovals: Approval, ownedApprovals: Approval, soughtApprovals: Approval, studies: Study]
	static mappedBy = [studies: "owners", grantedApprovals: "approvals", ownedApprovals: "owners"]
	
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
	}

	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		username type: "text", index: 'balsa_username_index'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}
}
