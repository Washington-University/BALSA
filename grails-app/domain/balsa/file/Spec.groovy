package balsa.file

import net.kaleidos.hibernate.usertype.ArrayType

class Spec extends FileMetadata {
	String[] files

    static constraints = {
    }
	static mapping = {
		files type:ArrayType, params: [type: String]
	}
	
	def setValuesFromFile(InputStream input) {
		def fileList = []
		def specFile = xmlSlurper().parse(input)
		for (df in specFile.DataFile) {
			String filepath = df.text().trim()
			fileList.add(filepath)
		}
		files = fileList.toArray()
	}
}
