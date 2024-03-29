package balsa.authorityControl

import net.kaleidos.hibernate.usertype.ArrayType
import balsa.Study
import balsa.Version

class Publication {
	String id
	String officialName
	String[] abbrNames = []
	Boolean approved = false
	
	static hasMany = [studies: Study, versions: Version]

    static constraints = {
		approved nullable: true
		officialName unique: true
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		officialName type: "text"
		abbrNames type:ArrayType, params: [type: String]
	}
}
