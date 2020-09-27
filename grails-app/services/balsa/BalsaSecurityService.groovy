package balsa

import grails.transaction.Transactional
import grails.util.Holders

import org.codehaus.groovy.grails.web.mapping.UrlMappingInfo
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot

import balsa.file.FileMetadata
import balsa.scene.Scene
import balsa.scene.SceneLine
import balsa.security.Approval

@Transactional
class BalsaSecurityService {
	def springSecurityService
	
	boolean canEdit(WebSecurityExpressionRoot webSecurityExpressionRoot, String objectType) {
		def object = getObject(webSecurityExpressionRoot, objectType)
		object?.canEdit()
	}
	
	boolean canView(WebSecurityExpressionRoot webSecurityExpressionRoot, String objectType) {
		def object = getObject(webSecurityExpressionRoot, objectType)
		object?.canView()
	}
	
	boolean isPublic(WebSecurityExpressionRoot webSecurityExpressionRoot, String objectType) {
		def object = getObject(webSecurityExpressionRoot, objectType)
		object?.isPublic(getObjectVersion(webSecurityExpressionRoot))
	}
	
	boolean hasAccess(WebSecurityExpressionRoot webSecurityExpressionRoot, String objectType) {
		def object = getObject(webSecurityExpressionRoot, objectType)
		object?.hasAccess()
	}
	
	boolean isApprovalOwner(WebSecurityExpressionRoot webSecurityExpressionRoot) {
		def objectId = getObjectId(webSecurityExpressionRoot)
		Approval approval = Approval.get(objectId)
		approval?.owners?.contains(springSecurityService.currentUser)
	}
	
	boolean isOwnProfile(WebSecurityExpressionRoot webSecurityExpressionRoot) {
		def profileId = getObjectId(webSecurityExpressionRoot)
		Profile profile = Profile.get(profileId)
		profile?.user == springSecurityService.currentUser
	}
	
	def getObject(WebSecurityExpressionRoot webSecurityExpressionRoot, String objectType) {
		def objectId = getObjectId(webSecurityExpressionRoot)
		switch(objectType) {
			case 'dataset':
				return Dataset.get(objectId)
			case 'file':
				return FileMetadata.get(objectId)
			case 'scene':
				return Scene.get(objectId)
			case 'sceneLine':
				return SceneLine.get(objectId)
			case 'version':
				return Version.get(objectId)
			default:
				return null
		}
	}
	
	def getObjectVersion(WebSecurityExpressionRoot webSecurityExpressionRoot) {
		def param = new GrailsParameterMap(webSecurityExpressionRoot.request)
		param?.version
	}
	
	def getObjectId(WebSecurityExpressionRoot webSecurityExpressionRoot) {
		def reqURI = webSecurityExpressionRoot.request.forwardURI
		def matcher = reqURI =~ ".*/([bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890]+)/?\\??.*"
		if (matcher.matches()) return matcher[0][1]
//		UrlMappingInfo urlMappingInfo = Holders.applicationContext.grailsUrlMappingsHolder.match(webSecurityExpressionRoot.request.forwardURI.substring(webSecurityExpressionRoot.request.contextPath.length()))
//		urlMappingInfo.params.id
	}
}
