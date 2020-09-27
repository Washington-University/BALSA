package balsa

import org.apache.commons.lang.StringUtils

import balsa.security.BalsaUser

class News {
	String id
	String contents
	Date dateCreated
	BalsaUser createdBy

    static constraints = {
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		contents type: "text"
	}
	
	def firstLine() {
		StringUtils.substringBefore(contents, "\n")
	}
	
	def subsequentLines() {
		StringUtils.substringAfter(contents, "\n")
	}
}
