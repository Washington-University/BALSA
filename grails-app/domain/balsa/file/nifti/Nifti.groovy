package balsa.file.nifti

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.zip.GZIPInputStream

import balsa.file.FileMetadata

class Nifti extends FileMetadata {
	boolean compressed
	int niftiVersion
	String datatype
	int bitsPerVoxel
	long voxelOffset

	public Nifti(boolean compressed) {
		this.compressed = compressed
	}
	
    static constraints = {
    }
	
	def setValuesFromFile(InputStream input) {
		ByteOrder order = ByteOrder.BIG_ENDIAN
		if (compressed) {
			input = new GZIPInputStream(input)
		}
		
		int headerLength = ByteBuffer.wrap(readBytes(input, 4)).order(order).getInt()
		switch (headerLength) {
			case 1543569408:
				order = ByteOrder.LITTLE_ENDIAN
			case 348:
				niftiVersion = 1
				break
			case 469893120:
				order = ByteOrder.LITTLE_ENDIAN
			case 540:
				niftiVersion = 2
				break
			default:
				throw new Exception("NIFTI version could not be derived.")
		}
		if (niftiVersion == 1) {
			readBytes(input, 66) // skip to datatype
			datatype = codeToDatatype(ByteBuffer.wrap(readBytes(input, 2)).order(order).getShort())
			bitsPerVoxel = ByteBuffer.wrap(readBytes(input, 2)).order(order).getShort()
			readBytes(input, 34) // skip to vox_offset
			voxelOffset = ByteBuffer.wrap(readBytes(input, 4)).order(order).getFloat()
			readBytes(input, 236) // skip to end of header
		}
		else if (niftiVersion == 2) {
			readBytes(input, 8) // skip to datatype
			datatype = codeToDatatype(ByteBuffer.wrap(readBytes(input, 2)).order(order).getShort())
			bitsPerVoxel = ByteBuffer.wrap(readBytes(input, 2)).order(order).getShort()
			readBytes(input, 152) // skip to vox_offset
			voxelOffset = ByteBuffer.wrap(readBytes(input, 8)).order(order).getLong()
			readBytes(input, 364) // skip to end of header
		}
		
	}
	
	private byte[] readBytes(InputStream input, int numberToRead) {
		byte[] b = new byte[numberToRead]
		input.read(b)
		return b
	}
	
	private String codeToDatatype(int datatypeCode) {
		switch (datatypeCode) {
			case 0:
				return "DT_NONE"
			case 1:
				return "DT_BINARY"
			case 2:
				return "NIFTI_TYPE_UINT8"
			case 4:
				return "NIFTI_TYPE_INT16"
			case 8:
				return "NIFTI_TYPE_INT32"
			case 16:
				return "NIFTI_TYPE_FLOAT32"
			case 32:
				return "NIFTI_TYPE_COMPLEX64"
			case 64:
				return "NIFTI_TYPE_FLOAT64"
			case 128:
				return "NIFTI_TYPE_RGB24"
			case 256:
				return "NIFTI_TYPE_INT8"
			case 512:
				return "NIFTI_TYPE_UINT16"
			case 768:
				return "NIFTI_TYPE_UINT32"
			case 1024:
				return "NIFTI_TYPE_INT64"
			case 1280:
				return "NIFTI_TYPE_UINT64"
			case 1536:
				return "NIFTI_TYPE_FLOAT128"
			case 1792:
				return "NIFTI_TYPE_COMPLEX128"
			case 2048:
				return "NIFTI_TYPE_COMPLEX256"
		}
	}
}
