package balsa

import net.kaleidos.hibernate.usertype.ArrayType

import grails.databinding.BindingFormat

import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.file.Documentation
import balsa.file.FileMetadata
import balsa.file.SceneFile
import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.security.BalsaUser

class Version {
	String id
	Dataset dataset
	Status status = Status.NONPUBLIC
	
	String description
	SceneLine focusScene
	String[] sceneFileOrder = []
	String comments
	String whatsNew // is this redundant with comments
	
	int versionNo = 1
	Date createdDate = new Date()
	Date updatedDate = new Date()
	Date publicDate = new Date()
	
	boolean preprint = false
	String publicationTitle
	String studyAbstract
	String doi
	String pmid
	String[] authors = []
	Publication publication
	
	Date submittedDate
	BalsaUser submitter
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date preprintDate
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date epubDate
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date journalDate
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date releaseDate
	DateRedirect dateRedirect = DateRedirect.CUSTOM
	
	static hasMany = [files: FileMetadata, institutions:Institution, linkedScenes: SceneLine]
	static belongsTo = [Dataset]
	
    static constraints = {
		description nullable: true, blank: true
		focusScene nullable: true
		sceneFileOrder nullable: true
		comments nullable: true, blank: true
		whatsNew nullable: true, blank: true
		updatedDate nullable: true, blank: true
		submittedDate nullable: true
		publicDate nullable: true
		submitter nullable: true
		
		pmid nullable: true, matches: "\\A[0-9]+\\z"
		doi nullable: true, matches: '\\A(10[.][0-9]{4,}(?:[.][0-9]+)*/(?:(?![%"\'&<>#? ])\\S)+)\\z'
		studyAbstract nullable: true, blank: true
		publicationTitle nullable: true, blank: true
		publication nullable: true
		preprintDate nullable: true
		epubDate nullable: true
		journalDate nullable: true
		releaseDate nullable: true
		dateRedirect nullable: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		dataset lazy: false
		status index: 'version_status_index'
		updatedDate index: 'version_updated_index'
		sceneFileOrder type:ArrayType, params: [type: String]
		comments type: "text"
		whatsNew type: "text"
		description type: "text"
		studyAbstract type: "text"
		doi index: 'version_doi_index'
		pmid index: 'version_pmid_index'
		authors type:ArrayType, params: [type: String]
	}
	
	enum DateRedirect {
		PREPRINT ("Preprint Date"),
		EPUB ("Epub Date"),
		JOURNAL ("Journal Date"),
		CUSTOM ("Custom Date")
		
		final String value
		
		DateRedirect(String value) { this.value = value }
		
		String toString() { value }
		String getKey() { name() }
	}
	
	Date actualReleaseDate() {
		if (dateRedirect == DateRedirect.PREPRINT) {
			return preprintDate
		}
		else if (dateRedirect == DateRedirect.EPUB) {
			return epubDate
		}
		else if (dateRedirect == DateRedirect.JOURNAL) {
			return journalDate
		}
		else if (dateRedirect == DateRedirect.CUSTOM) {
			return releaseDate
		}
		else {
			return null
		}
	}
	
	enum Status {
		NONPUBLIC, SUBMITTED, APPROVED, PUBLIC
	}
	
	Version() {}

	Version(Version priorVersion) {
		this.dataset = priorVersion.dataset
		this.description = priorVersion.description
		this.focusScene = priorVersion.focusScene
		this.sceneFileOrder = priorVersion.sceneFileOrder
		this.versionNo = priorVersion.dataset.maxVersionNo()+1
		
		priorVersion.files.each { this.addToFiles(it) }
		priorVersion.linkedScenes.each { this.addToLinkedScenes(it) }
		
		this.publicationTitle = priorVersion.publicationTitle
		this.studyAbstract = priorVersion.studyAbstract
		this.doi = priorVersion.doi
		this.pmid = priorVersion.pmid
		this.authors = priorVersion.authors
		this.publication = priorVersion.publication
		
		this.preprintDate = priorVersion.preprintDate
		this.epubDate = priorVersion.epubDate
		this.journalDate = priorVersion.journalDate
		this.releaseDate = priorVersion.releaseDate
		this.dateRedirect = priorVersion.dateRedirect
		
		priorVersion.institutions.each { this.addToInstitutions(it) }
	}
	
	def isReference() {
		dataset instanceof Reference
	}
	
	def isNonpublic() {
		return status == Status.NONPUBLIC
	}
	
	def isSubmitted() {
		return status == Status.SUBMITTED
	}
	
	def isApproved() {
		return status == Status.APPROVED
	}
	
	def isPublic(parameter = null) {
		return status == Status.PUBLIC
	}
	
	def canEdit() {
		dataset.canEdit()
	}
	
	def canView() {
		dataset.canView()
	}
	
	def hasAccess() {
		dataset.hasAccess()
	}
	
	boolean isReadyForSubmission() {
		dataset.isReadyForSubmission()
	}
	
	boolean isReadyForApproval() {
		dataset.isReadyForApproval()
	}
	
	boolean isDefaultVersion() {
		this == dataset.defaultVersion()
	}
	
	Set<FileMetadata> documentation() {
		files.findAll({ it instanceof Documentation }).toSet()
	}

	Set<FileMetadata>  visibleDocumentation() {
		files.findAll({ it instanceof Documentation && it.visible }).toSet()
	}

	Set<SceneFile> sceneFiles() {
		def returnList = SceneFile.executeQuery("select s from SceneFile s join s.versions v where v.id = '" + this.id + "'");
		
		def sceneFileOrderList = sceneFileOrder ? Arrays.asList(sceneFileOrder) : []
		returnList.sort { a,b ->
			sceneFileOrderList?.indexOf(a.filename) <=> sceneFileOrderList?.indexOf(b.filename) ?: a.filename <=> b.filename
		}
		
		returnList
	}

	Set<Scene> scenes() {
		def returnList = [] as Set
		for (sceneFile in sceneFiles()) {
			returnList.addAll(sceneFile.scenesSorted())
		}
		returnList
	}
	
	Scene focusScene() {
		scenes().find { it.sceneLine == focusScene }
	}

	Set<Scene> linkedScenes() {
		def returnList = [] as Set
		for (linkedScene in linkedScenes) {
			returnList.add(linkedScene.sceneForVersion(null))
		}
		returnList.sort { a,b -> a.sceneFile.dataset.createdDate <=> b.sceneFile.dataset.createdDate ?: a.sceneFile.filename <=> b.sceneFile.filename ?: a.index <=> b.index }
	}
	
	Set<Dataset> linkedDatasets() {
		def returnList = [] as Set
		for (linkedScene in linkedScenes) {
			returnList.add(linkedScene.dataset)
		}
		returnList.sort { a,b -> a.createdDate <=> b.createdDate }
	}

	Set<Scene> linkedScenesByDataset(Dataset dataset) {
		def returnList = [] as Set
		for (linkedScene in linkedScenes) {
			if (linkedScene.dataset == dataset) returnList.add(linkedScene.sceneForVersion(null))
		}
		returnList.sort { a,b -> a.sceneFile.filename <=> b.sceneFile.filename ?: a.index <=> b.index }
	}
	
	def sceneForVersion(SceneLine sceneLine) {
		for (scene in sceneLine?.scenes) {
			if (files.contains(scene.sceneFile)) {
				return scene
			}
		}
	}
	
	def safeFocusScene() {
		sceneForVersion(focusScene) ?: scenes()[0]
	}
	
	boolean isDependentFile(FileMetadata file) {
		def allDependentFiles = []
		this.scenes().each {
			allDependentFiles += (it.supportingFiles as List) + (it.specFiles as List)
		}
		allDependentFiles.contains(file.filepath)
	}
	
	def addOrReplaceFile(FileMetadata file) {
		def filesToRemove = files.findAll { it.filepath == file.filepath && it.id != file.id }
		for (fileToRemove in filesToRemove) {
			this.removeFromFiles(fileToRemove)
		}
		if (!files.find { it.id == file.id }) {
			this.addToFiles(file)
		}
	}
	
	def extractDirectory(boolean includeSuffix) {
		dataset.extract + (includeSuffix ? "_" + id[0..1] + "_" + dataset.id : "")
	}
	
	def focusSceneIndex() {
		def focusSceneIndex = scenes().findIndexOf { it.sceneLine == focusScene }
		focusSceneIndex >= 0 ? focusSceneIndex : 0
	}
	
	def tagsInAllScenes() {
		def tags
		for (sceneFile in sceneFiles()) {
			if(tags) {
				tags = tags.intersect(sceneFile.tagsInAllScenes())
			}
			else {
				tags = sceneFile.tagsInAllScenes()
			}
		}
		tags
	}
}
