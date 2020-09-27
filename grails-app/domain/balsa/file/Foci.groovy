package balsa.file

import java.io.InputStream;

class Foci extends FileMetadata {
	String caretVersion

    static constraints = {
		caretVersion nullable: true, blank: true
    }
	static mapping = {
		caretVersion type: "text"
	}
	
	def setValuesFromFile(InputStream input) {
		def giftiFile = xmlSlurper().parse(input)
		for (md in giftiFile.MetaData.MD) {
			switch (md.Name.text()) {
				case "Caret-Version":
					this.caretVersion = md.Value.text()
					break
			}
		}
	}
}
