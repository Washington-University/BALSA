import balsa.security.Approval
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_approval_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/approval/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: approvalInstance, field: 'title', 'error'))
printHtmlPart(1)
invokeTag('field','g',5,['type':("text"),'name':("title"),'value':(approvalInstance?.title)],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'contents', 'error'))
printHtmlPart(3)
invokeTag('textArea','g',10,['id':("contents"),'name':("contents"),'value':(approvalInstance?.contents),'rows':("10"),'cols':("40")],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: approvalInstance, field: 'link', 'error'))
printHtmlPart(4)
invokeTag('field','g',14,['type':("text"),'name':("link"),'value':(approvalInstance?.link)],-1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450218509914L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
