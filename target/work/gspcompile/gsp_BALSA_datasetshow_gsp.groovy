import balsa.security.BalsaUser
import balsa.Dataset
import balsa.Study
import balsa.curation.Issue
import balsa.Version
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_datasetshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(datasetInstance.title)
})
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('datasetTermUppercase','g',15,['item':(datasetInstance)],-1)
printHtmlPart(5)
expressionOut.print(datasetInstance.shortTitle ?: datasetInstance.title)
printHtmlPart(6)
invokeTag('render','g',17,['template':("/templates/terms"),'bean':(datasetInstance.terms()),'var':("terms")],-1)
printHtmlPart(7)
invokeTag('render','g',18,['template':("/download/downloadModal"),'bean':(datasetInstance),'var':("item")],-1)
printHtmlPart(7)
invokeTag('render','g',19,['template':("/templates/fileModal"),'model':(['files':versionInstance.files, 'datasetInstance': datasetInstance, 'versionInstance': versionInstance])],-1)
printHtmlPart(7)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(8)
invokeTag('render','g',21,['template':("/templates/notesModal"),'bean':(datasetInstance),'var':("dataset")],-1)
printHtmlPart(8)
invokeTag('render','g',22,['template':("/templates/downloadStatsModal"),'model':(['dataset':datasetInstance])],-1)
printHtmlPart(7)
}
printHtmlPart(9)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('link','g',30,['class':("btn btn-default canedit"),'action':("edit"),'id':(datasetInstance.id),'params':([version: versionInstance.id])],3)
printHtmlPart(12)
createClosureForHtmlPart(13, 3)
invokeTag('link','g',31,['class':("btn btn-default canedit"),'action':("editScenes"),'id':(datasetInstance.id),'params':([version: versionInstance.id])],3)
printHtmlPart(12)
if(true && (!versionInstance.isPublic())) {
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(15)
expressionOut.print(datasetInstance.issues?.count({ it.status != Issue.Status.RESOLVED }))
})
invokeTag('link','g',33,['class':("btn btn-default canedit"),'action':("issues"),'id':(datasetInstance.id)],4)
printHtmlPart(12)
}
printHtmlPart(16)
if(true && (!datasetInstance.hasReadNotes())) {
printHtmlPart(17)
}
printHtmlPart(18)
}
printHtmlPart(8)
if(true && (datasetInstance?.canView() || versionInstance?.isPublic())) {
printHtmlPart(12)
if(true && (datasetInstance?.hasAccess())) {
printHtmlPart(19)
}
else {
printHtmlPart(14)
createClosureForHtmlPart(20, 4)
invokeTag('ifLoggedIn','sec',47,[:],4)
printHtmlPart(14)
createClosureForHtmlPart(21, 4)
invokeTag('ifNotLoggedIn','sec',50,[:],4)
printHtmlPart(12)
}
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (datasetInstance?.terms()?.numberOfTerms > 0)) {
printHtmlPart(22)
}
printHtmlPart(23)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(24)
invokeTag('render','g',60,['template':("/templates/submissionButtons"),'bean':(versionInstance),'var':("versionInstance")],-1)
printHtmlPart(25)
invokeTag('createLink','g',62,['action':("delete"),'id':(versionInstance.dataset.id)],-1)
printHtmlPart(26)
}
printHtmlPart(27)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(28)
if(true && (versionInstance.sceneFiles().size() > 0)) {
printHtmlPart(8)
invokeTag('render','g',71,['template':("/templates/carouselWithThumbnails"),'model':(['datasetVersion': versionInstance])],-1)
printHtmlPart(8)
}
printHtmlPart(29)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(30)
if(true && (versionInstance == datasetInstance.workingVersion() && versionInstance.isNonpublic())) {
printHtmlPart(31)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (versionInstance != datasetInstance.workingVersion() && versionInstance.isNonpublic())) {
printHtmlPart(34)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(35)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',81,['action':("setWorkingVersion"),'id':(datasetInstance.id),'params':([version: versionInstance.id])],4)
printHtmlPart(12)
}
printHtmlPart(33)
if(true && (versionInstance == datasetInstance.publicVersion())) {
printHtmlPart(31)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(37)
}
printHtmlPart(33)
if(true && (versionInstance == datasetInstance.preprintVersion())) {
printHtmlPart(38)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(37)
}
printHtmlPart(33)
if(true && (versionInstance == datasetInstance.submittedVersion())) {
printHtmlPart(31)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(39)
}
printHtmlPart(33)
if(true && (versionInstance == datasetInstance.approvedVersion())) {
printHtmlPart(31)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(40)
if(true && (versionInstance.actualReleaseDate())) {
printHtmlPart(41)
expressionOut.print(versionInstance.actualReleaseDate().format("yyyy-MM-dd h:mm a z"))
printHtmlPart(42)
}
else {
printHtmlPart(43)
createClosureForHtmlPart(11, 5)
invokeTag('link','g',102,['action':("edit"),'id':(datasetInstance.id)],5)
printHtmlPart(44)
}
printHtmlPart(12)
}
printHtmlPart(33)
if(true && (datasetInstance.publicVersion() && versionInstance != datasetInstance.publicVersion())) {
printHtmlPart(45)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(46)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',107,['action':("show"),'id':(datasetInstance.id),'params':([version: 'public'])],4)
printHtmlPart(42)
}
printHtmlPart(12)
if(true && (datasetInstance.preprintVersion() && versionInstance != datasetInstance.preprintVersion())) {
printHtmlPart(47)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(46)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',110,['action':("show"),'id':(datasetInstance.id),'params':([version: 'preprint'])],4)
printHtmlPart(42)
}
printHtmlPart(12)
if(true && (datasetInstance.submittedVersion() && versionInstance != datasetInstance.submittedVersion())) {
printHtmlPart(45)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(48)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',113,['action':("show"),'id':(datasetInstance.id),'params':([version: 'submitted'])],4)
printHtmlPart(42)
}
printHtmlPart(12)
if(true && (datasetInstance.approvedVersion() && versionInstance != datasetInstance.approvedVersion())) {
printHtmlPart(45)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(49)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',116,['action':("show"),'id':(datasetInstance.id),'params':([version: 'approved'])],4)
printHtmlPart(42)
}
printHtmlPart(12)
if(true && (datasetInstance.workingVersion() && versionInstance != datasetInstance.workingVersion())) {
printHtmlPart(50)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(51)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',119,['action':("show"),'id':(datasetInstance.id),'params':([version: 'working'])],4)
printHtmlPart(42)
}
printHtmlPart(52)
for( version in (datasetInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}) ) {
printHtmlPart(53)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(54)
expressionOut.print(datasetInstance.id)
printHtmlPart(55)
expressionOut.print(version.id)
printHtmlPart(56)
expressionOut.print(version.id)
printHtmlPart(57)
expressionOut.print(version.status)
printHtmlPart(57)
expressionOut.print(version.updatedDate)
printHtmlPart(58)
}
printHtmlPart(59)
invokeTag('fieldValue','g',140,['bean':(datasetInstance),'field':("id")],-1)
printHtmlPart(60)
}
printHtmlPart(29)
if(true && (versionInstance.isPublic() && !versionInstance.preprint && datasetInstance.preprintVersion())) {
printHtmlPart(61)
createClosureForHtmlPart(36, 3)
invokeTag('link','g',146,['action':("show"),'id':(datasetInstance.id),'params':([version: 'preprint'])],3)
printHtmlPart(62)
}
printHtmlPart(8)
if(true && (versionInstance.isPublic() && versionInstance.preprint && datasetInstance.publicVersion())) {
printHtmlPart(63)
createClosureForHtmlPart(36, 3)
invokeTag('link','g',151,['action':("show"),'id':(datasetInstance.id)],3)
printHtmlPart(62)
}
printHtmlPart(64)
expressionOut.print(datasetInstance.title)
printHtmlPart(65)
if(true && (datasetInstance?.species)) {
printHtmlPart(66)
invokeTag('join','g',163,['in':(datasetInstance.species.name),'delimiter':(", ")],-1)
printHtmlPart(60)
}
printHtmlPart(29)
if(true && (versionInstance?.description)) {
printHtmlPart(67)
createTagBody(3, {->
invokeTag('fieldValue','g',170,['bean':(versionInstance),'field':("description")],-1)
})
invokeTag('encodeAs','g',170,['codec':("PreserveWhitespace")],3)
printHtmlPart(60)
}
printHtmlPart(29)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(8)
if(true && (versionInstance?.studyAbstract)) {
printHtmlPart(68)
expressionOut.print(versionInstance?.studyAbstract)
printHtmlPart(69)
}
printHtmlPart(29)
if(true && (versionInstance?.publication)) {
printHtmlPart(70)
expressionOut.print(versionInstance?.publication?.officialName.encodeAsHTML())
printHtmlPart(71)
if(true && (versionInstance?.doi)) {
printHtmlPart(72)
expressionOut.print(versionInstance.doi)
printHtmlPart(73)
invokeTag('fieldValue','g',189,['bean':(versionInstance),'field':("doi")],-1)
printHtmlPart(74)
}
printHtmlPart(12)
if(true && (versionInstance?.pmid)) {
printHtmlPart(75)
expressionOut.print(versionInstance.pmid)
printHtmlPart(73)
invokeTag('fieldValue','g',193,['bean':(versionInstance),'field':("pmid")],-1)
printHtmlPart(74)
}
printHtmlPart(60)
}
printHtmlPart(29)
if(true && (versionInstance?.authors)) {
printHtmlPart(76)
for( _it1903379555 in (versionInstance.authors) ) {
changeItVariable(_it1903379555)
printHtmlPart(77)
expressionOut.print(it)
printHtmlPart(78)
}
printHtmlPart(79)
}
printHtmlPart(29)
if(true && (versionInstance?.institutions)) {
printHtmlPart(80)
for( _it1058892307 in (versionInstance.institutions) ) {
changeItVariable(_it1058892307)
printHtmlPart(77)
expressionOut.print(it.canonicalName)
printHtmlPart(78)
}
printHtmlPart(79)
}
printHtmlPart(8)
}
printHtmlPart(29)
if(true && (versionInstance.sceneFiles().size() > 0)) {
printHtmlPart(81)
invokeTag('render','g',224,['template':("/templates/sceneList"),'model':(['sceneFiles': versionInstance.sceneFiles()])],-1)
printHtmlPart(82)
}
printHtmlPart(29)
if(true && (versionInstance.linkedScenes.size() > 0)) {
printHtmlPart(83)
invokeTag('render','g',231,['template':("/templates/linkedSceneList"),'model':(['currentDatasetVersion': versionInstance])],-1)
printHtmlPart(82)
}
printHtmlPart(84)
createTagBody(2, {->
printHtmlPart(85)
expressionOut.print(BalsaUser.get(sec.loggedInUserInfo(field:"id"))?.profile?.emailAddress)
printHtmlPart(86)
})
invokeTag('formRemote','g',264,['name':("contactCuratorsForm"),'class':("form-horizontal"),'style':("padding-right:15px;padding-left:15px"),'url':([controller: datasetTerm('item':datasetInstance), action: 'messageCurator', id: datasetInstance.id]),'method':("POST"),'onSuccess':("curatorSendingSuccess();updateRecentActivity();")],2)
printHtmlPart(87)
})
invokeTag('captureBody','sitemesh',281,[:],1)
printHtmlPart(88)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1600210900291L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
