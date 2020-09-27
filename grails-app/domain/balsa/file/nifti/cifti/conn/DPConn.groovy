package balsa.file.nifti.cifti.conn

import balsa.file.nifti.cifti.Cifti

class DPConn extends Cifti {

    static constraints = {
    }
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
