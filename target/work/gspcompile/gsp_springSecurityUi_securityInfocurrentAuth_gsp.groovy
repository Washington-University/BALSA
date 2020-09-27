import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_securityInfocurrentAuth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/securityInfo/currentAuth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',4,['code':("spring.security.ui.menu.securityInfo.currentAuth")],-1)
})
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
invokeTag('message','g',9,['code':("spring.security.ui.info.currentAuth.label.authorities")],-1)
printHtmlPart(4)
expressionOut.print(auth.authorities)
printHtmlPart(5)
invokeTag('message','g',13,['code':("spring.security.ui.info.currentAuth.label.details")],-1)
printHtmlPart(4)
expressionOut.print(auth.details)
printHtmlPart(6)
invokeTag('message','g',17,['code':("spring.security.ui.info.currentAuth.label.principal")],-1)
printHtmlPart(4)
expressionOut.print(auth.principal)
printHtmlPart(5)
invokeTag('message','g',21,['code':("spring.security.ui.info.currentAuth.label.name")],-1)
printHtmlPart(4)
expressionOut.print(auth.name)
printHtmlPart(7)
})
invokeTag('securityInfoTable','s2ui',24,['type':("currentAuth"),'headerCodes':("name,value")],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',25,[:],1)
printHtmlPart(8)
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
