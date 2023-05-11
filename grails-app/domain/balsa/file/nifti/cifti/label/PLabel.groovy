package balsa.file.nifti.cifti.label

import balsa.file.nifti.cifti.Cifti

class PLabel extends Cifti {
    static constraints = {
    }
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
