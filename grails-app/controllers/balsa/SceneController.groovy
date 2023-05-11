package balsa


import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

import org.apache.catalina.connector.ClientAbortException

import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.scene.ScenePreview


@Transactional(readOnly = true)
@Secured(['permitAll'])
class SceneController extends AbstractBalsaController {
	
	@Secured("(@balsaSecurityService.canView(#this, 'sceneLine') || @balsaSecurityService.isPublic(#this, 'sceneLine'))")
	def show(SceneLine sceneLineInstance) {
		if (notFound(sceneLineInstance)) return
		
		Scene sceneInstance = sceneLineInstance.sceneForVersion(params.version)
		
		if (notFound(sceneInstance)) return
		
		[sceneInstance: sceneInstance, dependencies: sceneInstance.dependencies(params.version)]
	}
	
	@Secured("(@balsaSecurityService.canView(#this, 'scene') || @balsaSecurityService.isPublic(#this, 'scene'))")
	def image(ScenePreview scenePreviewInstance) {
		if (notFound(scenePreviewInstance)) return
		
		byte[] image = scenePreviewInstance.image
		String imageName = params.id + "." + scenePreviewInstance.imageFormat
		String mimeType = grailsMimeUtility.getMimeTypeForExtension(scenePreviewInstance.imageFormat)
		response.setStatus(200)
		response.setContentType(mimeType)
		response.setContentLength(image.length as int)
			OutputStream outStream = response.getOutputStream()
		try {
			outStream.write(image)
			outStream.flush()
		}
		catch (ClientAbortException e) {
			// ignore, as this is almost certainly just someone closing their browser window; I'm just tired of it showing up in the logs
		}
		finally {
			outStream.close()
		}
	}
	
	@Secured("@balsaSecurityService.canEdit(#this, 'scene')")
	def edit(Scene sceneInstance) {
		if (notFound(sceneInstance)) return
		
		redirect controller: (sceneInstance.sceneFile.dataset instanceof Study ? 'study' : 'reference'), action: 'editScenes', id: sceneInstance.sceneLine.dataset.id, params: [startId: sceneInstance.id]
	}
	
	@Secured("@balsaSecurityService.canEdit(#this, 'scene')")
	def tagScan(Scene sceneInstance) {
		if (notFound(sceneInstance)) return
		
		def tags = [] as Set
		for (file in sceneInstance.dependencies(params.version)) {
			tags.addAll(file.scanForTags())
		}
		
		render (template: "/tagging/tagScan", model: [tags: tags])
	}
	
	@Transactional
	def newId() {
		def generator = new BalsaIdGenerator()
		render generator.generate(null, null)
	}
	
	@Transactional
	def newIds() {
		def generator = new BalsaIdGenerator()
		def number = params.id.toInteger()
		def idList = ''
		for (int i = 0; i < number; i++) {
			idList += generator.generate(null, null) + "<br>"
		}
		render idList
	}
}
