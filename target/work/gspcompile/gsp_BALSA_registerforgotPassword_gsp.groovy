import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_registerforgotPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/register/forgotPassword.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
invokeTag('message','g',11,['error':(error)],-1)
printHtmlPart(7)
})
invokeTag('eachError','g',12,['bean':(forgotPasswordCommand),'var':("error")],3)
printHtmlPart(8)
})
invokeTag('hasErrors','g',14,['bean':(forgotPasswordCommand)],2)
printHtmlPart(9)
if(true && (emailSent)) {
printHtmlPart(10)
}
else {
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(11)
expressionOut.print(forgotPasswordCommand.username)
printHtmlPart(12)
})
invokeTag('form','g',35,['class':("form-horizontal"),'controller':("register"),'action':("forgotPassword"),'name':("forgotPasswordForm"),'autocomplete':("off"),'focus':("username")],3)
printHtmlPart(9)
}
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',38,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468948039924L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
