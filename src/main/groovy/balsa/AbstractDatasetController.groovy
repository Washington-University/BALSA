package balsa


import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import grails.util.Environment
import grails.web.Action

import java.nio.file.Files

import balsa.curation.Issue
import balsa.curation.IssueComment
import balsa.file.Documentation
import balsa.file.FileMetadata
import balsa.file.SceneFile
import balsa.scene.SceneLine
import balsa.security.Terms
import balsa.tagging.TagCategory

abstract class AbstractDatasetController extends AbstractBalsaController {
	static final int BUFF_SIZE = 100000;
	static final byte[] buffer = new byte[BUFF_SIZE];
	
	abstract Class getDatasetType()
	
	@Action	
	@Secured(['permitAll'])
	def index() {
		def datasetInstanceList = getDatasetType().createCriteria().listDistinct() {
			if (SpringSecurityUtils.ifNotGranted('ROLE_CURATOR')) {
				versions {
					eq("status", Version.Status.PUBLIC)
				}
			}
		}
		render (view: '/dataset/index', model: [datasetInstanceList: datasetInstanceList, datasetType:getDatasetType().getSimpleName()])
	}
	
	@Action
	@Secured("(@balsaSecurityService.canView(#this, 'dataset') || @balsaSecurityService.isPublic(#this, 'dataset'))")
	def show() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		render (view: '/dataset/show', model: [datasetInstance: datasetInstance, versionInstance: versionInstance])
	}
	
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def downloadStats() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		render (template: "/templates/downloadStatsModalContents", model: [downloadStats: statsService.getDownloadStats(10, datasetInstance)])
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def edit() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		render (view: '/dataset/edit', model: [datasetInstance: datasetInstance, versionInstance: versionInstance])
	}
	
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def editScenes() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		render (view: '/dataset/editScenes', model: [datasetInstance: datasetInstance, versionInstance: versionInstance, startId: params.startId, tagCategories: TagCategory.list()])
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def saveSceneEdits() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		for (sceneFile in versionInstance.sceneFiles()) {
			sceneFile.label = params['label' + sceneFile.id]
			for (scene in sceneFile.scenes) {
				scene.shortName = params['shortName' + scene.id]
				def sceneTags = params['tags' + scene.id]
				scene.tags = sceneTags.split(',')
			}
		}
		
		render 'Save successful.'
	}
	
	// UPLOADING
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def upload() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		render (view: '/dataset/upload', model: [datasetInstance: datasetInstance])
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def processUpload() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		fileService.process(datasetInstance, params.comments)
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def sceneList() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		render datasetInstance.workingVersion().sceneFiles().collect { sceneFile ->
			[ sceneFile.filepath, sceneFile.scenes.collect{ [it.index, it.id] } ]
		} as JSON
	}

	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def checkScenes() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return

		def sceneList = params.sceneList?.split(',')

		def badSceneIds = []
		for (sceneId in sceneList) {
			SceneLine sceneLine = SceneLine.get(sceneId)
			if (sceneLine && sceneLine.dataset != datasetInstance) {
				badSceneIds.add(sceneId)
			}
		}

		def generator = new BalsaIdGenerator()
		def newSceneIds = []
		for (sceneId in badSceneIds) {
			newSceneIds.add(generator.generate(null, null))
		}

		render([badSceneIds: badSceneIds, newSceneIds: newSceneIds] as JSON)
	}
	
	// from Uploadr
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def handleUpload() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def fileName    = URLDecoder.decode(request.getHeader('X-File-Name') ?: 'temp.zip', 'UTF-8') as String
		def fileSize 	= (request.getHeader('X-File-Size') != "undefined") ? request.getHeader('X-File-Size') as Long : 0L
		def savePath	= params.id
		def dir 		= new File(grailsApplication.config.staging.location, savePath)
		def file		= new File(dir,fileName)
		int status      = 0
		def statusText  = ""
		
		Files.deleteIfExists(file.toPath())

		// set response content type to json
		response.contentType    = 'application/json'

		// does the path exist?
		if (!dir.exists()) {
			// attempt to create the path
			try {
				dir.mkdirs()
			} catch (Exception e) {
				response.sendError(500, "could not create upload path ${savePath}")
				render([written: false, fileName: file.name] as JSON)
				return false
			}
		}

		// do we have enough space available for this upload?
		def freeSpace = dir.getUsableSpace()
		if (fileSize > freeSpace) {
			// not enough free space
			response.sendError(500, "cannot store '${fileName}' (${fileSize} bytes), only ${freeSpace} bytes of free space left on device")
			render([written: false, fileName: file.name] as JSON)
			return false
		}

		// is the file writable?
		if (!dir.canWrite()) {
			// no, try to make it writable
			if (!dir.setWritable(true)) {
				response.sendError(500, "'${savePath}' is not writable, and unable to change rights")
				render([written: false, fileName: file.name] as JSON)
				return false
			}
		}

		// define input and output streams
		InputStream inStream = null
		OutputStream outStream = null

		// handle file upload
		try {
			inStream = request.getInputStream()
			outStream = new FileOutputStream(file)

			while (true) {
				synchronized (buffer) {
					int amountRead = inStream.read(buffer);
					if (amountRead == -1) {
						break
					}
					outStream.write(buffer, 0, amountRead)
				}
			}
			outStream.flush()

			status      = 200
			statusText  = "'${file.name}' upload successful!"
		} catch (Exception e) {
			// whoops, looks like something went wrong
			status      = 500
			statusText  = e.getMessage()
		} finally {
			if (inStream != null) inStream.close()
			if (outStream != null) outStream.close()
		}

		// make sure the file was properly written
		if (status == 200 && fileSize > file.size()) {
			// whoops, looks like the transfer was aborted!
			status      = 500
			statusText  = "'${file.name}' transfer incomplete, received ${file.size()} of ${fileSize} bytes"
		}

		// got an error of some sorts?
		if (status != 200) {
			// then -try to- delete the file
			try {
				file.delete()
			} catch (Exception e) { }
		}

		// render json response
		response.setStatus(status)
		render([written: (status == 200), fileName: file.name, status: status, statusText: statusText] as JSON)
	}
	
	@Action
	@Transactional
	@Secured("ROLE_CURATOR")
	def approveCustomTerms() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		Terms customTerms = new Terms(title: datasetInstance.customTermsTitle, contents: datasetInstance.customTermsContent)
		datasetInstance.customTermsTitle = ''
		datasetInstance.customTermsContent = ''
		datasetInstance.addToAccessAgreements(customTerms)
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def removeFiles() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def versionToAlter = new Version(datasetInstance.workingVersion())
		versionToAlter.updatedDate = new Date()
		
		params.list('fileToRemove').each {
			versionToAlter.removeFromFiles(FileMetadata.get(it))
			it.removeFromVersions(versionToAlter)
		}

		versionToAlter.save()
		
		redirect action: 'show', id: datasetInstance.id
	}

	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def removeAllFiles() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return

		def versionToAlter = new Version(datasetInstance.workingVersion())
		versionToAlter.updatedDate = new Date()

		versionToAlter.files.each {
			versionToAlter.removeFromFiles(FileMetadata.get(it))
			it.removeFromVersions(versionToAlter)
		}

		versionToAlter.save()

		redirect action: 'show', id: datasetInstance.id
	}

	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def setWorkingVersion() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		versionInstance.updatedDate = new Date()
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	// NOTES
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def updateNotes() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		datasetInstance.notes = params.notes
		datasetInstance.readNotes = userService.current.username
		
		render(status: 200)
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def readNotes() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		datasetInstance.readNotes += ',' + userService.current.username
		
		render(status: 200)
	}
	
	// STATUS CHANGES
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def submitForCuration() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def submittedVersion = datasetInstance.submittedVersion()
		if (submittedVersion) submittedVersion.status = Version.Status.NONPUBLIC
		
		def workingVersion = datasetInstance.workingVersion()
		if (workingVersion.status == Version.Status.NONPUBLIC) {
			workingVersion.status = Version.Status.SUBMITTED
			workingVersion.updatedDate = new Date()
			
			if (datasetInstance instanceof Study) {
				if (!workingVersion.submitter) {
					workingVersion.submitter = userService.current
					workingVersion.submittedDate = new Date()
				}
				
				if (Environment.current == Environment.PRODUCTION) {
					mailService.sendMail {
						to (datasetInstance.owners*.profile.emailAddress).findAll()
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Submitted'
						html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been submitted for curation.'
					}
					
					mailService.sendMail {
						to datasetInstance.curator?.profile?.emailAddress ?: grailsApplication.config.balsa.curatorContacts
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Submitted'
						html 'A BALSA study, <a href="' + grailsLinkGenerator.link(action: 'issues', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been submitted for curation.'
					}
				}
			}
		}
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def withdrawFromCuration() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def submittedVersion = datasetInstance.submittedVersion()
		if (submittedVersion) {
			submittedVersion.status = Version.Status.NONPUBLIC
			submittedVersion.updatedDate = new Date()
			
			if (datasetInstance instanceof Study) {
				if (Environment.current == Environment.PRODUCTION) {
					mailService.sendMail {
						to (datasetInstance.owners*.profile.emailAddress).findAll()
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Withdrawn From Curation'
						html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been withdrawn from curation.'
					}
				}
			}
		}
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("ROLE_CURATOR")
	def approve() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def approvedVersion = datasetInstance.approvedVersion()
		if (approvedVersion) approvedVersion.status = Version.Status.NONPUBLIC
		
		def submittedVersion = datasetInstance.submittedVersion()
		if (submittedVersion) {
			submittedVersion.status = Version.Status.APPROVED
			submittedVersion.updatedDate = new Date()
			
			if (datasetInstance instanceof Study) {	
				if (Environment.current == Environment.PRODUCTION) {
					mailService.sendMail {
						to (datasetInstance.owners*.profile.emailAddress).findAll()
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Approved'
						html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been approved.' +
							(submittedVersion.releaseDate ? (' It will be made public as of its release date: ' + submittedVersion.releaseDate.format("yyyy-MM-dd h:mm a z") + '.') : '')
					}
				}
			}
		}
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("ROLE_CURATOR")
	def revise() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def submittedVersion = datasetInstance.submittedVersion()
		if (submittedVersion) {
			submittedVersion.status = Version.Status.NONPUBLIC
			
			if (datasetInstance instanceof Study) {
				if (Environment.current == Environment.PRODUCTION) {
					mailService.sendMail {
						to (datasetInstance.owners*.profile.emailAddress).findAll()
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Returned For Revision'
						html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been returned for revision. Changes are required before submitting again. A list of issues to be addressed is available <a href="' +
							grailsLinkGenerator.link(action: 'issues', id: datasetInstance.id, absolute: true) + '">here</a>.'
					}
				}
			}
		}
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def removeFromApproved() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def approvedVersion = datasetInstance.approvedVersion()
		if (approvedVersion) {
			approvedVersion.status = Version.Status.NONPUBLIC
			approvedVersion.updatedDate = new Date()
			
			if (datasetInstance instanceof Study) {
				if (Environment.current == Environment.PRODUCTION) {
					mailService.sendMail {
						to (datasetInstance.owners*.profile.emailAddress).findAll()
						from 'noreply@balsa.wustl.edu'
						subject 'BALSA Study Withdrawn'
						html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
							'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
							'</a> has been returned to editable status.'
					}
				}
			}
		}
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset') && @balsaSecurityService.isPublic(#this, 'dataset')")
	def removeFromPublic() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		datasetInstance?.getVersion(params.version).status = Version.Status.NONPUBLIC
		
		if (datasetInstance instanceof Study) {
			if (Environment.current == Environment.PRODUCTION) {
				mailService.sendMail {
					to (datasetInstance.owners*.profile.emailAddress).findAll()
					from 'noreply@balsa.wustl.edu'
					subject 'BALSA Study Withdrawn'
					html 'Your BALSA study, <a href="' + grailsLinkGenerator.link(action: 'show', id: datasetInstance.id, absolute: true) +
						'">' + (datasetInstance.shortTitle ?: datasetInstance.title) +
						'</a> has been withdrawn from public availability.'
				}
			}
		}
		
		redirect action: 'show', id: datasetInstance.id
	}
	
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def messageCurator() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return

		if (!recaptchaService.verifyAnswer(session, params)) {
			render(status: 418)
			return
		}

		if (Environment.current == Environment.PRODUCTION) {
			mailService.sendMail {
				to datasetInstance.curator?.profile?.emailAddress ?: grailsApplication.config.balsa.curatorContacts
				from 'noreply@balsa.wustl.edu'
				subject 'BALSA Message: ' + params.subject
				html userService.current.username + ' sent the following message to the BALSA curator(s) of study "<a href="balsa.wustl.edu/' + 
					(datasetInstance instanceof Study ? 'study/' : 'reference/') + datasetInstance.id + '">' + datasetInstance.title + '</a>":<br><br>' + 
					params.contents.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('\n','<br>') + '<br><br>Replies should be sent to <a href="mailto:' +
					params.emailAddress.replaceAll('<','&lt;').replaceAll('>','&gt;') + '">' +
					params.emailAddress.replaceAll('<','&lt;').replaceAll('>','&gt;') + '</a>'
			}
		}
		
		render(status: 200)
	}
	
	// LINKED SCENES
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def addLinkedScene() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		def scene = SceneLine.get(params.sceneId)
		if (notFound(scene)) return
		versionInstance.addToLinkedScenes(scene)
		
		render(status: 200)
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def removeLinkedScene() {
		Dataset datasetInstance = Dataset.get(params.id)
		Version versionInstance = datasetInstance?.getVersion(params.version)
		if (notFound(datasetInstance) || notFound(versionInstance) || wrongType(datasetInstance)) return
		
		def scene = SceneLine.get(params.sceneId)
		if (notFound(scene)) return
		versionInstance.removeFromLinkedScenes(scene)
		
		render(status: 200)
	}
	
	//ISSUES
	@Action
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def issues() {
		Dataset datasetInstance = Dataset.get(params.id)
		def warnings = [] as Set
		
		// dataset level warnings
		if (!datasetInstance.species) {
			warnings.add('This dataset has no species information.')
		}
		if (datasetInstance.species?.find({ it.name.equalsIgnoreCase('Human')}) && !datasetInstance.accessAgreements && !datasetInstance.restrictedAccessTerms) {
			warnings.add('This dataset contains human data but is not protected by data access terms.')
		}		
		if (datasetInstance.customTermsTitle && datasetInstance.customTermsContent) {
			warnings.add('This dataset has proposed custom data access terms that have not been approved.')
		}
		if (!datasetInstance.sceneLines) {
			warnings.add('This dataset has no scenes.')
		}
		if (!datasetInstance.files) {
			warnings.add('This dataset has no files.')
		}
		
		// version level warnings
		def versionInstance = datasetInstance.submittedVersion() ?: datasetInstance.workingVersion()
		
		if (datasetInstance instanceof Study) {
			if (!versionInstance.releaseDate) {
				warnings.add('No release date has been set for this dataset.')
			}
			if (!versionInstance.publication) {
				warnings.add('No publication has been set for this dataset.')
			}
			else if (!versionInstance.publication?.approved) {
				warnings.add('The publication name for this dataset requires approval.')
			}
			if (versionInstance.institutions*.approved.contains(false)) {
				warnings.add('One or more institution names for this dataset requires approval.')
			}
		}
		
		// species tags
		def lacksSpeciesTag = []
		versionInstance.scenes().each { scene ->
			if (!scene.hasSpeciesTag()) {
				lacksSpeciesTag.add(scene)
			}
		}
		
		// files
		def versionFiles = versionInstance.files
		def filepaths = versionFiles*.filepath
		
		def allDependentFiles = []
		versionInstance.scenes().each {
			allDependentFiles += it.supportingFiles as List
		}
		def missingFiles = (allDependentFiles - filepaths).unique()
		
		def notUsed = []
		versionInstance.files.each { file ->
			if (!(file instanceof Documentation || file instanceof SceneFile || allDependentFiles.contains(file.filepath))) {
				notUsed.add(file)
			}
		}
		
		def identicalPaths = []
		def duplicatePaths = filepaths.countBy{it}.grep{it.value > 1}.collect{it.key}
		duplicatePaths.each { path ->
			identicalPaths.add(versionFiles.findAll { it.filepath == path } )
		}
		
		def identicalData = []
		Iterable fileDataIds = versionInstance.files*.fileDataId
		def duplicateIds = fileDataIds.countBy{it}.grep{it.value > 1}.collect{it.key}
		duplicateIds.each { duplicateId ->
			identicalData.add(versionFiles.findAll { it.fileDataId == duplicateId })
		}
		
		render (view: '/dataset/issues', model: [datasetInstance: datasetInstance, versionInstance: versionInstance, warnings:warnings, lacksSpeciesTag: lacksSpeciesTag, missingFiles: missingFiles, notUsed: notUsed, identicalPaths: identicalPaths, identicalData: identicalData])
	}
	
	@Action
	@Transactional
	@Secured("ROLE_CURATOR")
	def createIssue() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def issue = new Issue()
		issue.dataset = datasetInstance
		issue.title = params.title
		issue.createdBy = userService.current.username
		issue.status = params.newStatus as Issue.Status
		issue.versionId = datasetInstance.submittedVersion().id
		
		def comment = new IssueComment()
		comment.comment = params.comment
		comment.createdBy = userService.current.username
		
		issue.addToComments(comment)
		issue.save(flush:true)
		
		redirect action: 'issues', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def updateIssue() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def issue = Issue.get(params.issueId)
		if (notFound(issue)) return
		
		if (params.newStatus) {
			issue.status = params.newStatus as Issue.Status
		}
		
		def comment = new IssueComment()
		comment.comment = params.newComment
		comment.createdBy = userService.current.username
		
		issue.addToComments(comment)
		issue.save(flush:true)
		
		redirect action: 'issues', id: datasetInstance.id
	}
	
	@Action
	@Transactional
	@Secured("ROLE_CURATOR")
	def deleteIssue() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		def issue = Issue.get(params.issueId)
		if (notFound(issue)) return

		issue.delete(flush:true)
		
		redirect action: 'issues', id: datasetInstance.id
	}
	
	@Action
	@Secured("(@balsaSecurityService.canView(#this, 'dataset') || @balsaSecurityService.isPublic(#this, 'dataset'))")
	def extractdir() {
		Dataset datasetInstance = Dataset.get(params.id)
		if (notFound(datasetInstance) || wrongType(datasetInstance)) return
		
		render datasetInstance.extract ?: datasetInstance.id
	}
	
	protected def wrongType(Dataset datasetInstance) {
		if (!getDatasetType().isInstance(datasetInstance)) {
			render view: '/404', status: NOT_FOUND
		}
		!getDatasetType().isInstance(datasetInstance)
	}
}
