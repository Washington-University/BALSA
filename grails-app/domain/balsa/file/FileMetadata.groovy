package balsa.file

import grails.plugin.springsecurity.SpringSecurityUtils
import groovy.transform.EqualsAndHashCode
import net.kaleidos.hibernate.usertype.ArrayType
import balsa.Dataset
import balsa.Download
import balsa.TagScanner
import balsa.Version
import balsa.security.BalsaUser
import net.kaleidos.hibernate.usertype.JsonMapType

@EqualsAndHashCode
class FileMetadata {
	String id
	Dataset dataset
	String filepath
	String filename
	String createdBy
	Date added
	Date removed
	String fileDataId
	String[] tags = []
	long filesize
	long zipsize
	Date processed
	Map fileInfo
	
	static belongsTo = [Dataset, Version]
	static hasMany = [versions: Version, downloads: Download]
    static constraints = {
		removed nullable: true
		processed nullable: true
		fileInfo nullable: true, blank: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
		dataset lazy: false
		filename type: "text", index: 'file_name_index'
		filepath type: "text", index: 'file_path_index'
		createdBy type: "text"
		tags type:ArrayType, params: [type: String]
		fileDataId index: 'file_data_index'
		downloads joinTable: [name: 'file_metadata_download', key: 'file_metadata_downloads_id']
		fileInfo type: JsonMapType
	}
	
	def setValuesFromFile(InputStream input) {}
	
	def scanForTags() {
		def returnList = [] as Set
		returnList.addAll(TagScanner.scan(filepath))
		returnList.toArray()
	}
	
	XmlSlurper xmlSlurper() {
		def parser=new XmlSlurper()
		parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
		parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
		parser
	}
	
	boolean isPublic(versionInfo = null) {
		if (versionInfo) {
			def datVersion = dataset.getVersion(versionInfo)
			versions.contains(datVersion) && datVersion?.isPublic()
		}
		else {
			versions.find { it.isPublic() }
		}
	}
	
	boolean canEdit() {
		dataset.canEdit()
	}
	
	boolean canView() {
		dataset.canView()
	}
	
	boolean hasAccess() {
		dataset.hasAccess()
	}
	
	def terms() {
		dataset.terms()
	}
	
	def datasetId() {
		dataset.id
	}
	
	boolean isDependentFile(versionInfo = null) {
		getVersion(versionInfo)?.isDependentFile(this)
	}
	
	Version getVersion(String versionInfo) {
		dataset.getVersion(versionInfo)
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
			if (SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')) submittedVersion() ?: workingVersion()
			else workingVersion()
		}
		else {
			publicVersion() ?: preprintVersion()
		}
	}
	
	Date lastUsed() {
		workingVersion().updatedDate
	}
	
	def allVersions() {
		dataset.files.findAll( { it.filepath == this.filepath } ).sort s{ it.lastUsed() } 
	}
	
	public int hashCode() {
		id ? id.hashCode() : filepath.hashCode()
	}
}
