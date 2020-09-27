import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_contacttechMessages_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/contact/techMessages.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( issue in (messages) ) {
printHtmlPart(5)
expressionOut.print(issue.createdDate)
printHtmlPart(6)
expressionOut.print(issue.createdBy.username)
printHtmlPart(7)
expressionOut.print(issue.createdBy.profile.emailAddress)
printHtmlPart(8)
expressionOut.print(issue.title)
printHtmlPart(9)
expressionOut.print(issue.contents)
printHtmlPart(10)
expressionOut.print(issue.id)
printHtmlPart(11)
createClosureForHtmlPart(12, 3)
invokeTag('link','g',37,['class':("btn btn-default"),'role':("button"),'action':("deleteTechMessage"),'id':(issue.id)],3)
printHtmlPart(13)
expressionOut.print(issue.id)
printHtmlPart(14)
createTagBody(3, {->
printHtmlPart(15)
invokeTag('textArea','g',53,['class':("form-control"),'name':("resolvedReason"),'rows':("10"),'cols':("40")],-1)
printHtmlPart(16)
expressionOut.print(issue.id)
printHtmlPart(17)
})
invokeTag('form','g',62,['name':("resolveIssueForm"),'action':("resolveTechMessage"),'id':(issue.id)],3)
printHtmlPart(18)
}
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',69,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1539807295478L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}