package balsa.file.nifti.cifti.series

import balsa.file.nifti.cifti.Cifti

class SDSeries extends Cifti {
	
	static constraints = {
	}
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
