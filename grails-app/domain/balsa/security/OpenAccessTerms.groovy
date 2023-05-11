package balsa.security

class OpenAccessTerms extends Terms {
	
    static constraints = {
    }
	
	def agree(BalsaUser user) {
		super.agree(user)
	}
}
