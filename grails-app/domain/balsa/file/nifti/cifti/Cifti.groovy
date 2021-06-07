package balsa.file.nifti.cifti

import net.kaleidos.hibernate.usertype.JsonbMapType
import balsa.TagScanner
import balsa.file.nifti.Nifti

class Cifti extends Nifti {
	String ciftiVersion
	String provenance
	String parentProvenance
	String programProvenance
	Map namedMaps

    static constraints = {
		ciftiVersion nullable: true, blank: true
		provenance nullable: true, blank: true
		parentProvenance nullable: true, blank: true
		programProvenance nullable: true, blank: true
		namedMaps nullable: true, blank: true
    }
	static mapping = {
		ciftiVersion type: "text"
		provenance type: "text"
		parentProvenance type: "text"
		programProvenance type: "text"
		namedMaps type: JsonbMapType
	}
	
	def setValuesFromFile(InputStream input) {
		super.setValuesFromFile(input)

		def ciftiFile = xmlSlurper().parseText(extractCiftiXml(input))
		
		ciftiVersion = ciftiFile.@Version
		
		for (md in ciftiFile.Matrix.MetaData?.MD) {
			if (md.Name.text() == 'Provenance') {
				provenance = md.Value.text()
			}
		}
		
		def maps = [:]
		for (mim in ciftiFile.Matrix.MatrixIndicesMap) {
			for (nm in mim.NamedMap) {
				def mapName = nm.MapName.text()
				def labelTable = []
				for (label in nm.LabelTable.Label) {
					labelTable.add(label.text())
				}
				maps.put(mapName, labelTable)
			}
		}
		namedMaps = maps
		
		return ciftiFile
	}
	
	String extractCiftiXml(InputStream input) {
		String concat = ""
		while (!(concat.contains('<CIFTI') && concat.contains('</CIFTI>')) && concat.length() < 52428800) { // capping string length at 10 MB in case of wrong file type
			byte[] b = new byte[20000]
			input.read(b)
			concat += new String(b)
		}
		if (!(concat.contains('<CIFTI') && concat.contains('</CIFTI>'))) {
			throw new Exception("Could not isolate CIFTI XML header.")
		}
		int beginIndex = concat.indexOf('<CIFTI')
		int endIndex = concat.indexOf('</CIFTI>', beginIndex) + 8
		String xml = concat.substring(beginIndex, endIndex)
		
		xml.replaceAll("[\\000]+", "") // if the chunks read by the input stream don't align properly with the chunks of the stored data, extra null 0x0 characters will be inserted and need to be removed
		xml
	}
	
	def scanForTags() { // should be able to call super.scanForTags, but weird error happens where object considers itself to be its own super object; no idea why or how to fix
		def returnList = [] as Set
		returnList.addAll(TagScanner.scan(filepath))
		for (namedMap in namedMaps) {
			returnList.addAll(TagScanner.scan(namedMap.key))
			for (label in namedMap.value) {
				returnList.addAll(TagScanner.scan(label))
			}
		}
		returnList.toArray()
	}
}
