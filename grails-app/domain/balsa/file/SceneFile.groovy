package balsa.file

import java.nio.file.Path
import java.nio.file.Paths

import javax.xml.bind.DatatypeConverter
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader

import net.kaleidos.hibernate.usertype.ArrayType
import balsa.BalsaIdGenerator
import balsa.TagScanner
import balsa.Version
import balsa.exceptions.BalsaException
import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.scene.ScenePreview

class SceneFile extends FileMetadata {
	def fileService
	static hasMany = [scenes: Scene]
	String[] specFiles = []
	String[] supportingFiles = []
	String label
	
    static constraints = {
		label nullable: true, blank: true
    }
	static mapping = {
		specFiles type:ArrayType, params: [type: String]
		supportingFiles type:ArrayType, params: [type: String]
	}
	
	def setValuesFromFile(InputStream inputStream) {
		try {
			this.save(flush: true, failOnError: true) // without this early save, using addToScenes breaks domain objects for some stupid reason
			def allSpecFilesList = [] as Set
			def allSupportingFilesList = [] as Set
			
			XMLInputFactory f = XMLInputFactory.newInstance();
			XMLStreamReader r = f.createXMLStreamReader(inputStream)
			while (r.hasNext()) {
				r.next()
				if (r.isStartElement() && r.getLocalName() == "SceneInfo") {
					Scene scene = new Scene()
					def sceneLineId
					
					scene.index = r.getAttributeValue('','Index').toInteger()
					while (r.hasNext()) {
						r.next()
						
						if (r.isStartElement() && r.getLocalName() == 'BalsaSceneID') {
							r.next()
							if (r.hasText()) sceneLineId = r.getText()
						}
						if (r.isStartElement() && r.getLocalName() == 'Name') {
							r.next()
							if (r.hasText()) scene.name = r.getText()
						}
						if (r.isStartElement() && r.getLocalName() == 'Description') {
							r.next()
							if (r.hasText()) scene.description = r.getText()
						}
						if (r.isStartElement() && r.getLocalName() == 'Image') {
							ScenePreview scenePreview = new ScenePreview()
							scenePreview.imageFormat = r.getAttributeValue('','Format')
							String imageText = ''
							while (r.hasNext()) {
								r.next()
								if (r.isEndElement()) break
								imageText += r.getText()
							}
							scenePreview.image = DatatypeConverter.parseBase64Binary(imageText.trim())
							scenePreview.scene = scene
							scene.preview = scenePreview
						}
						if (r.isEndElement() && r.getLocalName() == "SceneInfo") break
					}
					
					
					if (sceneLineId != null && sceneLineId != "") {
						SceneLine line = SceneLine.get(sceneLineId)
						if (line != null && line.dataset != this.dataset) {
							throw new BalsaException("Attempted modification of scene outside of dataset.")
						}
						if (line == null) {
							line = new SceneLine()
							line.id = sceneLineId
							this.dataset.addToSceneLines(line)
							line.save()
						}
						line.addToScenes(scene)
					}
					else {
						SceneLine line = new SceneLine()
						line.id = new BalsaIdGenerator().generate(null, null)
						this.dataset.addToSceneLines(line)
						line.save()
						line.addToScenes(scene)
					}
					
					scene.sceneFile = this
					this.addToScenes(scene)
				}
				
				if (r.isStartElement() && r.getLocalName() == "Scene") {
					def index = r.getAttributeValue('','Index').toInteger()
					Scene scene = this.scenes.find({it.index == index})
					def specFilesList = [] as Set
					def supportingFilesList = [] as Set
					
					while (r.hasNext()) {
						r.next()
						
						if (r.isStartElement() && r.getAttributeValue('', 'Name') == 'specFileName') {
							r.next()
							try {
								if (r.hasText()) specFilesList.add(resolve(filepath, r.getText()))
							}
							catch(BalsaException b) { /* known error with spec file paths; ignore */ }
						}
						if (r.isStartElement() && r.getAttributeValue('', 'Name') == 'specFileDataFile') {
							while (r.hasNext()) {
								r.next()
								if (r.isStartElement() && r.getAttributeValue('', 'Name') == 'fileName') {
									r.next()
									if (r.hasText()) supportingFilesList.add(resolve(filepath, r.getText()))
									break
								}
							}
						}
						if (r.isEndElement() && r.getLocalName() == "Scene") break
					}
					
					allSpecFilesList.addAll(specFilesList)
					allSupportingFilesList.addAll(supportingFilesList)
					
					scene.specFiles = specFilesList.toArray()
					scene.supportingFiles = supportingFilesList.toArray()
				}
			}
			
			specFiles = allSpecFilesList.unique().toArray()
			supportingFiles = allSupportingFilesList.unique().toArray()
		} catch (BalsaException e) {
			throw e
		}
	}
	
	def dependencies(versionInfo = null) {
		def files = getVersion(versionInfo)?.files
		files?.findAll({specFiles.contains(it.filepath) || supportingFiles.contains(it.filepath)})
	}
	
	Scene focusScene(versionInfo = null) {
		def focusSceneLine = getVersion(versionInfo)?.focusScene
		return scenes.find { it.sceneLine == focusSceneLine }
	}
	
	def resolve(String base, String relative) {
		if (!relative) throw new BalsaException("Blank file path")
		if (relative.startsWith("http")) return relative
		Path basePath = Paths.get(base)
		String path = basePath.resolveSibling(relative).normalize().toString().replace('\\','/')
		if (path.startsWith("../")) throw new BalsaException("Scene file references files outside of dataset base directory: " + path)
		return path
	}
	
	def scenesSorted() {
		scenes.sort {a,b -> a.index <=> b.index}
	}
	
	def scanForTags() {
		def returnList = [] as Set
		returnList.addAll(TagScanner.scan(filepath))
		for (scene in scenes) {
			returnList.addAll(TagScanner.scan(scene.name))
			returnList.addAll(TagScanner.scan(scene.description))
		}
		returnList.toArray()
	}
	
	def tagsInAllScenes() {
		def tags
		for (scene in scenes) {
			if(tags) {
				tags = tags.intersect(Arrays.asList(scene.tags))
			}
			else {
				tags = Arrays.asList(scene.tags)
			}
		}
		tags
	}
	
//	intended to allow scene files that use balsa file ids for their files
//	awaits integration with workbench stage
//	public InputStream substituteLinks(InputStream original, Set<FileMetadata> filesToSubstitute) {
//		String fileText = IOUtils.copyToString(original)
//		
//		def open = "<![CDATA["
//		def close = "]]>"
//		
//		for (FileMetadata file : filesToSubstitute) {
//			def filePath = open + file.filepath + close
//			def fileName = open + file.filename + close
//			def link = grailsLinkGenerator.link(controller: 'file', action: 'download', id: file.id, absolute: true)
//			
//		}
//	}
}
