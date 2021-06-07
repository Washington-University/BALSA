package balsa

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

import balsa.file.FileMetadata
import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.security.BalsaUser

class BalsaTagLib {
	def userService
	def recaptchaService
	
	def isStudyOwner = { attrs, body ->
		def studyId = attrs.studyId
		Study study = Study.get(studyId)
		BalsaUser user = userService.current
		Profile profile = user.profile
		boolean access = study.owners.contains(profile)
		return access
	}
	
	def datasetTerm = { attrs, body ->
		def item = attrs.item
		def dataset
		if (item instanceof Dataset) {
			dataset = item
		}
		else if (item instanceof Version || item instanceof FileMetadata || item instanceof SceneLine) {
			dataset = item.dataset
		}
		else if (item instanceof Scene) {
			dataset = item.sceneFile.dataset
		}
		
		if (dataset instanceof Reference) {
			out << "reference"
		}
		else {
			out << "study"
		}
	}
	
	def datasetTermPlural = { attrs, body ->
		def item = attrs.item
		def dataset
		if (item instanceof Dataset) {
			dataset = item
		}
		else if (item instanceof Version || item instanceof FileMetadata || item instanceof SceneLine) {
			dataset = item.dataset
		}
		else if (item instanceof Scene) {
			dataset = item.sceneFile.dataset
		}
		
		if (dataset instanceof Reference) {
			out << "references"
		}
		else {
			out << "studies"
		}
	}
	
	def datasetTermUppercase = { attrs, body ->
		def item = attrs.item
		def dataset
		if (item instanceof Dataset) {
			dataset = item
		}
		else if (item instanceof Version || item instanceof FileMetadata || item instanceof SceneLine) {
			dataset = item.dataset
		}
		else if (item instanceof Scene) {
			dataset = item.sceneFile.dataset
		}
		
		if (dataset instanceof Reference) {
			out << "Reference"
		}
		else {
			out << "Study"
		}
	}
	
	def displaySize = { attrs, body ->
		out << FileUtils.byteCountToDisplaySize(attrs.bytes ?: 0)
	}
	
	def shortFileTerm = { attrs, body ->
		def filename = attrs.filename
		switch (filename) {
			case ~/.*\.scene/:
			case ~/.*\.wb_scene/:
				out << "Scene"
				break
			case ~/.*\.spec/:
			case ~/.*\.wb_spec/:
				out << "Spec"
				break
			case ~/.*\.border/:
			case ~/.*\.wb_border/:
				out << "Border"
				break
			case ~/.*\.foci/:
			case ~/.*\.wb_foci/:
				out << "Foci"
				break
			case ~/.*\.annot/:
			case ~/.*\.wb_annot/:
				out << "Annotation"
				break
			case ~/.*\.wbsparse/:
				out << "Trajectory"
				break
			case ~/.*\.func\.gii/:
			case ~/.*\.shape\.gii/:
				out << "Metric"
				break
			case ~/.*\.label\.gii/:
				out << "Label"
				break
			case ~/.*\.surf\.gii/:
				out << "Surface"
				break
			case ~/.*\.dscalar\.nii/:
				out << "DScalar"
				break
			case ~/.*\.pscalar\.nii/:
				out << "PScalar"
				break
			case ~/.*\.fiberTEMP\.nii/:
				out << "Fiber"
				break
			case ~/.*\.dlabel\.nii/:
				out << "DLabel"
				break
			case ~/.*\.plabel\.nii/:
				out << "PLabel"
				break
			case ~/.*\.dtseries\.nii/:
				out << "DTSeries"
				break
			case ~/.*\.ptseries\.nii/:
				out << "PTSeries"
				break
			case ~/.*\.sdseries\.nii/:
				out << "SDSeries"
				break
			case ~/.*\.dconn\.nii/:
				out << "DConn"
				break
			case ~/.*\.pconn\.nii/:
				out << "PConn"
				break
			case ~/.*\.pdconn\.nii/:
				out << "PDConn"
				break
			case ~/.*\.dpconn\.nii/:
				out << "DPConn"
				break
			case ~/.*\.nii/:
			case ~/.*\.nii\.gz/:
				out << "Volume"
				break
			case ~/.*\.txt/:
			case ~/.*\.rtf/:
			case ~/.*\.pdf/:
			case ~/.*\.odt/:
			case ~/.*\.odp/:
			case ~/.*\.wpd/:
			case ~/.*\.doc/:
			case ~/.*\.docx/:
			case ~/.*\.ppt/:
			case ~/.*\.pptx/:
				out << "Document"
				break
			default:
				out << "Misc"
		}
	}
	
	def fileCategory = { attrs, body ->
		def filename = attrs.filename
		switch (filename) {
			case ~/.*\.scene/:
			case ~/.*\.wb_scene/:
			case ~/.*\.spec/:
			case ~/.*\.wb_spec/:
			case ~/.*\.border/:
			case ~/.*\.wb_border/:
			case ~/.*\.foci/:
			case ~/.*\.wb_foci/:
			case ~/.*\.annot/:
			case ~/.*\.wb_annot/:
			case ~/.*\.wbsparse/:
				out << "Workbench"
				break
			case ~/.*\.func\.gii/:
			case ~/.*\.shape\.gii/:
			case ~/.*\.label\.gii/:
			case ~/.*\.surf\.gii/:
				out << "GIFTI"
				break
			case ~/.*\.dscalar\.nii/:
			case ~/.*\.pscalar\.nii/:
			case ~/.*\.fiberTEMP\.nii/:
			case ~/.*\.dlabel\.nii/:
			case ~/.*\.plabel\.nii/:
			case ~/.*\.dtseries\.nii/:
			case ~/.*\.ptseries\.nii/:
			case ~/.*\.sdseries\.nii/:
			case ~/.*\.dconn\.nii/:
			case ~/.*\.pconn\.nii/:
			case ~/.*\.pdconn\.nii/:
			case ~/.*\.dpconn\.nii/:
				out << "CIFTI"
				break
			case ~/.*\.nii/:
			case ~/.*\.nii\.gz/:
				out << "NIFTI"
				break
			default:
				out << "Miscellaneous"
		}
	}
	
	def fullFileTerm = { attrs, body ->
		def filename = attrs.filename
		switch (filename) {
			case ~/.*\.scene/:
			case ~/.*\.wb_scene/:
				out << "Scene"
				break
			case ~/.*\.spec/:
			case ~/.*\.wb_spec/:
				out << "Specification"
				break
			case ~/.*\.border/:
			case ~/.*\.wb_border/:
				out << "Border"
				break
			case ~/.*\.foci/:
			case ~/.*\.wb_foci/:
				out << "Foci"
				break
			case ~/.*\.annot/:
			case ~/.*\.wb_annot/:
				out << "Annotation"
				break
			case ~/.*\.wbsparse/:
				out << "Trajectory"
				break
			case ~/.*\.func\.gii/:
			case ~/.*\.shape\.gii/:
				out << "Metric"
				break
			case ~/.*\.label\.gii/:
				out << "Label"
				break
			case ~/.*\.surf\.gii/:
				out << "Surface"
				break
			case ~/.*\.dscalar\.nii/:
				out << "Dense Scalar"
				break
			case ~/.*\.pscalar\.nii/:
				out << "Parcellated Scalar"
				break
			case ~/.*\.fiberTEMP\.nii/:
				out << "Fiber Orientation"
				break
			case ~/.*\.dlabel\.nii/:
				out << "Dense Label"
				break
			case ~/.*\.plabel\.nii/:
				out << "Parcellated Label"
				break
			case ~/.*\.dtseries\.nii/:
				out << "Dense Series"
				break
			case ~/.*\.ptseries\.nii/:
				out << "Parcellated Series"
				break
			case ~/.*\.sdseries\.nii/:
				out << "Series Data"
			case ~/.*\.dconn\.nii/:
				out << "Dense Connectivity"
				break
			case ~/.*\.pconn\.nii/:
				out << "Parcellated Connectivity"
				break
			case ~/.*\.pdconn\.nii/:
				out << "Parcellated Dense Connectivity"
				break
			case ~/.*\.dpconn\.nii/:
				out << "Dense Parcellated Connectivity"
				break
			case ~/.*\.nii/:
			case ~/.*\.nii\.gz/:
				out << "Volume"
				break
			case ~/.*\.txt/:
			case ~/.*\.rtf/:
			case ~/.*\.pdf/:
			case ~/.*\.odt/:
			case ~/.*\.odp/:
			case ~/.*\.wpd/:
			case ~/.*\.doc/:
			case ~/.*\.docx/:
			case ~/.*\.ppt/:
			case ~/.*\.pptx/:
				out << "Documentation"
				break
			default:
				out << FilenameUtils.getExtension(filename).toUpperCase()
		}
	}
	
	def recaptcha = { attrs, body ->
		out << "<script src='https://www.google.com/recaptcha/api.js' async defer></script>"
		out << "<script>function submit_${attrs.form}(token) { \$('#${attrs.form}').submit(); }</script>"
		out << "<button class='btn btn-primary g-recaptcha' data-sitekey='${recaptchaService.siteKey}' data-callback='submit_${attrs.form}'>${attrs.value}</button>"
	}
}
