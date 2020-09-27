import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_registrationCodeedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/registrationCode/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.edit.label"),'entityNameMessageCode':("registrationCode.label"),'entityNameDefault':("RegistrationCode")],-1)
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
invokeTag('textFieldRow','s2ui',14,['name':("username"),'size':("50"),'labelCodeDefault':("Username")],-1)
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',15,['name':("token"),'size':("50"),'labelCodeDefault':("Token")],-1)
printHtmlPart(7)
expressionOut.print(message(code: 'registrationCode.dateCreated.label', default: 'Date Created'))
printHtmlPart(8)
expressionOut.print(formatDate(date: uiPropertiesStrategy.getProperty(registrationCode, 'dateCreated'), formatName: 'spring.security.ui.dateFormatGsp'))
printHtmlPart(9)
invokeTag('submitButton','s2ui',26,[:],-1)
printHtmlPart(10)
if(true && (registrationCode)) {
printHtmlPart(10)
invokeTag('deleteButton','s2ui',28,[:],-1)
printHtmlPart(10)
}
printHtmlPart(11)
})
invokeTag('form','s2ui',31,[:],3)
printHtmlPart(12)
})
invokeTag('formContainer','s2ui',32,['type':("update"),'beanType':("registrationCode"),'height':("350")],2)
printHtmlPart(12)
if(true && (registrationCode)) {
printHtmlPart(12)
invokeTag('deleteButtonForm','s2ui',34,['instanceId':(registrationCode.id)],-1)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('ajaxSearch','s2ui',37,['paramName':("username")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',38,[:],1)
printHtmlPart(14)
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
