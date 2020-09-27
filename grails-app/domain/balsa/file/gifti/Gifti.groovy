package balsa.file.gifti

import java.io.InputStream;

import balsa.file.FileMetadata;

class Gifti extends FileMetadata {
	String uniqueID
	String caretVersion
	String encoding

    static constraints = {
		uniqueID nullable:true, blank:true
		caretVersion nullable:true, blank:true
		encoding nullable:true, blank:true
    }
	static mapping = {
		uniqueID type: "text"
		caretVersion type: "text"
		encoding type: "text"
	}
	
	def setValuesFromFile(InputStream input) {
		def giftiFile = xmlSlurper().parse(input)
		for (md in giftiFile.MetaData.MD) {
			switch (md.Name.text()) {
				case "Caret-Version":
					this.caretVersion = md.Value.text()
					break
				case "UniqueID":
					this.uniqueID = md.Value.text()
					break				
				case "encoding":
					this.encoding = md.Value.text()
					break
			}
		}
	}
}
