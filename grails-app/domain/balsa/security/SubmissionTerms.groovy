package balsa.security

class SubmissionTerms extends Terms {
	
	transient balsaUserDetailsService
	transient springSecurityService

    static constraints = {
    }
	
	def agree(BalsaUser user) {
		super.agree(user)
		UserRole.create(user, Role.findByAuthority('ROLE_SUBMITTER'), true)
		balsaUserDetailsService.reauthenticate(springSecurityService.currentUser.username)
	}
}
