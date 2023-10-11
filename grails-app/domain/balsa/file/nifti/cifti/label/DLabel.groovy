package balsa.file.nifti.cifti.label

import balsa.file.nifti.cifti.Cifti

class DLabel extends Cifti {

    static constraints = {
    }
	
	static mapping = {
	}
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
