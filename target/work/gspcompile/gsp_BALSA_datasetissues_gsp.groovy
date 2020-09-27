import balsa.Dataset
import balsa.curation.Issue
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_datasetissues_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/issues.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(datasetInstance.shortTitle ?: datasetInstance.title)
printHtmlPart(3)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
printHtmlPart(6)
expressionOut.print(datasetInstance.shortTitle ?: datasetInstance.title)
printHtmlPart(7)
invokeTag('render','g',13,['template':("/templates/notesModal")],-1)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',16,['class':("btn btn-default"),'action':("show"),'id':(datasetInstance.id)],2)
printHtmlPart(10)
if(true && (!datasetInstance.hasReadNotes())) {
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
if(true && (versionInstance.isSubmitted())) {
printHtmlPart(14)
}
printHtmlPart(15)
expressionOut.print((datasetInstance.owners*.profile*.emailAddress).minus(null).join(','))
printHtmlPart(16)
createClosureForHtmlPart(17, 3)
invokeTag('link','g',25,['class':("btn btn-default"),'controller':("curation")],3)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',29,['controller':("institution")],3)
printHtmlPart(20)
createClosureForHtmlPart(21, 3)
invokeTag('link','g',30,['controller':("publication")],3)
printHtmlPart(22)
})
invokeTag('ifAnyGranted','sec',33,['roles':("ROLE_CURATOR,ROLE_ADMIN")],2)
printHtmlPart(13)
invokeTag('render','g',34,['template':("/templates/submissionButtons"),'bean':(versionInstance),'var':("item")],-1)
printHtmlPart(23)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(24)
if(true && (warnings || lacksSpeciesTag || missingFiles || notUsed || identicalPaths || identicalData)) {
printHtmlPart(25)
for( _it1875021403 in (warnings) ) {
changeItVariable(_it1875021403)
printHtmlPart(26)
expressionOut.print(it)
printHtmlPart(27)
}
printHtmlPart(28)
if(true && (lacksSpeciesTag)) {
printHtmlPart(29)
for( _it1664659426 in (lacksSpeciesTag) ) {
changeItVariable(_it1664659426)
printHtmlPart(30)
createTagBody(5, {->
expressionOut.print(it.shortName ?: it.name)
})
invokeTag('link','g',51,['controller':("scene"),'action':("show"),'id':(it.sceneLine.id)],5)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(28)
if(true && (missingFiles)) {
printHtmlPart(33)
for( _it477655945 in (missingFiles) ) {
changeItVariable(_it477655945)
printHtmlPart(30)
expressionOut.print(it)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(28)
if(true && (notUsed)) {
printHtmlPart(34)
for( _it1739822603 in (notUsed) ) {
changeItVariable(_it1739822603)
printHtmlPart(30)
createTagBody(5, {->
expressionOut.print(it.filepath)
})
invokeTag('link','g',73,['controller':("file"),'action':("show"),'id':(it.id)],5)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(28)
if(true && (identicalPaths)) {
printHtmlPart(35)
for( _it1095918240 in (identicalPaths) ) {
changeItVariable(_it1095918240)
printHtmlPart(30)
createTagBody(5, {->
expressionOut.print(it.filepath)
})
invokeTag('link','g',84,['controller':("file"),'action':("show"),'id':(it.id)],5)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(28)
for( identicalDataSet in (identicalData) ) {
printHtmlPart(36)
for( _it432142068 in (identicalDataSet) ) {
changeItVariable(_it432142068)
printHtmlPart(30)
createTagBody(5, {->
expressionOut.print(it.filepath)
})
invokeTag('link','g',95,['controller':("file"),'action':("show"),'id':(it.id)],5)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(37)
}
printHtmlPart(38)
createTagBody(2, {->
printHtmlPart(39)
invokeTag('field','g',116,['type':("text"),'class':("form-control"),'name':("title"),'required':("required")],-1)
printHtmlPart(40)
invokeTag('select','g',120,['class':("form-control"),'name':("newStatus"),'from':(['PROBLEM', 'SUGGESTION'])],-1)
printHtmlPart(41)
invokeTag('textArea','g',125,['class':("form-control"),'name':("comment"),'rows':("10"),'cols':("40"),'required':("required")],-1)
printHtmlPart(42)
})
invokeTag('form','g',134,['name':("createIssueForm"),'action':("createIssue"),'id':(datasetInstance.id)],2)
printHtmlPart(43)
if(true && (!datasetInstance.issues)) {
printHtmlPart(44)
}
printHtmlPart(45)
for( issue in (datasetInstance.issues) ) {
printHtmlPart(46)
expressionOut.print(issue.title)
printHtmlPart(47)
expressionOut.print(issue.status)
printHtmlPart(48)
createTagBody(3, {->
expressionOut.print(issue.versionId)
})
invokeTag('link','g',156,['controller':(datasetTerm('item':datasetInstance)),'action':("show"),'id':(datasetInstance.id),'params':("version: issue.versionId")],3)
printHtmlPart(49)
for( comment in (issue.comments?.take(1)) ) {
printHtmlPart(50)
invokeTag('formatDate','g',162,['format':("yyyy-MM-dd HH:mm"),'date':(comment.createdDate)],-1)
printHtmlPart(51)
expressionOut.print(comment.createdBy)
printHtmlPart(6)
expressionOut.print(comment.comment)
printHtmlPart(52)
}
printHtmlPart(53)
if(true && (issue.comments?.size() > 1)) {
printHtmlPart(54)
for( comment in (issue.comments.drop(1)) ) {
printHtmlPart(55)
invokeTag('formatDate','g',172,['format':("yyyy-MM-dd HH:mm"),'date':(comment.createdDate)],-1)
printHtmlPart(51)
expressionOut.print(comment.createdBy)
printHtmlPart(6)
expressionOut.print(comment.comment)
printHtmlPart(56)
}
printHtmlPart(57)
}
printHtmlPart(58)
expressionOut.print(issue.id)
printHtmlPart(59)
expressionOut.print(issue.id)
printHtmlPart(60)
invokeTag('createLink','g',182,['action':("deleteIssue"),'id':(datasetInstance.id),'params':([issueId:issue.id])],-1)
printHtmlPart(61)
expressionOut.print(issue.id)
printHtmlPart(62)
createTagBody(3, {->
printHtmlPart(63)
invokeTag('hiddenField','g',198,['name':("issueId"),'value':(issue.id)],-1)
printHtmlPart(64)
invokeTag('textArea','g',202,['class':("form-control"),'name':("newComment"),'rows':("10"),'cols':("40"),'required':("required")],-1)
printHtmlPart(65)
expressionOut.print(issue.id)
printHtmlPart(66)
})
invokeTag('form','g',209,['name':("addCommentForm"),'action':("updateIssue"),'id':(datasetInstance.id)],3)
printHtmlPart(67)
expressionOut.print(issue.id)
printHtmlPart(68)
createTagBody(3, {->
printHtmlPart(63)
invokeTag('hiddenField','g',226,['name':("issueId"),'value':(issue.id)],-1)
printHtmlPart(69)
if(true && (versionInstance.isSubmitted())) {
printHtmlPart(70)
invokeTag('select','g',229,['class':("form-control"),'name':("newStatus"),'from':(['PROBLEM', 'SUGGESTION', 'RESOLVED'])],-1)
printHtmlPart(71)
}
else {
printHtmlPart(70)
invokeTag('select','g',232,['class':("form-control"),'name':("newStatus"),'from':(['FIXED', 'INTENTIONAL'])],-1)
printHtmlPart(71)
}
printHtmlPart(72)
invokeTag('textArea','g',236,['class':("form-control"),'name':("newComment"),'rows':("10"),'cols':("40"),'required':("required")],-1)
printHtmlPart(73)
expressionOut.print(issue.id)
printHtmlPart(66)
})
invokeTag('form','g',246,['name':("updateIssueForm"),'action':("updateIssue"),'id':(datasetInstance.id)],3)
printHtmlPart(74)
}
printHtmlPart(75)
})
invokeTag('captureBody','sitemesh',248,[:],1)
printHtmlPart(76)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1599021454515L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
