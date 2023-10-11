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
	}

	def readXML(InputStream input) {
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

			if (r.isStartElement() && r.getLocalName() == 'NamedMap') {
				def mapName
				def labelTable = []
				while (r.hasNext()) {
					r.next()
					if (r.isStartElement() && r.getLocalName() == 'MapName') {
						r.next()
						mapName = r.hasText() ? r.getText() : 'Labels'
					}
					if (r.isStartElement() && r.getLocalName() == 'LabelTable') {
						while (r.hasNext()) {
							r.next()
							if (r.isStartElement() && r.getLocalName() == 'Label') {
								def key = r.getAttributeValue('','Key')
								def red = Math.round(r.getAttributeValue('','Red').toFloat() * 255)
								def green = Math.round(r.getAttributeValue('','Green').toFloat() * 255)
								def blue = Math.round(r.getAttributeValue('','Blue').toFloat() * 255)
								def alpha = Math.round(r.getAttributeValue('','Alpha').toFloat() * 255)
								r.next()
								if (r.hasText()) labelTable.add([index:key, red:red, green:green, blue:blue, alpha:alpha, label:r.getText()])
							}
							if (r.isEndElement() && r.getLocalName() == 'LabelTable') {
								break
							}
						}
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
	
	def scanForTags() { // should be able to call super.scanForTags, but weird error happens where object considers itself to be its own super object; no idea why or how to fix
		def returnList = [] as Set
		returnList.addAll(TagScanner.scan(filepath))
//		for (namedMap in namedMaps) {
//			returnList.addAll(TagScanner.scan(namedMap.key))
//			for (label in namedMap.value) {
//				returnList.addAll(TagScanner.scan(label))
//			}
//		}
		returnList.toArray()
	}
}
