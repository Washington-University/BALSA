import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_persistentLoginedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/persistentLogin/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.edit.label"),'entityNameMessageCode':("persistentLogin.label"),'entityNameDefault':("PersistentLogin")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
expressionOut.print(message(code: 'persistentLogin.series.label', default: 'Series'))
printHtmlPart(6)
expressionOut.print(persistentLogin.series)
printHtmlPart(7)
expressionOut.print(message(code: 'persistentLogin.username.label', default: 'Username'))
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(persistentLogin.username)
})
invokeTag('link','g',24,['action':("edit"),'controller':("user"),'params':([username: persistentLogin.username])],4)
printHtmlPart(8)
invokeTag('textFieldRow','s2ui',26,['name':("token"),'size':("50"),'labelCodeDefault':("Token")],-1)
printHtmlPart(9)
invokeTag('dateFieldRow','s2ui',27,['name':("lastUsed"),'size':("50"),'labelCodeDefault':("Last Used")],-1)
printHtmlPart(10)
invokeTag('submitButton','s2ui',32,[:],-1)
printHtmlPart(11)
if(true && (persistentLogin)) {
printHtmlPart(11)
invokeTag('deleteButton','s2ui',34,[:],-1)
printHtmlPart(11)
}
printHtmlPart(12)
})
invokeTag('form','s2ui',37,['idName':("series")],3)
printHtmlPart(13)
})
invokeTag('formContainer','s2ui',38,['type':("update"),'beanType':("persistentLogin"),'focus':("token"),'height':("350")],2)
printHtmlPart(13)
if(true && (persistentLogin)) {
printHtmlPart(13)
invokeTag('deleteButtonForm','s2ui',40,['instanceId':(persistentLogin.series)],-1)
printHtmlPart(13)
}
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',43,[:],1)
printHtmlPart(15)
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
