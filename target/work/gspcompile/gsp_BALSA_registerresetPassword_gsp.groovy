import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_registerresetPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/register/resetPassword.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.resetPassword.title")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
invokeTag('message','g',11,['error':(error)],-1)
printHtmlPart(6)
})
invokeTag('eachError','g',12,['bean':(resetPasswordCommand),'var':("error")],3)
printHtmlPart(7)
})
invokeTag('hasErrors','g',14,['bean':(resetPasswordCommand)],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('hiddenField','g',16,['name':("t"),'value':(token)],-1)
printHtmlPart(10)
})
invokeTag('form','g',34,['class':("form-horizontal"),'controller':("register"),'action':("resetPassword"),'name':("resetPasswordForm"),'autocomplete':("off"),'focus':("password")],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',36,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468622014003L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
