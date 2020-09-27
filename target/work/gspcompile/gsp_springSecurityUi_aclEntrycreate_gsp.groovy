import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclEntrycreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclEntry/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.create.label"),'entityNameMessageCode':("aclEntry.label"),'entityNameDefault':("AclEntry")],-1)
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
invokeTag('textFieldRow','s2ui',14,['name':("aclObjectIdentity.id"),'size':("50"),'labelCodeDefault':("AclObjectIdentity")],-1)
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',15,['name':("aceOrder"),'size':("50"),'labelCodeDefault':("Ace Order")],-1)
printHtmlPart(6)
invokeTag('selectRow','s2ui',16,['name':("sid.id"),'from':(sids),'labelCodeDefault':("SID"),'optionValue':("sid"),'noSelection':(['null': ''])],-1)
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',17,['name':("mask"),'size':("50"),'labelCodeDefault':("Mask")],-1)
printHtmlPart(6)
invokeTag('checkboxRow','s2ui',18,['name':("granting"),'labelCodeDefault':("Granting")],-1)
printHtmlPart(6)
invokeTag('checkboxRow','s2ui',19,['name':("auditSuccess"),'labelCodeDefault':("Audit Success")],-1)
printHtmlPart(6)
invokeTag('checkboxRow','s2ui',20,['name':("auditFailure"),'labelCodeDefault':("Audit Failure")],-1)
printHtmlPart(7)
invokeTag('submitButton','s2ui',21,[:],-1)
printHtmlPart(8)
})
invokeTag('form','s2ui',25,[:],3)
printHtmlPart(9)
})
invokeTag('formContainer','s2ui',26,['type':("save"),'beanType':("aclEntry"),'focus':("aclObjectIdentity")],2)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',28,[:],1)
printHtmlPart(11)
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
