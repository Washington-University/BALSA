import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclObjectIdentitycreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclObjectIdentity/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.create.label"),'entityNameMessageCode':("aclObjectIdentity.label"),'entityNameDefault':("AclObjectIdentity")],-1)
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
invokeTag('selectRow','s2ui',14,['name':("aclClass.id"),'from':(classes),'labelCodeDefault':("AclClass"),'optionValue':("className"),'noSelection':(['null': ''])],-1)
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',15,['name':("objectId"),'size':("50"),'labelCodeDefault':("Object ID")],-1)
printHtmlPart(6)
invokeTag('selectRow','s2ui',16,['name':("owner.id"),'from':(sids),'labelCodeDefault':("Owner"),'optionValue':("sid"),'noSelection':(['null': ''])],-1)
printHtmlPart(6)
invokeTag('textFieldRow','s2ui',17,['name':("parent.id"),'size':("50"),'labelCodeDefault':("Parent")],-1)
printHtmlPart(6)
invokeTag('checkboxRow','s2ui',18,['name':("entriesInheriting"),'labelCodeDefault':("Entries Inheriting")],-1)
printHtmlPart(7)
invokeTag('submitButton','s2ui',20,[:],-1)
printHtmlPart(8)
})
invokeTag('form','s2ui',24,[:],3)
printHtmlPart(9)
})
invokeTag('formContainer','s2ui',25,['type':("save"),'beanType':("aclObjectIdentity"),'focus':("objectId")],2)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',27,[:],1)
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
