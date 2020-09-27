import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_roleedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/role/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.edit.label"),'entityNameMessageCode':("role.label"),'entityNameDefault':("Role")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',7,['code':("default.edit.label"),'args':([entityName])],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
invokeTag('textFieldRow','s2ui',13,['name':("authority"),'labelCodeDefault':("Authority")],-1)
printHtmlPart(8)
})
invokeTag('tab','s2ui',16,['name':("roleinfo"),'height':("150")],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(9)
if(true && (users.empty)) {
printHtmlPart(9)
invokeTag('message','g',19,['code':("spring.security.ui.role_no_users")],-1)
printHtmlPart(9)
}
printHtmlPart(9)
for( u in (users) ) {
printHtmlPart(10)
createTagBody(6, {->
expressionOut.print(uiPropertiesStrategy.getProperty(u, 'username'))
})
invokeTag('link','g',22,['controller':("user"),'action':("edit"),'id':(u.id)],6)
printHtmlPart(11)
}
printHtmlPart(6)
})
invokeTag('tab','s2ui',24,['name':("users"),'height':("150")],4)
printHtmlPart(5)
})
invokeTag('tabs','s2ui',25,['elementId':("tabs"),'height':("150"),'data':(tabData)],3)
printHtmlPart(12)
invokeTag('submitButton','s2ui',27,[:],-1)
printHtmlPart(6)
if(true && (role)) {
printHtmlPart(6)
invokeTag('deleteButton','s2ui',29,[:],-1)
printHtmlPart(6)
}
printHtmlPart(13)
})
invokeTag('form','s2ui',32,['type':("update"),'beanName':("role")],2)
printHtmlPart(1)
if(true && (role)) {
printHtmlPart(1)
invokeTag('deleteButtonForm','s2ui',34,['instanceId':(role.id)],-1)
printHtmlPart(1)
}
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',36,[:],1)
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
