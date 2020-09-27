package balsa.file

class FileError extends FileMetadata {
	String message
	String stacktrace
	
	static mapping = {
		message type: "text"
		stacktrace type: "text"
	}
}
