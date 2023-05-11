package balsa

import balsa.file.Documentation
import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.file.FileMetadata
import balsa.scene.Scene

@Transactional(readOnly = true)
class DownloadController extends AbstractBalsaController {
	@Secured('ROLE_USER')
	def mine() {
		[downloads: Download.findAllByUsername(userService.current.username)]
	}
	
	@Transactional
	@Secured('ROLE_USER')
	def anonymize() {
		def downloads = Download.findAllByUsername(userService.current.username)
		
		downloads.each {
			it.username = null
		}
		
		redirect action: 'mine'
	}
	
	@Secured("(@balsaSecurityService.canView(#this, 'dataset') || @balsaSecurityService.isPublic(#this, 'dataset')) && @balsaSecurityService.hasAccess(#this, 'dataset')")
    def downloadModalContents(Dataset datasetInstance) {
		render template:'downloadModalContents', model: [versionInstance: datasetInstance.getVersion(params.version)]
	}
	
	@Transactional
	@Secured("(@balsaSecurityService.canView(#this, 'version') || @balsaSecurityService.isPublic(#this, 'version')) && @balsaSecurityService.hasAccess(#this, 'version')")
	def download(Version versionInstance) {
		if (notFound(versionInstance)) return
		
		// parse download form to obtain list of files and scenes
		def files = []
		def scenes = []
		
		for (fileId in params.list('file')) {
			files.add(FileMetadata.get(fileId))
		}
		for (sceneId in params.list('scene')) {
			scenes.add(Scene.get(sceneId))
		}

		def useSuffix = params.suffix == 'on'

		def totalSize = 0
		
		// verify that all files/scenes are within requested version, if not render status 403
		for (file in files) {
			if (!versionInstance.files.contains(file)) {
				render status: 403
				return
			}
			totalSize += file.filesize
		}
		for (scene in scenes) {
			if (!versionInstance.files.contains(scene.sceneFile)) {
				render status: 403
				return
			}
		}
		
		// generate partial scene file structure [scenefile:[indices]]
		def partialSceneFiles = [:]
		for (scene in scenes) {
			if (!files.contains(scene.sceneFile)) {
				if (partialSceneFiles.containsKey(scene.sceneFile)) {
					partialSceneFiles[scene.sceneFile].add(scene.index)
				}
				else {
					partialSceneFiles.put(scene.sceneFile, [scene.index])
				}
				totalSize += (scene.sceneFile.filesize / scene.sceneFile.scenes.size())
			}
		}
		
		// create download object and add all elements to it
		Download download = new Download(date: new Date(), username: userService.current?.username, 
			ipAddress: request.getRemoteAddr(), totalSize: totalSize, dataset: versionInstance.dataset)
		download.save(flush: true)
		
		// download is owned by other objects so links need to be created from them
		for (file in files) {
			file.addToDownloads(download)
		}
		for (scene in scenes) {
			scene.addToDownloads(download)
		}
		
		fileService.downloadZip(versionInstance.extractDirectory(useSuffix), files, partialSceneFiles, response)
		
		render(status: 200)
	}
	
	@Transactional
	@Secured("(@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file')) && @balsaSecurityService.hasAccess(#this, 'file')")
	def downloadFile(FileMetadata file) {
		if (notFound(file)) return
		file.addToDownloads(new Download(date: new Date(), username: userService.current?.username, ipAddress: request.getRemoteAddr(), totalSize: file.filesize, dataset: file.dataset))
		fileService.downloadFile(file, response)
	}

	@Secured("(@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file')) && @balsaSecurityService.hasAccess(#this, 'file')")
	def downloadDoc(Documentation file) {
		if (notFound(file)) return
		fileService.downloadFile(file, response, file.mime)
	}

	@Secured('ROLE_CURATOR')
	def stats() {
		[downloadStats: statsService.getDownloadStats(200, null)]
	}
	
	@Secured('permitAll')
	def robots() {
		
	}
}
