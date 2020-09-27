package balsa.file.nifti.cifti.series

import balsa.file.FileController

class SDSeriesController extends FileController {
	protected Class getFileType() {
		SDSeries.class
	}
}
