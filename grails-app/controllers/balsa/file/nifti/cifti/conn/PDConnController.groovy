package balsa.file.nifti.cifti.conn

import balsa.file.FileController;

class PDConnController extends FileController {
	protected Class getFileType() {
		PDConn.class
	}
}
