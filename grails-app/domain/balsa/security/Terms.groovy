package balsa.security

class Terms {
	String id
	String title
	String contents
	
	static hasMany = [BalsaUser]
    static constraints = {
		title size: 5..200
		contents size: 10..100000
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		title type: "text"
		contents type: "text"
	}
	
	def agree(BalsaUser user) {
		user.addToAgreedTerms(this)
	}
}
