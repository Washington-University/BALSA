import balsa.Dataset
import balsa.Version
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_datasetindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(datasetType)
printHtmlPart(3)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(datasetType)
printHtmlPart(6)
expressionOut.print(datasetType.toLowerCase())
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('ifAnyGranted','sec',28,['roles':("ROLE_ADMIN,ROLE_CURATOR")],2)
printHtmlPart(9)
if(true && (datasetType=='Study')) {
printHtmlPart(10)
}
printHtmlPart(11)
for( datasetInstance in (datasetInstanceList) ) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(datasetInstance.shortTitle ?: datasetInstance.title)
})
invokeTag('link','g',50,['action':("show"),'id':(datasetInstance.id)],3)
printHtmlPart(13)
expressionOut.print(datasetInstance.species*.name.join(', '))
printHtmlPart(14)
createTagBody(3, {->
printHtmlPart(15)
expressionOut.print(datasetInstance.workingVersion().status)
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',59,['roles':("ROLE_ADMIN,ROLE_CURATOR")],3)
printHtmlPart(9)
if(true && (datasetType=='Study')) {
printHtmlPart(16)
expressionOut.print(datasetInstance.publicVersion()?.pmid)
printHtmlPart(17)
expressionOut.print(datasetInstance.publicVersion()?.pmid)
printHtmlPart(18)
expressionOut.print(datasetInstance.publicVersion()?.doi)
printHtmlPart(17)
expressionOut.print(datasetInstance.publicVersion()?.doi)
printHtmlPart(19)
expressionOut.print(datasetInstance.owners*.username.sort().join(', '))
printHtmlPart(20)
if(true && (datasetInstance.publicVersion() && (datasetInstance.workingVersion() != datasetInstance.publicVersion()))) {
printHtmlPart(21)
createClosureForHtmlPart(22, 5)
invokeTag('link','g',72,['action':("show"),'id':(datasetInstance.id),'params':([version: 'public']),'style':("display: block")],5)
printHtmlPart(23)
}
printHtmlPart(23)
if(true && (datasetInstance.preprintVersion() && (datasetInstance.workingVersion() != datasetInstance.preprintVersion()))) {
printHtmlPart(21)
createClosureForHtmlPart(24, 5)
invokeTag('link','g',75,['action':("show"),'id':(datasetInstance.id),'params':([version: 'preprint']),'style':("display: block")],5)
printHtmlPart(23)
}
printHtmlPart(25)
createTagBody(4, {->
printHtmlPart(23)
if(true && (datasetInstance.submittedVersion() && (datasetInstance.workingVersion() != datasetInstance.submittedVersion()))) {
printHtmlPart(21)
createClosureForHtmlPart(26, 6)
invokeTag('link','g',80,['action':("show"),'id':(datasetInstance.id),'params':([version: 'submitted']),'style':("display: block")],6)
printHtmlPart(23)
}
printHtmlPart(23)
if(true && (datasetInstance.approvedVersion() && (datasetInstance.workingVersion() != datasetInstance.approvedVersion()))) {
printHtmlPart(21)
createClosureForHtmlPart(27, 6)
invokeTag('link','g',83,['action':("show"),'id':(datasetInstance.id),'params':([version: 'approved']),'style':("display: block")],6)
printHtmlPart(23)
}
printHtmlPart(28)
for( version in (datasetInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}) ) {
printHtmlPart(29)
expressionOut.print(datasetType.toLowerCase())
printHtmlPart(30)
expressionOut.print(datasetInstance.id)
printHtmlPart(31)
expressionOut.print(version.id)
printHtmlPart(32)
expressionOut.print(version.status == Version.Status.PUBLIC && version.preprint ? 'PREPRINT' : version.status)
printHtmlPart(33)
expressionOut.print(version.updatedDate)
printHtmlPart(34)
}
printHtmlPart(35)
})
invokeTag('ifAnyGranted','sec',91,['roles':("ROLE_ADMIN,ROLE_CURATOR")],4)
printHtmlPart(14)
}
printHtmlPart(36)
}
printHtmlPart(37)
if(true && (datasetType=='Study')) {
printHtmlPart(38)
}
printHtmlPart(39)
})
invokeTag('captureBody','sitemesh',132,[:],1)
printHtmlPart(40)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1589344975169L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
