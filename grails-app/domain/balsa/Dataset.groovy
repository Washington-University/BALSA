package balsa

import grails.plugin.springsecurity.SpringSecurityUtils
import net.kaleidos.hibernate.usertype.ArrayType
import balsa.curation.Issue
import balsa.file.Documentation
import balsa.file.FileMetadata
import balsa.file.SceneFile
import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.security.Approval
import balsa.security.BalsaUser
import balsa.security.Terms


class Dataset {
	def springSecurityService

	String id
	String title
	String shortTitle
	@Deprecated
	String description
	String extract
	@Deprecated
	String[] sceneFileOrder = []
	@Deprecated
	Status status = Status.EDITABLE
	SceneLine focusScene
	Date createdDate = new Date()
	Date publicDate
	BalsaUser curator
	String notes
	String readNotes
	String customTermsTitle
	String customTermsContent

	static hasMany = [versions: Version, files: FileMetadata, downloads: Download, sceneLines: SceneLine, linkedScenes: SceneLine, species: Species, accessAgreements: Terms, restrictedAccessTerms: Approval, issues: Issue]
	static mappedBy = [linkedScenes: "linkedDatasets"]

	static constraints = {
		title size: 5..200, blank: false
		shortTitle size: 5..100, blank: true, nullable: true
		description nullable: true, blank: true
		extract size: 0..50, blank: true, nullable: true
		focusScene nullable: true
		sceneFileOrder nullable: true
		curator nullable: true
		notes nullable: true, blank: true
		readNotes nullable: true, blank: true
		customTermsTitle blank: true, nullable: true
		customTermsContent blank: true, nullable: true
		publicDate nullable: true
	}
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		title type: "text", index: 'dataset_title_index'
		shortTitle type: "text", index: 'dataset_shorttitle_index'
		description type: "text"
		extract type: "text"
		sceneFileOrder type:ArrayType, params: [type: String]
		notes type: "text"
		readNotes type: "text"
		customTermsTitle type: "text"
		customTermsContent type: "text"
	}

	@Deprecated
	Set<FileMetadata> currentFiles() {
		files.findAll({ it.removed == null }).toSet()
	}
	
	Version getVersion(String versionInfo) {
		if (!versionInfo) return defaultVersion()
		if (versionInfo.equalsIgnoreCase('public')) return publicVersion()
		if (versionInfo.equalsIgnoreCase('preprint')) return preprintVersion()
		if (versionInfo.equalsIgnoreCase('working')) return workingVersion()
		if (versionInfo.equalsIgnoreCase('submitted')) return submittedVersion()
		if (versionInfo.equalsIgnoreCase('approved')) return approvedVersion()
		versions.find { it.id == versionInfo }
	}
	
	Version publicVersion() {
		versions.find { it.isPublic() && !it.preprint }
	}
	
	Version preprintVersion() {
		versions.find { it.isPublic() && it.preprint }
	}
	
	Version workingVersion() {
		def mostRecentDate = (versions*.updatedDate)?.max()
		versions.find { it.updatedDate == mostRecentDate }
	}
	
	Version submittedVersion() {
		versions.find { it.isSubmitted() }
	}
	
	Version approvedVersion() {
		versions.find { it.isApproved() }
	}
	
	Version defaultVersion() {
		if (canView()) {
			if (SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')) (submittedVersion() ?: approvedVersion()) ?: workingVersion()
			else workingVersion()
		}
		else {
			publicVersion() ?: preprintVersion()
		}
	}

	@Deprecated
	enum Status {
		EDITABLE, SUBMITTED, APPROVED, PUBLIC, EMERGENCY
	}
	
	boolean isPublic(versionInfo = null) {
		if (versionInfo) {
			getVersion(versionInfo)?.isPublic()
		}
		else {
			publicVersion() || preprintVersion()
		}
	}

	boolean canEdit() {
		SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')
	}

	boolean canView() {
		SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')
	}

	boolean hasAccess() {
		hasAgreedToTerms(springSecurityService.currentUser) && isApprovedForAccess(springSecurityService.currentUser)
	}

	boolean hasAgreedToTerms(BalsaUser user) {
		if (accessAgreements == null || accessAgreements.isEmpty()) return true
		user?.agreedTerms?.containsAll(accessAgreements)
	}

	boolean isApprovedForAccess(BalsaUser user) {
		if (restrictedAccessTerms == null || restrictedAccessTerms.isEmpty()) return true
		user?.grantedApprovals.containsAll(restrictedAccessTerms)
	}

	def terms() {
		def numberOfTerms = accessAgreements.size() + restrictedAccessTerms.size() + (customTermsTitle && customTermsContent ? 1 : 0)
		def agreed = accessAgreements.findAll { springSecurityService.currentUser?.agreedTerms?.contains(it) }
		def notAgreed = accessAgreements.findAll { !springSecurityService.currentUser?.agreedTerms?.contains(it) }
		def approved = accessAgreements.findAll { springSecurityService.currentUser?.grantedApprovals?.contains(it) }
		def notApproved = accessAgreements.findAll { !springSecurityService.currentUser?.grantedApprovals?.contains(it) }

		[numberOfTerms: numberOfTerms, agreed: agreed, notAgreed: notAgreed, approved: approved, notApproved: notApproved, customTermsTitle: customTermsTitle, customTermsContent: customTermsContent, datasetId: id]
	}

	boolean hasReadNotes() {
		!notes || readNotes?.contains(springSecurityService.currentUser?.username)
	}

	def datasetId() {
		this.id
	}

	boolean isReadyForSubmission() {
		for (issue in issues) {
			if (issue.status == Issue.Status.PROBLEM) return false
		}
		true
	}

	boolean isReadyForApproval() {
		for (issue in issues) {
			if (issue.status != Issue.Status.RESOLVED && issue.status != Issue.Status.SUGGESTION) return false
		}
		true
	}
	
	def maxVersionNo() {
		(versions*.versionNo).max()
	}
}
