import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_includes_ajaxLogin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/includes/_ajaxLogin.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('set','g',1,['var':("securityConfig"),'value':(applicationContext.springSecurityService.securityConfig)],-1)
printHtmlPart(0)
invokeTag('link','g',11,['elementId':("_logout"),'controller':("logout")],-1)
printHtmlPart(1)
expressionOut.print(request.contextPath)
expressionOut.print(securityConfig.logout.afterLogoutUrl)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',16,['code':("spring.security.ui.login.loggedInAs"),'args':([sec.username()])],-1)
printHtmlPart(4)
createTagBody(2, {->
invokeTag('message','g',17,['code':("spring.security.ui.login.logout")],-1)
})
invokeTag('link','g',17,['controller':("logout"),'elementId':("logout")],2)
printHtmlPart(5)
})
invokeTag('ifLoggedIn','sec',18,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("spring.security.ui.login.login")],-1)
printHtmlPart(7)
})
invokeTag('ifNotLoggedIn','sec',21,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(request.contextPath)
expressionOut.print(securityConfig.switchUser.exitUserUrl)
printHtmlPart(9)
invokeTag('message','g',24,['code':("spring.security.ui.login.resumeAs"),'args':([sec.switchedUserOriginalUsername()])],-1)
printHtmlPart(10)
})
invokeTag('ifSwitched','sec',26,[:],1)
printHtmlPart(11)
expressionOut.print(message(code:'spring.security.ui.login.signin'))
printHtmlPart(12)
expressionOut.print(request.contextPath)
expressionOut.print(securityConfig.apf.filterProcessesUrl)
printHtmlPart(13)
invokeTag('message','g',33,['code':("spring.security.ui.login.username")],-1)
printHtmlPart(14)
expressionOut.print(securityConfig.apf.usernameParameter)
printHtmlPart(15)
invokeTag('message','g',35,['code':("spring.security.ui.login.password")],-1)
printHtmlPart(16)
expressionOut.print(securityConfig.apf.passwordParameter)
printHtmlPart(17)
expressionOut.print(securityConfig.rememberMe.parameter)
printHtmlPart(18)
invokeTag('message','g',38,['code':("spring.security.ui.login.rememberme")],-1)
printHtmlPart(19)
createTagBody(1, {->
invokeTag('message','g',39,['code':("spring.security.ui.login.forgotPassword")],-1)
})
invokeTag('link','g',39,['controller':("register"),'action':("forgotPassword")],1)
printHtmlPart(3)
createTagBody(1, {->
invokeTag('message','g',40,['code':("spring.security.ui.login.register")],-1)
})
invokeTag('link','g',40,['controller':("register")],1)
printHtmlPart(20)
createTagBody(1, {->
printHtmlPart(21)
invokeTag('message','g',47,['code':("spring.security.ui.login.login")],-1)
printHtmlPart(22)
invokeTag('message','g',48,['code':("spring.security.ui.login.cancel")],-1)
printHtmlPart(23)
invokeTag('message','g',49,['code':("spring.security.ui.login.loggingYouIn")],-1)
printHtmlPart(24)
invokeTag('message','g',50,['code':("spring.security.ui.login.loggedInAs"),'args':(["{0}"])],-1)
printHtmlPart(25)
})
invokeTag('script','asset',51,[:],1)
printHtmlPart(26)
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
