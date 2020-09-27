import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_useredit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(0)
invokeTag('set','g',3,['var':("username"),'value':(uiPropertiesStrategy.getProperty(user, 'username'))],-1)
printHtmlPart(0)
if(true && (username)) {
printHtmlPart(0)
invokeTag('set','g',5,['var':("canRunAs"),'value':(true)],-1)
printHtmlPart(0)
}
printHtmlPart(0)
})
invokeTag('ifAllGranted','sec',7,['roles':(securityConfig.ui.switchUserRoleName)],2)
printHtmlPart(1)
})
invokeTag('ifNotSwitched','sec',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(3)
invokeTag('title','s2ui',12,['messageCode':("default.edit.label"),'entityNameMessageCode':("user.label"),'entityNameDefault':("User")],-1)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',15,['code':("default.edit.label"),'args':([entityName])],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(7)
createTagBody(4, {->
printHtmlPart(8)
invokeTag('textFieldRow','s2ui',21,['name':("username"),'labelCodeDefault':("Username")],-1)
printHtmlPart(9)
invokeTag('checkboxRow','s2ui',22,['name':("enabled"),'labelCodeDefault':("Enabled")],-1)
printHtmlPart(9)
invokeTag('checkboxRow','s2ui',23,['name':("accountExpired"),'labelCodeDefault':("Account Expired")],-1)
printHtmlPart(9)
invokeTag('checkboxRow','s2ui',24,['name':("accountLocked"),'labelCodeDefault':("Account Locked")],-1)
printHtmlPart(9)
invokeTag('checkboxRow','s2ui',25,['name':("passwordExpired"),'labelCodeDefault':("Password Expired")],-1)
printHtmlPart(10)
})
invokeTag('tab','s2ui',28,['name':("userinfo"),'height':("275")],4)
printHtmlPart(7)
createTagBody(4, {->
printHtmlPart(11)
for( entry in (roleMap) ) {
printHtmlPart(11)
invokeTag('set','g',31,['var':("roleName"),'value':(uiPropertiesStrategy.getProperty(entry.key, 'authority'))],-1)
printHtmlPart(12)
invokeTag('checkBox','g',33,['name':(roleName),'value':(entry.value)],-1)
printHtmlPart(9)
createTagBody(6, {->
expressionOut.print(roleName)
})
invokeTag('link','g',34,['controller':("role"),'action':("edit"),'id':(entry.key.id)],6)
printHtmlPart(13)
}
printHtmlPart(7)
})
invokeTag('tab','s2ui',37,['name':("roles"),'height':("275")],4)
printHtmlPart(6)
})
invokeTag('tabs','s2ui',38,['elementId':("tabs"),'height':("375"),'data':(tabData)],3)
printHtmlPart(14)
invokeTag('submitButton','s2ui',40,[:],-1)
printHtmlPart(6)
if(true && (user)) {
printHtmlPart(6)
invokeTag('deleteButton','s2ui',42,[:],-1)
printHtmlPart(6)
}
printHtmlPart(6)
if(true && (canRunAs)) {
printHtmlPart(15)
expressionOut.print(message(code:'spring.security.ui.runas.submit'))
printHtmlPart(16)
}
printHtmlPart(17)
})
invokeTag('form','s2ui',48,['type':("update"),'beanName':("user"),'focus':("username"),'class':("button-style")],2)
printHtmlPart(3)
if(true && (user)) {
printHtmlPart(3)
invokeTag('deleteButtonForm','s2ui',50,['instanceId':(user.id)],-1)
printHtmlPart(3)
}
printHtmlPart(3)
if(true && (canRunAs)) {
printHtmlPart(18)
expressionOut.print(request.contextPath)
expressionOut.print(securityConfig.switchUser.switchUserUrl)
printHtmlPart(19)
invokeTag('hiddenField','g',54,['name':(securityConfig.switchUser.usernameParameter),'value':(username)],-1)
printHtmlPart(20)
}
printHtmlPart(0)
createClosureForHtmlPart(21, 2)
invokeTag('documentReady','s2ui',63,[:],2)
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',64,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1451942533509L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
