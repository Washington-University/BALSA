import balsa.security.Approval
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_approvaledit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/approval/edit.gsp" }
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
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
expressionOut.print(flash.message)
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
createTagBody(3, {->
printHtmlPart(9)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(10)
expressionOut.print(error.field)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',17,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',18,['bean':(approvalInstance),'var':("error")],3)
printHtmlPart(14)
})
invokeTag('hasErrors','g',20,['bean':(approvalInstance)],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(15)
invokeTag('hiddenField','g',22,['name':("version"),'value':(approvalInstance?.version)],-1)
printHtmlPart(16)
invokeTag('render','g',24,['template':("form")],-1)
printHtmlPart(17)
invokeTag('actionSubmit','g',27,['class':("btn btn-primary"),'action':("update"),'value':("Update")],-1)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',28,['class':("btn btn-default"),'action':("show"),'resource':(approvalInstance)],3)
printHtmlPart(20)
})
invokeTag('form','g',30,['url':([resource:approvalInstance, action:'update']),'method':("PUT")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',32,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1452190948225L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
