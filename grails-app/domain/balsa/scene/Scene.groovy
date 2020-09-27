package balsa.scene

import net.kaleidos.hibernate.usertype.ArrayType
import balsa.Download
import balsa.TagScanner
import balsa.file.FileMetadata
import balsa.file.SceneFile

class Scene {
	String id
	Number index
	String name
	String shortName
	String description
	String[] specFiles = []
	String[] supportingFiles = []
	String[] tags = []
	
	static belongsTo = [sceneFile: SceneFile, sceneLine: SceneLine]
	static hasMany = [downloads: Download]
	static hasOne = [preview: ScenePreview]
    static constraints = {
		shortName size: 5..100, blank: true, nullable: true
		description blank: true, nullable: true
	}
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		name type: "text", index: 'scene_name_index'
		shortName type: "text", index: 'scene_shortname_index'
		description type: "text", index: 'scene_desc_index'
		specFiles type:ArrayType, params: [type: String]
		supportingFiles type:ArrayType, params: [type: String]
		tags type:ArrayType, params: [type: String]
		preview cascade: 'all-delete-orphan'
		downloads joinTable: [name: 'scene_download', key: 'scene_downloads_id']
	}
	
	def dependencies(versionInfo = null) {
		def files = sceneFile.getVersion(versionInfo)?.files
		files?.findAll({supportingFiles.contains(it.filepath) || specFiles.contains(it.filepath)})
	}
	
	def missingSupportingFiles(versionInfo = null) {
		def missingFiles = []
		def files = sceneFile.getVersion(versionInfo)?.files*.filepath
		supportingFiles.each { if (!(it in files)) missingFiles.add(it) }
		return missingFiles
	}
	
	def hasSpeciesTag() {
		tags.find({ it.startsWith("Species") })
	}
	
	def aggregateTags(versionInfo = null) {
		def returnList = [] as Set
		for (file in this.dependencies(versionInfo)) {
			returnList.addAll(file.tags)
		}
		returnList.addAll(TagScanner.scan(this.name))
		returnList.addAll(TagScanner.scan(this.description))
		returnList.toArray()
	}
	
	boolean isPublic(versionInfo = null) {
		sceneFile.isPublic(versionInfo)
	}
	
	boolean canEdit() {
		sceneFile.canEdit()
	}
	
	boolean canView() {
		sceneFile.canView()
	}
	
	boolean hasAccess() {
		sceneFile.hasAccess()
	}
	
	def terms() {
		sceneFile.terms()
	}
	
	def datasetId() {
		sceneFile.datasetId()
	}
}
