package balsa.file.nifti.cifti.scalar

import balsa.file.FileController;

class FiberTempController extends FileController {
	protected Class getFileType() {
		FiberTemp.class
	}
}
