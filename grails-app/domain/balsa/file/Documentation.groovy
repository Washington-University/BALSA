package balsa.file

class Documentation extends FileMetadata {
	String mime = 'text/plain'

    static constraints = {
		mime nullable: true
    }
	static mapping = {
		mime type: "text"
	}
	
	public Documentation(String mimetype) {
		this.mime = mimetype
	}
}
