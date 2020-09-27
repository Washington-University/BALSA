import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclSidedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclSid/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.edit.label"),'entityNameMessageCode':("aclSid.label"),'entityNameDefault':("AclSid")],-1)
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
invokeTag('textFieldRow','s2ui',14,['name':("sid"),'size':("50"),'labelCodeDefault':("SID")],-1)
printHtmlPart(6)
invokeTag('checkboxRow','s2ui',15,['name':("principal"),'labelCodeDefault':("Principal")],-1)
printHtmlPart(7)
createTagBody(4, {->
invokeTag('message','g',18,['code':("spring.security.ui.aclClass.edit.viewOids")],-1)
})
invokeTag('link','g',18,['action':("search"),'controller':("aclObjectIdentity"),'params':(["owner.id": aclSid.id])],4)
printHtmlPart(8)
createTagBody(4, {->
invokeTag('message','g',23,['code':("spring.security.ui.aclClass.edit.viewEntries")],-1)
})
invokeTag('link','g',23,['action':("search"),'controller':("aclEntry"),'params':(["sid.id": aclSid.id])],4)
printHtmlPart(9)
invokeTag('submitButton','s2ui',30,[:],-1)
printHtmlPart(10)
if(true && (aclSid)) {
printHtmlPart(10)
invokeTag('deleteButton','s2ui',32,[:],-1)
printHtmlPart(10)
}
printHtmlPart(11)
})
invokeTag('form','s2ui',35,[:],3)
printHtmlPart(12)
})
invokeTag('formContainer','s2ui',36,['type':("update"),'beanType':("aclSid"),'focus':("sid")],2)
printHtmlPart(12)
if(true && (aclSid)) {
printHtmlPart(12)
invokeTag('deleteButtonForm','s2ui',38,['instanceId':(aclSid.id)],-1)
printHtmlPart(12)
}
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',41,[:],1)
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
