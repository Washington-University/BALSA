import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_registerresetPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/register/resetPassword.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutRegister)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.resetPassword.title")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
createTagBody(3, {->
printHtmlPart(4)
invokeTag('hiddenField','g',9,['name':("t"),'value':(token)],-1)
printHtmlPart(5)
invokeTag('message','g',12,['code':("spring.security.ui.resetPassword.description")],-1)
printHtmlPart(6)
invokeTag('passwordFieldRow','s2ui',14,['name':("password"),'labelCodeDefault':("Password")],-1)
printHtmlPart(7)
invokeTag('passwordFieldRow','s2ui',15,['name':("password2"),'labelCodeDefault':("Password (again)")],-1)
printHtmlPart(8)
invokeTag('submitButton','s2ui',17,['elementId':("submit"),'messageCode':("spring.security.ui.resetPassword.submit")],-1)
printHtmlPart(9)
})
invokeTag('form','s2ui',19,['beanName':("resetPasswordCommand")],3)
printHtmlPart(1)
})
invokeTag('formContainer','s2ui',20,['type':("resetPassword"),'focus':("password"),'width':("475px")],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',21,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450678956593L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
