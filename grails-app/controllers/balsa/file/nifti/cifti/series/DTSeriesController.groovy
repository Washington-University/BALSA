package balsa.file.nifti.cifti.series

import balsa.file.FileController;

class DTSeriesController extends FileController {
	protected Class getFileType() {
		DTSeries.class
	}
}
