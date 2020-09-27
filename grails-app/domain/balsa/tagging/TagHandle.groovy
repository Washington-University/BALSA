package balsa.tagging

class TagHandle {
	String id
	String value
	String handle
	
	static belongsTo = [category: TagCategory]
	
    static constraints = {
		value nullable: true, blank: true
		handle unique: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
	}
}
