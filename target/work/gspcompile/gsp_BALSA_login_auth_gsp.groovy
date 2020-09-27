import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_login_auth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/login/_auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('link','g',39,['action':("forgotUsername"),'controller':("register")],2)
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('link','g',40,['action':("forgotPassword"),'controller':("register")],2)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',44,['class':("btn btn-default"),'action':("register"),'controller':("register"),'style':("width:87px")],2)
printHtmlPart(7)
})
invokeTag('formRemote','g',48,['class':("form-horizontal"),'action':("/j_spring_security_check"),'url':([controller: 'j_spring_security_check', action: '']),'id':("loginForm"),'name':("loginForm"),'autocomplete':("off"),'onSuccess':("isLoggedIn(data)")],1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1555971769069L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
