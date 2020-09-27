import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_registerforgotPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/register/forgotPassword.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutRegister)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.forgotPassword.title")],-1)
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
if(true && (emailSent)) {
printHtmlPart(5)
invokeTag('message','g',11,['code':("spring.security.ui.forgotPassword.sent")],-1)
printHtmlPart(4)
}
else {
printHtmlPart(6)
invokeTag('message','g',15,['code':("spring.security.ui.forgotPassword.description")],-1)
printHtmlPart(7)
invokeTag('textFieldRow','s2ui',17,['name':("username"),'size':("25"),'labelCodeDefault':("Username")],-1)
printHtmlPart(8)
invokeTag('submitButton','s2ui',19,['elementId':("submit"),'messageCode':("spring.security.ui.forgotPassword.submit")],-1)
printHtmlPart(4)
}
printHtmlPart(3)
})
invokeTag('form','s2ui',21,['beanName':("forgotPasswordCommand")],3)
printHtmlPart(1)
})
invokeTag('formContainer','s2ui',22,['type':("forgotPassword"),'focus':("username"),'width':("50%")],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',23,[:],1)
printHtmlPart(9)
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
