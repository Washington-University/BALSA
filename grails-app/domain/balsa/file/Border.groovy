package balsa.file

import net.kaleidos.hibernate.usertype.ArrayType
import balsa.TagScanner

class Border extends FileMetadata {
	String uniqueID
	String caretVersion
	String[] labels = []
	
	static constraints = {
		uniqueID nullable: true, blank: true
		caretVersion nullable: true, blank: true
		labels nullable: true, blank: true
	}
	static mapping = {
		uniqueID type: "text"
		caretVersion type: "text"
		labels type:ArrayType, params: [type: String]
	}
	
	def setValuesFromFile(InputStream input) {
		def borderFile = xmlSlurper().parse(input)
		for (md in borderFile.MetaData.MD) {
			switch (md.Name.text()) {
				case "Caret-Version":
					this.caretVersion = md.Value.text()
					break
				case "UniqueID":
					this.uniqueID = md.Value.text()
					break
			}
		}
		def labelList = []
		for (label in borderFile.LabelTable.Label) {
			labelList.add(label.text())
		}
		labels = labelList.toArray()
	}
	
	def scanForTags() {
		def returnList = [] as Set
		returnList.addAll(TagScanner.scan(filepath))
		for (label in labels) {
			returnList.addAll(TagScanner.scan(label))
		}
		returnList.toArray()
	}
}
