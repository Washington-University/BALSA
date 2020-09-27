package balsa

import java.util.Date;

import balsa.security.BalsaUser

class TechMessage {
	String id
	BalsaUser createdBy
	String title
	String contents
	Date createdDate
	Date resolved
	String resolvedReason

    static constraints = {
		resolved nullable: true
		title nullable: true, blank: true
		resolvedReason nullable: true, blank: true
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		title type: "text"
		contents type: "text"
		resolvedReason type: "text"
	}
}
