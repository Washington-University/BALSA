package balsa

import org.postgresql.largeobject.LargeObject

class FileData {
	String id
	String hash
	long data

    static constraints = {
		data nullable: true
    }
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		hash type: "text"
	}
}
