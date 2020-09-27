package balsa.security

class OpenAccessTerms extends Terms {
	
	transient balsaUserDetailsService
	
    static constraints = {
    }
	
	def agree(BalsaUser user) {
		super.agree(user)
		balsaUserDetailsService.addAuthority(user.username, "BalsaOpenAccess")
	}
}
