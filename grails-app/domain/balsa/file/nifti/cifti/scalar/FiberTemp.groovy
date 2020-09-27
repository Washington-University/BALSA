package balsa.file.nifti.cifti.scalar

import balsa.file.nifti.cifti.Cifti

class FiberTemp extends Cifti {
	
	static constraints = {
	}
	
	static mapping = {
	}
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
