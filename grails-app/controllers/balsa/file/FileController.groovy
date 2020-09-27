package balsa.file

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.AbstractBalsaController
import balsa.Dataset
import balsa.Study
import balsa.Version
import balsa.tagging.TagCategory

@Transactional(readOnly = true)
@Secured("@balsaSecurityService.canEdit(#this, 'file')")
class FileController extends AbstractBalsaController {
	@Secured("permitAll")
    def index() {
        params.max = 10
		def fileList = getFileType().createCriteria().list(params) {
			createAlias("versions", "v")
			eq("v.status", Version.Status.PUBLIC)
		}
		
        render (view: "/file/index", model:[fileList: fileList, fileCount: fileList.totalCount])
    }
	
	@Secured("(@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file'))")
    def show(FileMetadata file) {
		if (notFound(file) || wrongType(file)) return
		
		def view = "/file/show"
		if (file instanceof SceneFile) { // scene files have their own details screen with special requirements
			redirect controller: 'sceneFile', action: 'show', id: file.id, params: params.version
		}
		
        render (view: view, model:[file: file, versionId: params.version])
    }
	
	@Secured("(@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file'))")
	def allScenes(FileMetadata file) {
		if (notFound(file) || wrongType(file)) return
		
		def sceneList = fileService.allScenes(file)
		
		render (view: "/file/allScenes", model:[scenes:sceneList, file: file])
	}
	
//	def edit(FileMetadata file) {
//		if (notFound(file) || wrongType(file)) return
//		
//		if (file instanceof SceneFile) { // scene files have their own edit screen with special requirements
//			redirect controller: 'sceneFile', action: 'edit', id: file.id
//		}
//		
//		render (view: "/file/edit", model:[file: file, categories: TagCategory.list()])
//	}
//
//	@Transactional
//	def update(FileMetadata file) {
//		if (notFound(file) || wrongType(file)) return
//		if (hasErrors(file, 'edit', 'file')) return
//
//		file.save flush:true
//
//		redirect action: 'show', id: file.id
//	}
//	
	def tagScan(FileMetadata file) {
		if (notFound(file) || wrongType(file)) return
		
		render (template: "/tagging/tagScan", model: [tags: file.scanForTags()])
	}
	
	// to be overridden by individual file type controllers
	protected Class getFileType() {
		FileMetadata.class
	}
	
	protected def wrongType(FileMetadata file) {
		if (!getFileType().isInstance(file)) {
			render view: '/404', status: NOT_FOUND
		}
		!getFileType().isInstance(file)
	}
	
	@Transactional
	def remove(FileMetadata file) {
		if (notFound(file) || wrongType(file)) return
	
		def currentVersion = file.dataset.workingVersion()
		def versionToAlter = (currentVersion.isNonpublic() || currentVersion.isSubmitted()) ? currentVersion : new Version(currentVersion)
		versionToAlter.updatedDate = new Date()
		versionToAlter.removeFromFiles(file)
		
		redirect controller: file.dataset instanceof Study ? 'study' : 'reference', action: 'show', id: file.dataset.id
	}
}
