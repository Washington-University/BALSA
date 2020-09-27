package balsa.tagging

import net.kaleidos.hibernate.usertype.ArrayType

class TagCategory {
	String id
	String name
	String description
	SearchType searchType
	String[] options = []
	
	static hasMany = [handles: TagHandle]

    static constraints = {
		description nullable: true, blank: true
		searchType nullable: true, blank: true
		options nullable: true, blank: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		name type: "text"
		description type: "text"
		options type:ArrayType, params: [type: String]
	}
	
	enum SearchType {
		DROPDOWN { 
			def template() {"dropdown"}
			def or() {false}
		}, 
		FIELD { 
			def template() {"field"}
			def or() {false}
		},
		RADIO {
			 def template() {"radio"}
			def or() {false}
		}, 
		CHECKBOX { 
			def template() {"checkbox"}
			def or() {true}
		}
	}
}
