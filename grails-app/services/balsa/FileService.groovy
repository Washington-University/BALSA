package balsa

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import java.security.MessageDigest
import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

import javax.servlet.http.HttpServletResponse

import org.apache.catalina.connector.ClientAbortException
import org.hibernate.SessionFactory
import org.postgresql.largeobject.LargeObjectManager

import balsa.exceptions.BalsaException
import balsa.file.Annotation
import balsa.file.Border
import balsa.file.Documentation
import balsa.file.FileError
import balsa.file.FileMetadata
import balsa.file.Foci
import balsa.file.SceneFile
import balsa.file.Spec
import balsa.file.TrajTemp
import balsa.file.gifti.Label
import balsa.file.gifti.Metric
import balsa.file.gifti.Surface
import balsa.file.nifti.Nifti
import balsa.file.nifti.cifti.conn.DConn
import balsa.file.nifti.cifti.conn.DPConn
import balsa.file.nifti.cifti.conn.PConn
import balsa.file.nifti.cifti.conn.PDConn
import balsa.file.nifti.cifti.label.DLabel
import balsa.file.nifti.cifti.label.PLabel
import balsa.file.nifti.cifti.scalar.DScalar
import balsa.file.nifti.cifti.scalar.FiberTemp
import balsa.file.nifti.cifti.scalar.PScalar
import balsa.file.nifti.cifti.series.DTSeries
import balsa.file.nifti.cifti.series.PTSeries
import balsa.file.nifti.cifti.series.SDSeries
import balsa.scene.Scene
import balsa.tagging.TagHandle

@Transactional
class FileService {
	SessionFactory sessionFactory
	def grailsApplication
	def userService
	private static final SimpleDateFormat RFC2616 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US)
	
	def stagingArea() {
		grailsApplication.config.staging.location
	}
	
	public InputStream getFileDataStream(String fileDataId) {
		def data = FileData.get(fileDataId)?.data
		getLOM().open(data).getInputStream()
	}
	
	private LargeObjectManager getLOM() {
		Connection conn = sessionFactory.getCurrentSession().connection().unwrap(org.postgresql.PGConnection)
		conn.getLargeObjectAPI()
	}
	
	/*
	 * I have to read the input stream twice, the first time to compute its hash, the second (if it has a
	 * unique hash) to store it in the database. The InputStream from ZipFile does not support marking and
	 * resetting, so the easiest thing is to create two separate streams, each of which will start at the
	 * beginning of the ZipEntry.
	 */
	public FileData uploadToDatabase(InputStream hashInput, InputStream fileInput, long length) {
		String hash = getHash(hashInput)		
		FileData extant = FileData.findByHash(hash) // check for presence of file in system
		
		if (extant != null) { // if the file is already stored in our system, return the extant file id
			return extant
		}
		
		LargeObjectManager lom = getLOM()
		long oid = lom.createLO()
		OutputStream dataStream = lom.open(oid).getOutputStream()
		dataStream << fileInput
		dataStream.flush()
		dataStream.close()
		
		FileData fileData = new FileData()
		fileData.setHash(hash)
		fileData.setData(oid)
		fileData.save(flush: true, failOnError: true)
		
		sessionFactory.getCurrentSession().refresh(fileData)
		
		fileData
	}
	
	private String getHash(InputStream input) {
		MessageDigest digest = MessageDigest.getInstance("SHA-256")
		byte[] buffer = new byte[8192]
		int read = 0
		while( (read = input.read(buffer)) > 0) {
			digest.update(buffer, 0, read)
		}
		byte[] sum = digest.digest()
		BigInteger bigInt = new BigInteger(1, sum)
		bigInt.toString(16).padLeft(64, '0')
	}
	
	public boolean process(Dataset dataset, String comments = null) {
		File root = new File(stagingArea() + dataset.getId())
		if (root.isDirectory() && root.exists()) {
			def newVersion = dataset.workingVersion() ? new Version(dataset.workingVersion()) : new Version()
			newVersion.comments = comments
			
			for (File file in root.listFiles()) {
				if (file.getName().endsWith(".zip")) {
					ZipFile zip = new ZipFile(file)
					for (ZipEntry entry in zip.entries()) {
						if (!entry.isDirectory()) {					
							
							def filepath = standardizeFilepath(entry.getName())
							if (filepath.endsWith('.DS_Store')) {
								break
							}
							
							dataset.extract = baseDirectoryName(entry.getName())
							def fileData = uploadToDatabase(zip.getInputStream(entry), zip.getInputStream(entry), entry.getSize())
							def filesize = entry.getSize()
							def zipsize = entry.getCompressedSize()
							
							
							def newFile
							try {
								newFile = create(fileData.id, fileData.data, filepath, filesize, zipsize, dataset)
							}
							catch (ValidationException e) {
								newFile = createError(fileData.id, filepath, dataset, "Processing failed to set all required values or required values missing from file.", e.getStackTrace().toString())
							}
							catch (BalsaException b) {
								newFile = createError(fileData.id, filepath, dataset, b.message, b.getStackTrace().toString())
							}
							catch (Exception e) {
								newFile = createError(fileData.id, filepath, dataset, "An error occured during processing.", e.getStackTrace().toString())
							}
							dataset.addToFiles(newFile)
							dataset.save(failOnError: true)
							newVersion.addOrReplaceFile(newFile)
						}
					}
					zip.close()
					file.delete()
				}
				else {
					if (!file.isDirectory()) {
						def filename = file.getName()
						if (!filename.endsWith('.DS_Store')) {
							def inputStream1 = file.newInputStream();
							def inputStream2 = file.newInputStream();
							def fileData = uploadToDatabase(inputStream1, inputStream2, file.length())
							inputStream1.close()
							inputStream2.close()
							def filesize = file.length()
							def zipsize = file.length()

							def newFile
							try {
								newFile = create(fileData.id, fileData.data, filename, filesize, zipsize, dataset)
							}
							catch (ValidationException e) {
								newFile = createError(fileData.id, filename, dataset, "Processing failed to set all required values or required values missing from file.", e.getStackTrace().toString())
							}
							catch (BalsaException b) {
								newFile = createError(fileData.id, filename, dataset, b.message, b.getStackTrace().toString())
							}
							catch (Exception e) {
								newFile = createError(fileData.id, filename, dataset, "An error occured during processing.", e.getStackTrace().toString())
							}
							dataset.addToFiles(newFile)
							dataset.save(failOnError: true)
							newVersion.addOrReplaceFile(newFile)
							file.delete()
						}
					}
				}
			}
			for (scene in newVersion.scenes()) {
				scene.tags = scene.aggregateTags()
				scene.save(failOnError: true)
			}
			newVersion.save(failOnError: true)
		}
	}
	
	private String standardizeFilepath(String filepath) {
		filepath = filepath.replaceAll("\\\\", "/") // separators used in zip entry names vary based on how they were zipped, so we standardize them here
		filepath = filepath.substring(filepath.indexOf("/") + 1) // remove the enclosing folder name
		filepath
	}
	
	private String baseDirectoryName(String filepath) {
		filepath = filepath.replaceAll("\\\\", "/") // separators used in zip entry names vary based on how they were zipped, so we standardize them here
		filepath.substring(0, filepath.indexOf("/"))
	}
	
	public FileMetadata create(fileDataId, fileDataData, filepath, filesize, zipsize, dataset) {
		def existingFile = dataset.files.find { it.fileDataId == fileDataId && it.filepath == filepath }
		if (existingFile && !(existingFile instanceof FileError)) {
			return existingFile
		}
		
		def file = newFile(filepath)
		file.dataset = dataset
		file.filename = filepath.substring(filepath.lastIndexOf("/") + 1) // last section of file path
		file.filepath = filepath
		file.fileDataId = fileDataId
		file.filesize = filesize
		file.zipsize = zipsize
		file.createdBy = userService.current.username // if I try to update a scene rather than replace it, this line throws a transient object error, lord knows why
		file.added = new Date()
		InputStream input = getLOM().open(fileDataData).getInputStream()
		try {
			file.setValuesFromFile(input)
		}
		catch (BalsaException b) {
			file.discard()
			throw b
		}
		catch (Exception e) {
			file.discard()
			throw new Exception("Could not process data from file or file unreadable.")
		}
		input.close()
		file.tags = file.scanForTags()
		file.save(flush: true, failOnError: true)
		file
	}
	
	public FileError createError(String fileDataId, String filepath, Dataset dataset, String message, String stacktrace) {
		def error = new FileError()
		error.dataset = dataset
		error.filename = filepath.substring(filepath.lastIndexOf("/") + 1) // last section of file path
		error.filepath = filepath.substring(filepath.indexOf("/") + 1) // remove the enclosing folder name
		error.fileDataId = fileDataId
		error.filesize = 0
		error.zipsize = 0
		error.message = message
		error.stacktrace = stacktrace
		error.createdBy = userService.current.username
		error.added = new Date()
		error.save(failOnError: true)
		error
	}
	
	public FileMetadata newFile(String filename) {
		switch (filename) {
			case ~/.*\.scene/:
			case ~/.*\.wb_scene/:
				return new SceneFile()
			case ~/.*\.spec/:
			case ~/.*\.wb_spec/:
				return new Spec()
			case ~/.*\.border/:
			case ~/.*\.wb_border/:
				return new Border()
			case ~/.*\.foci/:
			case ~/.*\.wb_foci/:
				return new Foci()
			case ~/.*\.annot/:
			case ~/.*\.wb_annot/:
				return new Annotation()
			case ~/.*\.wbsparse/:
				return new TrajTemp()
			case ~/.*\.func\.gii/:
			case ~/.*\.shape\.gii/:
				return new Metric()
			case ~/.*\.label\.gii/:
				return new Label()
			case ~/.*\.surf\.gii/:
				return new Surface()
			case ~/.*\.dscalar\.nii/:
				return new DScalar()
			case ~/.*\.pscalar\.nii/:
				return new PScalar()
			case ~/.*\.fiberTEMP\.nii/:
				return new FiberTemp()
			case ~/.*\.dlabel\.nii/:
				return new DLabel()
			case ~/.*\.plabel\.nii/:
				return new PLabel()
			case ~/.*\.dtseries\.nii/:
				return new DTSeries()
			case ~/.*\.ptseries\.nii/:
				return new PTSeries()
			case ~/.*\.sdseries\.nii/:
				return new SDSeries()
			case ~/.*\.dconn\.nii/:
				return new DConn()
			case ~/.*\.pconn\.nii/:
				return new PConn()
			case ~/.*\.pdconn\.nii/:
				return new PDConn()
			case ~/.*\.dpconn\.nii/:
				return new DPConn()
			case ~/.*\.nii/:
				return new Nifti(false)
			case ~/.*\.nii\.gz/:
				return new Nifti(true)
			case ~/.*\.txt/:
				return new Documentation('text/plain')
			case ~/.*\.rtf/:
				return new Documentation('application/rtf')
			case ~/.*\.pdf/:
				return new Documentation('application/pdf')
			case ~/.*\.odt/:
				return new Documentation('application/vnd.oasis.opendocument.text')
			case ~/.*\.odp/:
				return new Documentation('application/vnd.oasis.opendocument.presentation')
			case ~/.*\.wpd/:
				return new Documentation('application/wordperfect')
			case ~/.*\.doc/:
				return new Documentation('application/msword')
			case ~/.*\.docx/:
				return new Documentation('application/vnd.openxmlformats-officedocument.wordprocessingml.document')
			case ~/.*\.ppt/:
				return new Documentation('application/vnd.ms-powerpoint')
			case ~/.*\.pptx/:
				return new Documentation('application/vnd.openxmlformats-officedocument.presentationml.presentation')
			default:
				return new FileMetadata()
		}
	}
	
	public void downloadZip(String extractDirectory, List files, Map partialSceneFiles, HttpServletResponse response) {
		response.setContentType("application/zip")
		response.setHeader("Content-Disposition", "Attachment;Filename=\"${extractDirectory}.zip\"")
		response.setHeader("Last-Modified", RFC2616.format(Collections.max(files*.added ?: [new Date()])))
		ZipOutputStream zos = new ZipOutputStream(response.outputStream)
		try {
			for (FileMetadata file in files.sort { a,b -> a.filepath <=> b.filepath }) {
				if (!(file instanceof FileError)) {
					InputStream is = getFileDataStream(file.fileDataId)
					def entry = new ZipEntry(extractDirectory + "/" + file.filepath)
					entry.setTime(file.added.time)
					zos.putNextEntry(entry)
					zos << is
					zos.closeEntry()
					is.close()
				}
			}
			for (psf in partialSceneFiles.sort { a,b -> a.key.filepath <=> b.key.filepath }) {
				def entry = new ZipEntry(extractDirectory + "/" + namePartialSceneFile(psf.key, psf.value))
				entry.setTime(psf.key.added.time)
				zos.putNextEntry(entry)
				trimSceneFile(psf.key, psf.value, zos)
				zos.closeEntry()
			}
			zos.finish()
			zos.flush()
			zos.close()
		}
		catch (ClientAbortException e) {
			// ignore, as this is almost certainly just someone closing their browser window
			def a = 3
		}
	}
	
	private String namePartialSceneFile(SceneFile sceneFile, List indices) {
		def partialTag = '_partial'
		def startIndex = 2000
		for (int i = 0; i <= sceneFile.scenes.size(); i++) {
			if (indices.contains(i)) {
				if (startIndex > i) {
					startIndex = i
					partialTag += '_' + i
				}
			}
			else {
				if (startIndex == i - 1) {
					startIndex = 2000
				}
				else if (startIndex < i) {
					startIndex = 2000
					partialTag += '-' + (i - 1).toString()
				}
			}
		}
		
		sceneFile.filepath.replace('.scene',  partialTag + '.scene')
	}
	
	private void trimSceneFile(SceneFile sceneFile, List indices, OutputStream out) {
		InputStream is = getFileDataStream(sceneFile.fileDataId)
		
		InputStreamReader isr = new InputStreamReader(is)
		BufferedReader br = new BufferedReader(isr)
		String line
		
		def sceneInfoStart = /<SceneInfo Index="([0-9]+)">/
		def sceneInfoEnd = /<\/SceneInfo>/
		def sceneStart = /<Scene Index="([0-9]+)"/
		def sceneEnd = /<\/Scene>/
		
		def sceneInfoIndexCount = 0
		def sceneIndexCount = 0
		
		while ((line = br.readLine()) != null) {
			def sceneInfoStartMatch = (line =~ /$sceneInfoStart/)
			def sceneStartMatch = (line =~ /$sceneStart/)
			
			if (sceneInfoStartMatch.size() > 0) {
				def index = sceneInfoStartMatch[0][1]
				if (indices.contains(index as Integer)) {
					out << (line.replace(index, sceneInfoIndexCount.toString()) + "\n")
					sceneInfoIndexCount++
				}
				else {
					while ((line = br.readLine()) != null) {
						if (line.find(sceneInfoEnd)) break
					}
				}
			}
			else if (sceneStartMatch.size() > 0) {
				def index = sceneStartMatch[0][1]
				if (indices.contains(index as Integer)) {
					out << (line.replace(index, sceneIndexCount.toString()) + "\n")
					sceneIndexCount++
				}
				else {
					while ((line = br.readLine()) != null) {
						if (line.find(sceneEnd)) break
					}
				}
			}
			else {
				out << (line + "\n")
			}
		}
		is.close()
	}
	
	public void downloadFile(FileMetadata file, HttpServletResponse response, String mime = 'application/octet-stream') {
		response.setContentType(mime)
		response.setHeader("Content-Disposition", "Attachment;Filename=\"${file.filename}\"")
		response.setHeader("Content-Length", file.filesize.toString())
		response.setHeader("Last-Modified", RFC2616.format(file.added))
		InputStream fis = getFileDataStream(file.fileDataId)
		try {
			response.outputStream << fis
			response.outputStream.flush()
			fis.close()
		}
		catch (ClientAbortException e) {
			// ignore, as this is almost certainly just someone closing their browser window
		}
	}
	
	def allScenes(FileMetadata file) {
		def c = FileMetadata.createCriteria()
		def equivalentFiles = c.list {
			eq("fileDataId", file.fileDataId)
		}
		def sceneList = []
		for (FileMetadata equivalentFile : equivalentFiles) {
			def datasetId = equivalentFile.dataset.id
			c = Scene.createCriteria()
			def results = c.list {
				createAlias("sceneFile.versions", "v")
				eq("v.status", Version.Status.PUBLIC)
				sceneFile {
					dataset {
						eq("id", datasetId)
					}
				}
				pgArrayContains("supportingFiles", equivalentFile.filepath)
			}
			sceneList.addAll(results)
		}
		sceneList
	}
	
	def deleteFileData(FileData fileData) {
		// find and delete (mark removed?) all FileMetadatas that reference this file data
		// delete the underlying blob
		// delete the file data entry
	}
	
	def tagHandles() {
		TagHandle.count()
	}
}
