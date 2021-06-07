package balsa.file

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.Study

@Transactional(readOnly = true)
class SceneFileController extends FileController {
	@Secured("(@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file'))")
	def show(SceneFile sceneFileInstance) {
		if (notFound(sceneFileInstance)) return
		
		[file: sceneFileInstance, dependencies: sceneFileInstance.dependencies(params.version), versionId: params.version]
	}
	
	@Secured("@balsaSecurityService.canEdit(#this, 'file')")
	def edit(SceneFile sceneFileInstance) {
		if (notFound(sceneFileInstance)) return
		
		redirect controller: (sceneFileInstance.dataset instanceof Study ? 'study' : 'reference'), action: 'editScenes', id: sceneFileInstance.dataset.id, params: [startId: sceneFileInstance.id]
	}
	
	protected Class getFileType() {
		SceneFile.class
	}
}
