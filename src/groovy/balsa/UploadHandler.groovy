package balsa

import java.security.MessageDigest
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class UploadHandler {
	ZipFile zip
	def statuses = [:]
	
	UploadHandler(ZipFile zip) {
		this.zip = zip
		for (ZipEntry entry in zip.entries()) {
			statuses.put(entry.getName(), new UploadStatus(size:entry.getSize()))
		}
	}
	
	def process() {
		for (ZipEntry entry in zip.entries()) {
			def hash = getHash(entry.getName(), zip.getInputStream(entry))
			statuses[entry.getName()].hash = hash
		}
	}
	
	private String getHash(String name, InputStream input) {
		MessageDigest digest = MessageDigest.getInstance("SHA-256")
		byte[] buffer = new byte[8192]
		int read = 0
		while( (read = input.read(buffer)) > 0) {
			digest.update(buffer, 0, read)
			statuses[name].bytesHashed += read
		}
		byte[] sum = digest.digest()
		BigInteger bigInt = new BigInteger(1, sum)
		bigInt.toString(16).padLeft(64, '0')
	}
	
	class UploadStatus {
		Number size
		Number bytesHashed = 0
		String hash
	}
}
