package balsa.file.nifti.cifti.series

import balsa.file.FileController;

class PTSeriesController extends FileController {
	protected Class getFileType() {
		PTSeries.class
	}
}
