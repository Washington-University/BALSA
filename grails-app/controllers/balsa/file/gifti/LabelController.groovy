package balsa.file.gifti

import balsa.file.FileController

class LabelController extends FileController {
	protected Class getFileType() {
		Label.class
	}
}
