import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_loginauth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/login/auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('set','g',1,['var':("securityConfig"),'value':(applicationContext.springSecurityService.securityConfig)],-1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('title','s2ui',5,['messageCode':("spring.security.ui.login.title")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',6,['src':("spring-security-ui-auth.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
invokeTag('message','g',14,['code':("spring.security.ui.login.signin")],-1)
printHtmlPart(5)
invokeTag('message','g',17,['code':("spring.security.ui.login.username")],-1)
printHtmlPart(6)
expressionOut.print(securityConfig.apf.usernameParameter)
printHtmlPart(7)
invokeTag('message','g',21,['code':("spring.security.ui.login.password")],-1)
printHtmlPart(8)
expressionOut.print(securityConfig.apf.passwordParameter)
printHtmlPart(9)
expressionOut.print(securityConfig.rememberMe.parameter)
printHtmlPart(10)
invokeTag('message','g',27,['code':("spring.security.ui.login.rememberme")],-1)
printHtmlPart(11)
createTagBody(3, {->
invokeTag('message','g',29,['code':("spring.security.ui.login.forgotPassword")],-1)
})
invokeTag('link','g',29,['controller':("register"),'action':("forgotPassword")],3)
printHtmlPart(12)
invokeTag('linkButton','s2ui',35,['elementId':("register"),'controller':("register"),'messageCode':("spring.security.ui.login.register")],-1)
printHtmlPart(13)
invokeTag('submitButton','s2ui',36,['elementId':("loginButton"),'messageCode':("spring.security.ui.login.login")],-1)
printHtmlPart(14)
})
invokeTag('form','s2ui',41,['type':("login"),'focus':("username")],2)
printHtmlPart(15)
})
invokeTag('captureBody','sitemesh',44,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450678956592L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
