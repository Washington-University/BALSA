package balsa.authorityControl

import net.kaleidos.hibernate.usertype.ArrayType

class Institution {
	String id
	String canonicalName
	String[] names = []
	Boolean approved = false
	
    static constraints = {
		approved nullable: true
		canonicalName unique: true
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		canonicalName type: "text", index: 'inst_name_index'
		names type:ArrayType, params: [type: String]
	}
	
	static Institution findByNameOrNickname(String name) {
		if (name == null || name == '') return null
		def res = findWhere(canonicalName: name)
		if (res == null) {
			res = withCriteria(uniqueResult: true) {
				pgArrayILike ('names', '%'+name+'%')
			}
		}
		return res
	}
}
