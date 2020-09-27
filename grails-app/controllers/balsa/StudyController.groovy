package balsa

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import org.apache.commons.lang.ArrayUtils

import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.scene.SceneLine
import balsa.security.BalsaUser
import balsa.security.Terms


@Transactional(readOnly = true)
@Secured("ROLE_SUBMITTER")
class StudyController extends AbstractDatasetController {
	
//	MailStrategy uiMailStrategy
	
	Class getDatasetType() {
		Study.class
	}
	
	def mine() {
		if (params.type == 'json') {
			render springSecurityService.currentUser.studies as JSON
			return
		}
		
		[studyInstanceList: springSecurityService.currentUser.studies]
	}

	@Transactional
	def save(Study studyInstance) {
		if (notFound(studyInstance)) return
		
		studyInstance.addToVersions(new Version())
		
		studyInstance.addToOwners(springSecurityService.currentUser)

		studyInstance.save flush:true
		
		if (params.type == 'json') {
			render studyInstance as JSON
			return
		}

		redirect action: 'edit', id: studyInstance.id
	}
	
	@Transactional
	def create(Study studyInstance) {
		if (notFound(studyInstance)) return
		
		studyInstance.addToVersions(new Version())
		
		studyInstance.addToOwners(springSecurityService.currentUser)

		studyInstance.save flush:true
		
		if (params.type == 'json') {
			render studyInstance as JSON
			return
		}

		redirect action: 'edit', id: studyInstance.id
	}
	
	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def update() {
		Study studyInstance = Study.get(params.id)
		if (notFound(studyInstance)) return
		
		Version versionInstance = studyInstance.getVersion(params.version)
		
		// Dataset
		studyInstance.title = params.title
		studyInstance.shortTitle = params.shortTitle
		studyInstance.extract = params.extract
		studyInstance.customTermsTitle = params.customTermsTitle
		studyInstance.customTermsContent = params.customTermsContent
		
		studyInstance.species.collect().each { studyInstance.removeFromSpecies(it) }
		List speciesList = ensureList(params.species)
		speciesList.each { speciesId ->
			studyInstance.addToSpecies(Species.get(speciesId))
		}
		
		studyInstance.accessAgreements.collect().each { studyInstance.removeFromAccessAgreements(it) }
		List accessAgreementsList = ensureList(params.accessAgreements)
		accessAgreementsList.each { agreementId ->
			studyInstance.addToAccessAgreements(Terms.get(agreementId))
		}
		
		studyInstance.owners.collect().each { studyInstance.removeFromOwners(it) }
		def ownerNames = params.ownerNames.split("\r\n")
		ownerNames.each { ownerName ->
			if (ownerName != "") {
				def owner = BalsaUser.findByUsername(ownerName)
				studyInstance.addToOwners(owner)
			}
		}
		studyInstance.viewers.collect().each { studyInstance.removeFromViewers(it) }
		def viewerNames = params.viewerNames.split("\r\n")
		viewerNames.each { viewerName ->
			if (viewerName != "") {
				def viewer = BalsaUser.findByUsername(viewerName)
				studyInstance.addToViewers(viewer)
			}
		}
		
		// Version
		versionInstance.updatedDate = new Date()
		versionInstance.description = params.description
		versionInstance.comments = params.comments
		versionInstance.focusScene = SceneLine.get(params.focusScene)?.dataset == studyInstance ? SceneLine.get(params.focusScene) : null
		versionInstance.sceneFileOrder = ArrayUtils.removeElement(params.sceneFileOrder.split(","), "")
		
		versionInstance.studyAbstract = params.studyAbstract
		versionInstance.doi = params.doi
		versionInstance.pmid = params.pmid
		versionInstance.preprint = params.preprint == 'on'
		versionInstance.publicationTitle = params.publicationTitle
		versionInstance.preprintDate = params.preprintDate ? new Date().parse("MM/dd/yyyy h:mm a", params.preprintDate) : null
		versionInstance.epubDate = params.epubDate ? new Date().parse("MM/dd/yyyy h:mm a", params.epubDate) : null
		versionInstance.journalDate = params.journalDate ? new Date().parse("MM/dd/yyyy h:mm a", params.journalDate) : null
		versionInstance.releaseDate = params.releaseDate ? new Date().parse("MM/dd/yyyy h:mm a", params.releaseDate) : null
		versionInstance.dateRedirect = params.dateRedirect ?: null
		versionInstance.authors = ArrayUtils.removeElement(params.authors.split("\r\n"), "")
		if (versionInstance.doi) {
			versionInstance.doi = versionInstance.doi.replaceAll(~/.*doi\.org\//,'')
		}
		versionInstance.institutions.collect().each { versionInstance.removeFromInstitutions(it) }
		def institutionsList = params.institutionsList.split("\r\n")
		institutionsList.each { institutionName ->
			if (institutionName) {
				def institution = Institution.findByCanonicalName(institutionName)
				if (!institution) {
					institution = new Institution()
					institution.canonicalName = institutionName
					institution.save flush: true
				}
				versionInstance.addToInstitutions(institution)
			}
		}
		def publication = Publication.findByOfficialName(params.publicationName)
		if (!publication && params.publicationName) {
			publication = new Publication()
			publication.officialName = params.publicationName
			publication.save flush: true
		}
		versionInstance.publication = publication
		
		render view: '/dataset/saveSuccess'
	}

	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def delete(Study studyInstance) {
		if (notFound(studyInstance)) return

		def owners = studyInstance.owners as BalsaUser[]
		for (owner in owners) {
			studyInstance.removeFromOwners(owner)
		}
		studyInstance.delete flush:true

		redirect action: 'mine'
	}
	
	@Secured(['permitAll'])
	def uploadStatus(Study studyInstance) {
		if (notFound(studyInstance)) return
		
		def returnValue = [status: 'EDITABLE']
		def canUpload = true
		def problems = []
		
		if (!springSecurityService.currentUser) {
			problems.add("The user is not currently logged in.")
			canUpload = false
		}
		else if (!studyInstance.canEdit()) {
			problems.add("The user is not permitted to edit this study.")
			canUpload = false
		}
		returnValue.put('problems', problems)
		returnValue.put('canUpload', canUpload)
		
		render returnValue as JSON
	}
	
	@Secured(['permitAll'])
	def getPubMedData() {
		def xmlSlurper = new XmlSlurper()
		// two features need to be set because the pubmed xml has a doctype at the beginning
		xmlSlurper.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
		xmlSlurper.setFeature('http://apache.org/xml/features/nonvalidating/load-external-dtd', false)
		def xml
		try {
			xml = xmlSlurper.parse('https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&format=xml&id=' + params.id)
		}
		catch (Exception e) {
			def returnData = ['errorMessage':'BALSA encountered an error attempting to read data from PubMed.']
			render returnData as JSON
		}
		
		if (xml?.PubmedArticle) {
			def journalName = xml.PubmedArticle.MedlineCitation?.Article?.Journal?.Title?.text()
			def journal = Publication.findByOfficialName(journalName)
			if (!journal && journalName) {
				journal = new Publication()
				journal.officialName = journalName
				journal.save flush: true
			}
			
			def authorlist = []
			for (author in xml.PubmedArticle.MedlineCitation?.Article?.AuthorList?.Author) {
				def authorname = author.ForeName.text() + ' ' + author.LastName.text()
				authorlist.add(authorname)
			}
			
			def studyAbstract = xml.PubmedArticle.MedlineCitation?.Article?.Abstract?.AbstractText?.text()
			
			def doi = xml.PubmedArticle.PubmedData?.ArticleIdList?.ArticleId?.find({it.@IdType == 'doi'})?.text()
			
			def returnData = ['doi':doi,'journalName':journalName,'authorlist':authorlist,'studyAbstract':studyAbstract]
			render returnData as JSON
		}
		else {
			def returnData = ['errorMessage':'Data could not be found in PubMed matching the specified PMID.']
			render returnData as JSON
		}
	}

	List ensureList(data) {
		if(!data) return []
		if(data.getClass().isArray()) return data
		[data]
	}
}
	
//	protected def emailOwners(Dataset datasetInstance, String message) {
//		datasetInstance.owners.each { owner ->
//			def emailAddress = Profile.findByUser(owner)?.emailAddress
//			if (emailAddress) {
//				uiMailStrategy.sendVerifyRegistrationMail(
//					to: emailAddress,
//					from: 'noreply@balsa.wustl.edu',
//					subject: 'Study status change',
//					html: '<p>Your <a href="http://balsa.wustl.edu">BALSA</a> username is <b>' + user.username + '</b></p>.')
//			}
//		}
//	}
