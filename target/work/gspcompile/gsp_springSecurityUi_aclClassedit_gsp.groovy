import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclClassedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclClass/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.edit.label"),'entityNameMessageCode':("aclClass.label"),'entityNameDefault':("AclClass")],-1)
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
invokeTag('textFieldRow','s2ui',14,['name':("className"),'size':("60"),'labelCodeDefault':("Class Name")],-1)
printHtmlPart(6)
createTagBody(4, {->
invokeTag('message','g',16,['code':("spring.security.ui.aclClass.edit.viewOids")],-1)
})
invokeTag('link','g',16,['action':("search"),'controller':("aclObjectIdentity"),'params':(["aclClass.id": aclClass.id])],4)
printHtmlPart(7)
createTagBody(4, {->
invokeTag('message','g',19,['code':("spring.security.ui.aclClass.edit.viewEntries")],-1)
})
invokeTag('link','g',19,['action':("search"),'controller':("aclEntry"),'params':(["aclClass.id": aclClass.id])],4)
printHtmlPart(8)
invokeTag('submitButton','s2ui',25,[:],-1)
printHtmlPart(9)
if(true && (aclClass)) {
printHtmlPart(9)
invokeTag('deleteButton','s2ui',27,[:],-1)
printHtmlPart(9)
}
printHtmlPart(10)
})
invokeTag('form','s2ui',30,[:],3)
printHtmlPart(11)
})
invokeTag('formContainer','s2ui',31,['type':("update"),'beanType':("aclClass"),'focus':("className"),'height':("300")],2)
printHtmlPart(11)
if(true && (aclClass)) {
printHtmlPart(11)
invokeTag('deleteButtonForm','s2ui',33,['instanceId':(aclClass.id)],-1)
printHtmlPart(11)
}
printHtmlPart(12)
})
invokeTag('captureBody','sitemesh',36,[:],1)
printHtmlPart(13)
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
