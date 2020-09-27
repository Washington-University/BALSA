import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_registerregister_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/register/register.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',4,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
invokeTag('message','g',10,['error':(error)],-1)
printHtmlPart(6)
})
invokeTag('eachError','g',11,['bean':(registerCommand),'var':("error")],3)
printHtmlPart(7)
})
invokeTag('hasErrors','g',13,['bean':(registerCommand)],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
if(true && (hasErrors(bean: registerCommand, field: 'username', 'error'))) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('field','g',18,['type':("text"),'name':("username"),'class':("form-control"),'id':("username"),'placeholder':("Username"),'value':(registerCommand.username),'required':("true")],-1)
printHtmlPart(12)
if(true && (hasErrors(bean: registerCommand, field: 'email', 'error'))) {
printHtmlPart(10)
}
printHtmlPart(13)
invokeTag('field','g',24,['type':("email"),'name':("email"),'class':("form-control"),'id':("username"),'placeholder':("Email Address"),'value':(registerCommand.email),'required':("true")],-1)
printHtmlPart(12)
if(true && (hasErrors(bean: registerCommand, field: 'password', 'error'))) {
printHtmlPart(10)
}
printHtmlPart(14)
invokeTag('field','g',30,['name':("password"),'class':("form-control"),'id':("password"),'placeholder':("Password"),'type':("password"),'required':("true")],-1)
printHtmlPart(12)
if(true && (hasErrors(bean: registerCommand, field: 'password2', 'error'))) {
printHtmlPart(10)
}
printHtmlPart(15)
invokeTag('field','g',36,['name':("password2"),'class':("form-control"),'id':("password"),'placeholder':("Password (again)"),'type':("password"),'required':("true")],-1)
printHtmlPart(16)
invokeTag('recaptcha','recaptcha',41,[:],-1)
printHtmlPart(17)
})
invokeTag('form','g',52,['controller':("register"),'action':("register"),'name':("registerForm"),'type':("register"),'autocomplete':("off"),'focus':("username")],2)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',54,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486163412171L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
