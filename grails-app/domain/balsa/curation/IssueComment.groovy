package balsa.curation

import java.util.Date;

class IssueComment {
	String id
	Issue issue
	String createdBy
	Date createdDate = new Date()
	String comment
	
	static belongsTo = [Issue]

    static constraints = {
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		createdBy type: "text"
		comment type: "text"
	}
}
