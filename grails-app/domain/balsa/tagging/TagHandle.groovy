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
	
	TagHandle(parts) {
		handle = parts.substring(0, parts.indexOf(':'))
		value = parts.indexOf(':') == parts.length() - 1 ? null : parts.substring(parts.indexOf(':') + 1)
	}
}
