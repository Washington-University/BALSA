import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_loginauth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/login/auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',4,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
createClosureForHtmlPart(5, 3)
invokeTag('link','g',34,['action':("forgotPassword"),'controller':("register")],3)
printHtmlPart(6)
createClosureForHtmlPart(7, 3)
invokeTag('link','g',35,['action':("forgotUsername"),'controller':("register")],3)
printHtmlPart(8)
createClosureForHtmlPart(9, 3)
invokeTag('link','g',39,['class':("btn btn-default"),'controller':("register"),'action':("register"),'style':("width:87px")],3)
printHtmlPart(10)
})
invokeTag('form','g',42,['class':("form-horizontal"),'controller':("j_spring_security_check"),'id':("loginForm"),'name':("loginForm"),'type':("login"),'autocomplete':("off"),'focus':("username")],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',44,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468881724843L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
