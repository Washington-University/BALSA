package balsa.curation

import balsa.Dataset
import balsa.Version

class Issue {
	String id
	String title
	Dataset dataset
	String createdBy
	Date createdDate = new Date()
	Status status
	String versionId
	
	static belongsTo = [Dataset]
	static hasMany = [comments: IssueComment]

    static constraints = {
		versionId nullable: true, blank: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		comments sort: 'createdDate', order: 'desc'
		versionId type: "text"
	}
	
	enum Status {
		PROBLEM, SUGGESTION, FIXED, INTENTIONAL, RESOLVED
	}
}
