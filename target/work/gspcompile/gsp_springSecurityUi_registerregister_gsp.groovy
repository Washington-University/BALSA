import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_registerregister_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/register/register.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutRegister)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.register.title")],-1)
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
invokeTag('message','g',11,['code':("spring.security.ui.register.sent")],-1)
printHtmlPart(4)
}
else {
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',17,['name':("username"),'size':("40"),'labelCodeDefault':("Username")],-1)
printHtmlPart(7)
invokeTag('textFieldRow','s2ui',18,['name':("email"),'size':("40"),'labelCodeDefault':("E-mail")],-1)
printHtmlPart(7)
invokeTag('passwordFieldRow','s2ui',19,['name':("password"),'size':("40"),'labelCodeDefault':("Password")],-1)
printHtmlPart(7)
invokeTag('passwordFieldRow','s2ui',20,['name':("password2"),'size':("40"),'labelCodeDefault':("Password (again)")],-1)
printHtmlPart(8)
invokeTag('submitButton','s2ui',23,['elementId':("submit"),'messageCode':("spring.security.ui.register.submit")],-1)
printHtmlPart(4)
}
printHtmlPart(3)
})
invokeTag('form','s2ui',25,['beanName':("registerCommand")],3)
printHtmlPart(1)
})
invokeTag('formContainer','s2ui',26,['type':("register"),'focus':("username"),'width':("800px")],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',27,[:],1)
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
