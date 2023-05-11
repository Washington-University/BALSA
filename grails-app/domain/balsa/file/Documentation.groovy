package balsa.file

class Documentation extends FileMetadata {
	String mime = 'text/plain'
	Boolean visible = true

    static constraints = {
		mime nullable: true
		visible nullable: true
    }
	static mapping = {
		mime type: "text"
	}
	
	public Documentation(String mimetype) {
		this.mime = mimetype
	}
}
