package balsa.security

class SubmissionTerms extends Terms {
	
	transient springSecurityService
	transient userService

    static constraints = {
    }
	
	def agree(BalsaUser user) {
		super.agree(user)
		UserRole.create(user, Role.findByAuthority('ROLE_SUBMITTER'), true)
		springSecurityService.reauthenticate(userService.current.username)
	}
}
