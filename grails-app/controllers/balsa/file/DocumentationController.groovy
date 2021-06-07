package balsa.file

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.Download

class DocumentationController extends FileController {
	protected Class getFileType() {
		Documentation.class
	}
	
	@Transactional
	@Secured("@balsaSecurityService.canView(#this, 'file') || @balsaSecurityService.isPublic(#this, 'file')")
	def download(Documentation file) {
		if (notFound(file)) return
		file.addToDownloads(new Download(date: new Date(), username: userService.current?.username, ipAddress: request.getRemoteAddr(), totalSize: file.filesize, dataset: file.dataset))
		fileService.downloadFile(file, response, file.mime)
	}
}
