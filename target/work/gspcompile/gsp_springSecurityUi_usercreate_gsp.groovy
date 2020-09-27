import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_usercreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/user/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("default.create.label"),'entityNameMessageCode':("user.label"),'entityNameDefault':("User")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',7,['code':("default.create.label"),'args':([entityName])],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
invokeTag('textFieldRow','s2ui',13,['name':("username"),'labelCodeDefault':("Username")],-1)
printHtmlPart(8)
invokeTag('passwordFieldRow','s2ui',14,['name':("password"),'labelCodeDefault':("Password")],-1)
printHtmlPart(8)
invokeTag('checkboxRow','s2ui',15,['name':("enabled"),'labelCodeDefault':("Enabled")],-1)
printHtmlPart(8)
invokeTag('checkboxRow','s2ui',16,['name':("accountExpired"),'labelCodeDefault':("Account Expired")],-1)
printHtmlPart(8)
invokeTag('checkboxRow','s2ui',17,['name':("accountLocked"),'labelCodeDefault':("Account Locked")],-1)
printHtmlPart(8)
invokeTag('checkboxRow','s2ui',18,['name':("passwordExpired"),'labelCodeDefault':("Password Expired")],-1)
printHtmlPart(9)
})
invokeTag('tab','s2ui',21,['name':("userinfo"),'height':("280")],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(10)
for( role in (authorityList) ) {
printHtmlPart(11)
invokeTag('set','g',25,['var':("authority"),'value':(uiPropertiesStrategy.getProperty(role, 'authority'))],-1)
printHtmlPart(8)
invokeTag('checkBox','g',26,['name':(authority)],-1)
printHtmlPart(8)
createTagBody(6, {->
expressionOut.print(authority)
})
invokeTag('link','g',27,['controller':("role"),'action':("edit"),'id':(role.id)],6)
printHtmlPart(12)
}
printHtmlPart(6)
})
invokeTag('tab','s2ui',30,['name':("roles"),'height':("280")],4)
printHtmlPart(5)
})
invokeTag('tabs','s2ui',31,['elementId':("tabs"),'height':("375"),'data':(tabData)],3)
printHtmlPart(13)
invokeTag('submitButton','s2ui',33,[:],-1)
printHtmlPart(14)
})
invokeTag('form','s2ui',35,['type':("save"),'beanName':("user"),'focus':("username")],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',36,[:],1)
printHtmlPart(15)
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
