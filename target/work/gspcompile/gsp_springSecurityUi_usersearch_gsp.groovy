import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_usersearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/user/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.user.search")],-1)
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
invokeTag('message','g',11,['code':("user.username.label"),'default':("Username")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("username"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(username)],-1)
printHtmlPart(7)
invokeTag('message','g',16,['code':("spring.security.ui.search.true")],-1)
printHtmlPart(8)
invokeTag('message','g',17,['code':("spring.security.ui.search.false")],-1)
printHtmlPart(8)
invokeTag('message','g',18,['code':("spring.security.ui.search.either")],-1)
printHtmlPart(9)
invokeTag('message','g',21,['code':("user.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
out.print(it.radio)
printHtmlPart(12)
})
invokeTag('radioGroup','g',24,['name':("enabled"),'labels':(['','','']),'values':([1,-1,0]),'value':(enabled ?: 0)],4)
printHtmlPart(13)
invokeTag('message','g',27,['code':("user.accountExpired.label"),'default':("Account Expired")],-1)
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
out.print(it.radio)
printHtmlPart(12)
})
invokeTag('radioGroup','g',30,['name':("accountExpired"),'labels':(['','','']),'values':([1,-1,0]),'value':(accountExpired ?: 0)],4)
printHtmlPart(13)
invokeTag('message','g',33,['code':("user.accountLocked.label"),'default':("Account Locked")],-1)
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
out.print(it.radio)
printHtmlPart(12)
})
invokeTag('radioGroup','g',36,['name':("accountLocked"),'labels':(['','','']),'values':([1,-1,0]),'value':(accountLocked ?: 0)],4)
printHtmlPart(13)
invokeTag('message','g',39,['code':("user.passwordExpired.label"),'default':("Password Expired")],-1)
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
out.print(it.radio)
printHtmlPart(12)
})
invokeTag('radioGroup','g',42,['name':("passwordExpired"),'labels':(['','','']),'values':([1,-1,0]),'value':(passwordExpired ?: 0)],4)
printHtmlPart(14)
})
invokeTag('searchForm','s2ui',44,['colspan':("4")],3)
printHtmlPart(15)
})
invokeTag('formContainer','s2ui',45,['type':("search"),'beanType':("user")],2)
printHtmlPart(15)
if(true && (searched)) {
printHtmlPart(16)
invokeTag('sortableColumn','s2ui',51,['property':("username"),'titleDefault':("Username")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','s2ui',52,['property':("enabled"),'titleDefault':("Enabled")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','s2ui',53,['property':("accountExpired"),'titleDefault':("Account Expired")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','s2ui',54,['property':("accountLocked"),'titleDefault':("Account Locked")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','s2ui',55,['property':("passwordExpired"),'titleDefault':("Password Expired")],-1)
printHtmlPart(18)
loop:{
int i = 0
for( user in (results) ) {
printHtmlPart(19)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(20)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(user, 'username'))
})
invokeTag('link','g',61,['action':("edit"),'id':(user.id)],4)
printHtmlPart(21)
invokeTag('formatBoolean','s2ui',62,['bean':(user),'name':("enabled")],-1)
printHtmlPart(21)
invokeTag('formatBoolean','s2ui',63,['bean':(user),'name':("accountExpired")],-1)
printHtmlPart(21)
invokeTag('formatBoolean','s2ui',64,['bean':(user),'name':("accountLocked")],-1)
printHtmlPart(21)
invokeTag('formatBoolean','s2ui',65,['bean':(user),'name':("passwordExpired")],-1)
printHtmlPart(22)
i++
}
}
printHtmlPart(23)
invokeTag('paginate','s2ui',71,['total':(totalCount)],-1)
printHtmlPart(15)
}
printHtmlPart(24)
invokeTag('ajaxSearch','s2ui',74,['paramName':("username")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',75,[:],1)
printHtmlPart(25)
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
