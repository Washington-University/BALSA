import balsa.security.Approval
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_approvalshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/approval/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(approvalInstance.title)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
if(true && (isOwner)) {
printHtmlPart(1)
createClosureForHtmlPart(3, 3)
invokeTag('link','g',10,['action':("queue"),'resource':(approvalInstance)],3)
printHtmlPart(1)
createClosureForHtmlPart(4, 3)
invokeTag('link','g',11,['action':("edit"),'resource':(approvalInstance)],3)
printHtmlPart(1)
}
printHtmlPart(5)
expressionOut.print(approvalInstance.title)
printHtmlPart(6)
expressionOut.print(approvalInstance.contents.encodeAsPreserveWhitespace())
printHtmlPart(7)
expressionOut.print(approvalInstance.link.encodeAsRemoveScript())
printHtmlPart(8)
})
invokeTag('captureBody','sitemesh',18,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1488232954839L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
