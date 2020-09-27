import balsa.security.Approval
import balsa.Dataset
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_studymine_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/study/mine.gsp" }
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
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
for( studyInstance in (studyInstanceList) ) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(studyInstance.shortTitle ?: studyInstance.title)
})
invokeTag('link','g',39,['action':("show"),'id':(studyInstance.id)],3)
printHtmlPart(7)
expressionOut.print(studyInstance.workingVersion().status)
printHtmlPart(7)
if(true && (studyInstance.publicVersion() && (studyInstance.workingVersion() != studyInstance.publicVersion()))) {
printHtmlPart(8)
createClosureForHtmlPart(9, 4)
invokeTag('link','g',46,['action':("show"),'id':(studyInstance.id),'params':([version: 'public']),'style':("display: block")],4)
printHtmlPart(10)
}
printHtmlPart(10)
if(true && (studyInstance.preprintVersion() && (studyInstance.workingVersion() != studyInstance.preprintVersion()))) {
printHtmlPart(8)
createClosureForHtmlPart(11, 4)
invokeTag('link','g',49,['action':("show"),'id':(studyInstance.id),'params':([version: 'preprint']),'style':("display: block")],4)
printHtmlPart(10)
}
printHtmlPart(10)
if(true && (studyInstance.submittedVersion() && (studyInstance.workingVersion() != studyInstance.submittedVersion()))) {
printHtmlPart(8)
createClosureForHtmlPart(12, 4)
invokeTag('link','g',52,['action':("show"),'id':(studyInstance.id),'params':([version: 'submitted']),'style':("display: block")],4)
printHtmlPart(10)
}
printHtmlPart(10)
if(true && (studyInstance.approvedVersion() && (studyInstance.workingVersion() != studyInstance.approvedVersion()))) {
printHtmlPart(8)
createClosureForHtmlPart(13, 4)
invokeTag('link','g',55,['action':("show"),'id':(studyInstance.id),'params':([version: 'approved']),'style':("display: block")],4)
printHtmlPart(10)
}
printHtmlPart(14)
for( version in (studyInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}) ) {
printHtmlPart(15)
expressionOut.print(studyInstance.id)
printHtmlPart(16)
expressionOut.print(version.id)
printHtmlPart(17)
expressionOut.print(version.status)
printHtmlPart(18)
expressionOut.print(version.updatedDate)
printHtmlPart(19)
}
printHtmlPart(20)
expressionOut.print(studyInstance.owners*.username.sort().join(', '))
printHtmlPart(21)
invokeTag('createLink','g',69,['action':("delete"),'id':(studyInstance.id)],-1)
printHtmlPart(22)
}
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',109,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1599769024951L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
