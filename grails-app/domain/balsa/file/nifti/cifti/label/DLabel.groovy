package balsa.file.nifti.cifti.label

import balsa.file.nifti.cifti.Cifti

class DLabel extends Cifti {
	//type
	//structure
	//size
	//maps to surface
	//maps to volume
	//maps with labeltable
	//maps with palette
	//all map palettes equal
	//map interval units
	//map interval start
	//map interval step
	//no rows
	//no columnns
	//volume dim 0
	//volume dim 1
	//volume dim 2
	//palette type
	//cifti dim 0
	//cifti dim 1
	//row map type
	//column map type
	//has volume data
	//volume dims
	//volume space
	//array of voxels per brain structure
	//number of vertices
	//number of triangles
	//normal vectors correct
	//surface type (primary)
	//surface type (secondary)
	//x-min
	//x-max
	//y-min
	//y-max
	//z-min
	//z-max
	//surface area
	//spacing mean
	//spacing std dev
	//spacing min
	//spacing max



    static constraints = {
    }
	
	static mapping = {
	}
	
	def setValuesFromFile(InputStream input) {
		def ciftiFile = super.setValuesFromFile(input)
	}
}
