import balsa.Dataset
import balsa.curation.Issue
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_curationindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/curation/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
for( queueItem in (myQueue) ) {
printHtmlPart(7)
if(true && (queueItem.submittedVersion() || (queueItem.publicVersion() ?: queueItem.preprintVersion()) != queueItem.workingVersion())) {
printHtmlPart(8)
createTagBody(4, {->
expressionOut.print(queueItem.shortTitle ?: queueItem.title)
})
invokeTag('link','g',29,['controller':(datasetTerm('item':queueItem)),'action':("issues"),'id':(queueItem.id)],4)
printHtmlPart(9)
for( version in (queueItem.versions.findAll { it.submittedDate }.sort {a,b-> b.submittedDate<=>a.submittedDate}) ) {
printHtmlPart(10)
expressionOut.print(datasetTerm('item':queueItem))
printHtmlPart(11)
expressionOut.print(queueItem.id)
printHtmlPart(12)
expressionOut.print(version.id)
printHtmlPart(13)
expressionOut.print(version.id)
printHtmlPart(14)
expressionOut.print(version.submittedDate)
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(queueItem.submittedVersion()?.status ?: queueItem.workingVersion()?.status)
printHtmlPart(17)
invokeTag('formatDate','g',42,['format':("yyyy-MM-dd HH:mm"),'date':(queueItem.submittedVersion()?.createdDate ?: queueItem.workingVersion()?.createdDate)],-1)
printHtmlPart(17)
invokeTag('formatDate','g',45,['format':("yyyy-MM-dd HH:mm"),'date':(queueItem.submittedVersion()?.actualReleaseDate() ?: queueItem.workingVersion()?.actualReleaseDate())],-1)
printHtmlPart(17)
expressionOut.print(queueItem.issues?.count({ it.status != Issue.Status.RESOLVED }))
printHtmlPart(18)
createClosureForHtmlPart(19, 4)
invokeTag('link','g',51,['action':("removeFromMyQueue"),'id':(queueItem.id),'data-toggle':("tooltip"),'data-placement':("bottom"),'title':("Remove from my queue")],4)
printHtmlPart(20)
}
printHtmlPart(7)
}
printHtmlPart(21)
for( queueItem in (mainQueue) ) {
printHtmlPart(8)
createTagBody(3, {->
expressionOut.print(queueItem.dataset.shortTitle ?: queueItem.dataset.title)
})
invokeTag('link','g',83,['controller':(datasetTerm('item':queueItem)),'action':("issues"),'id':(queueItem.dataset.id)],3)
printHtmlPart(22)
invokeTag('formatDate','g',87,['format':("yyyy-MM-dd HH:mm"),'date':(queueItem.createdDate)],-1)
printHtmlPart(17)
invokeTag('formatDate','g',90,['format':("yyyy-MM-dd HH:mm"),'date':(queueItem.actualReleaseDate())],-1)
printHtmlPart(17)
expressionOut.print(queueItem.dataset.issues?.count({ it.status != Issue.Status.RESOLVED }))
printHtmlPart(17)
if(true && (queueItem.dataset.curator)) {
expressionOut.print(queueItem.dataset.curator.username)
}
else {
createClosureForHtmlPart(23, 4)
invokeTag('link','g',97,['action':("addToMyQueue"),'id':(queueItem.dataset.id)],4)
}
printHtmlPart(20)
}
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',114,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1599023287068L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
