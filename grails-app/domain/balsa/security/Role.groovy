package balsa.security

class Role {

	String id
	String authority

	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		cache true
		authority type: "text"
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
