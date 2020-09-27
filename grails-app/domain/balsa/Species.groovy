package balsa

import net.kaleidos.hibernate.usertype.ArrayType

class Species {
	String id
	String name
	String description
	String[] examples
	
    static constraints = {
		description nullable: true, blank: true
		examples nullable: true, blank: true
    }
	
	static mapping = {
		id type: "text", generator: 'assigned'
		name type: "text"
		description type: "text"
		examples type:ArrayType, params: [type: String]
	}
}
