package balsa.file.nifti

import balsa.Version
import balsa.file.FileController
import grails.plugin.springsecurity.annotation.Secured

class VolumeController extends FileController {
	protected Class getFileType() {
		Nifti.class
	}

	@Secured("permitAll")
	def index() {
		def fileList = getFileType().createCriteria().list() {
			isNull('ciftiVersion')
			createAlias("versions", "v")
			eq("v.status", Version.Status.PUBLIC)
		}

		render (view: "/file/index", model:[fileList: fileList])
	}
}
