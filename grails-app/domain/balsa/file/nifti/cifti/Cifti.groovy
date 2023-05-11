package balsa.file.nifti.cifti

import net.kaleidos.hibernate.usertype.JsonbMapType
import balsa.TagScanner
import balsa.file.nifti.Nifti
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader

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
		
		advanceToBracket(input)
		
		XMLInputFactory f = XMLInputFactory.newInstance()
		XMLStreamReader r = f.createXMLStreamReader(input)
		
		while (r.hasNext()) {
			r.next()
			if (r.isStartElement() && r.getLocalName() == 'CIFTI') {
				ciftiVersion = r.getAttributeValue('','Version')
			}
			if (r.isEndElement() && r.getLocalName() == 'CIFTI') {
				break
			}
			
			if (r.isStartElement() && r.getLocalName() == 'MD') {
				def name
				def value
				while (r.hasNext()) {
					r.next()
					if (r.isStartElement() && r.getLocalName() == 'Name') {
						r.next()
						if (r.hasText()) name = r.getText()
					}
					if (r.isStartElement() && r.getLocalName() == 'Value') {
						r.next()
						if (r.hasText()) value = r.getText()
					}
					if (r.isEndElement() && r.getLocalName() == 'MD') {
						break
					}
				}
				if (name && value) {
					switch(name) {
						case 'Provenance':
							provenance = value
							break
						case 'ParentProvenance':
							parentProvenance = value
							break
						case 'ProgramProvenance':
							programProvenance = value
					}
				}
			}
			
			if (r.isStartElement() && r.getLocalName() == 'MapName') {
				r.next()
				def mapName = r.hasText() ? r.getText() : null
				def labelTable = []
				while (r.hasNext()) {
					r.next()
					if (r.isStartElement() && r.getLocalName() == 'Label') {
						r.next()
						if (r.hasText()) labelTable.add(r.getText())
					}
					if (r.isEndElement() && r.getLocalName() == 'LabelTable') {
						break
					}
					if (r.isEndElement() && r.getLocalName() == 'NamedMap') {
						break
					}
				}
				if (mapName && labelTable) {
					if (!namedMaps) namedMaps = [:]
					namedMaps.put(mapName, labelTable)
				}
			}
		}
	}
	
	String extractCiftiXml(InputStream input) {
		String concat = ""
		while (!(concat.contains('<CIFTI') && concat.contains('</CIFTI>')) && concat.length() < 52428800) { // capping string length at 50 MB in case of wrong file type
			byte[] b = new byte[20000]
			input.read(b)
			concat += new String(b)
		}
		if (concat.contains('<CIFTI') && concat.contains('</CIFTI>')) {
			
			int beginIndex = concat.indexOf('<CIFTI')
			int endIndex = concat.indexOf('</CIFTI>', beginIndex) + 8
			String xml = concat.substring(beginIndex, endIndex)

			xml.replaceAll("[\\000]+", "") // if the chunks read by the input stream don't align properly with the chunks of the stored data, extra null 0x0 characters will be inserted and need to be removed
			return xml
		}
	}
	
	def advanceToBracket(input) {
		while (true) {
			input.mark(2)
			def nextByte = input.read()
			if (nextByte == 60) {
				input.reset()
				break
			}
		}
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
