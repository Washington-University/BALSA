import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_approval_seeking_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/approval/_seeking.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(it.id)
printHtmlPart(1)
expressionOut.print(it.username)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('remoteLink','g',5,['action':("approve"),'id':(approvalInstance.id),'params':([userId:it.id]),'onSuccess':("approvalGranted(${it.id}, data);updateRecentActivity();")],1)
printHtmlPart(4)
createClosureForHtmlPart(5, 1)
invokeTag('remoteLink','g',8,['action':("deny"),'id':(approvalInstance.id),'params':([userId:it.id]),'onSuccess':("approvalDenied(${it.id});updateRecentActivity();")],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1555971684225L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
