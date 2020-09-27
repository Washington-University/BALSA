import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_layoutsspringSecurityUI_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/layouts/springSecurityUI.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("x-ua-compatible"),'content':("ie=edge")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1")],-1)
printHtmlPart(1)
invokeTag('stylesheet','s2ui',8,['src':("spring-security-ui")],-1)
printHtmlPart(2)
invokeTag('layoutHead','g',9,[:],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('menu','s2ui',15,['controller':("user")],-1)
printHtmlPart(5)
invokeTag('menu','s2ui',16,['controller':("role")],-1)
printHtmlPart(5)
if(true && (securityConfig.securityConfigType?.toString() == 'Requestmap')) {
printHtmlPart(5)
invokeTag('menu','s2ui',18,['controller':("requestmap")],-1)
printHtmlPart(5)
}
printHtmlPart(5)
if(true && (securityConfig.rememberMe.persistent)) {
printHtmlPart(5)
invokeTag('menu','s2ui',21,['controller':("persistentLogin"),'searchOnly':("true")],-1)
printHtmlPart(5)
}
printHtmlPart(5)
invokeTag('menu','s2ui',23,['controller':("registrationCode"),'searchOnly':("true")],-1)
printHtmlPart(5)
if(true && (applicationContext.pluginManager.hasGrailsPlugin('springSecurityAcl'))) {
printHtmlPart(6)
invokeTag('message','g',25,['code':("spring.security.ui.menu.acl")],-1)
printHtmlPart(7)
invokeTag('menu','s2ui',27,['controller':("aclClass"),'submenu':("true")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',28,['controller':("aclSid"),'submenu':("true")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',29,['controller':("aclObjectIdentity"),'submenu':("true")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',30,['controller':("aclEntry"),'submenu':("true")],-1)
printHtmlPart(9)
}
printHtmlPart(6)
invokeTag('message','g',34,['code':("spring.security.ui.menu.securityInfo")],-1)
printHtmlPart(7)
invokeTag('menu','s2ui',36,['controller':("securityInfo"),'itemAction':("config")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',37,['controller':("securityInfo"),'itemAction':("mappings")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',38,['controller':("securityInfo"),'itemAction':("currentAuth")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',39,['controller':("securityInfo"),'itemAction':("usercache")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',40,['controller':("securityInfo"),'itemAction':("filterChains")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',41,['controller':("securityInfo"),'itemAction':("logoutHandlers")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',42,['controller':("securityInfo"),'itemAction':("voters")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',43,['controller':("securityInfo"),'itemAction':("providers")],-1)
printHtmlPart(8)
invokeTag('menu','s2ui',44,['controller':("securityInfo"),'itemAction':("secureChannel")],-1)
printHtmlPart(10)
invokeTag('message','g',49,['code':("spring.security.ui.defaultTitle")],-1)
printHtmlPart(11)
invokeTag('render','g',50,['template':("/includes/ajaxLogin")],-1)
printHtmlPart(12)
invokeTag('layoutBody','g',56,[:],-1)
printHtmlPart(13)
invokeTag('javascript','asset',60,['src':("spring-security-ui.js")],-1)
printHtmlPart(2)
invokeTag('showFlash','s2ui',61,[:],-1)
printHtmlPart(2)
invokeTag('deferredScripts','s2ui',62,[:],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',63,[:],1)
printHtmlPart(14)
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
