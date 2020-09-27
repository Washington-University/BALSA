package balsa

import net.kaleidos.hibernate.usertype.ArrayType
import balsa.authorityControl.Institution
import balsa.security.BalsaUser

class Profile {
	String id
	String fullname
	String[] citednames
	String emailAddress
	String phone
	boolean contactInfoPublic = false
	static hasMany = [institutions:Institution]
	static belongsTo = [user: BalsaUser]

    static constraints = {
		fullname size: 5..200, blank: true, nullable: true
		citednames blank: true, nullable: true
		emailAddress blank: true, nullable: true, email: true
		phone black: true, nullable: true, phoneNumber: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		fullname type: "text", index: 'profile_name_index'
		emailAddress type: "text", index: 'profile_email_index'
		citednames type:ArrayType, params: [type: String]
	}
}
