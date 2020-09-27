import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_registrationCodesearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/registrationCode/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.registrationCode.search")],-1)
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
invokeTag('message','g',11,['code':("registrationCode.username.label"),'default':("Username")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("username"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(username)],-1)
printHtmlPart(7)
invokeTag('message','g',15,['code':("registrationCode.token.label"),'default':("Token")],-1)
printHtmlPart(6)
invokeTag('textField','g',16,['name':("token"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(token)],-1)
printHtmlPart(8)
})
invokeTag('searchForm','s2ui',18,['colspan':("2")],3)
printHtmlPart(9)
})
invokeTag('formContainer','s2ui',19,['type':("search"),'beanType':("registrationCode"),'height':("350")],2)
printHtmlPart(9)
if(true && (searched)) {
printHtmlPart(10)
invokeTag('sortableColumn','s2ui',25,['property':("token"),'titleDefault':("Token")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','s2ui',26,['property':("username"),'titleDefault':("Username")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','s2ui',27,['property':("dateCreated"),'titleDefault':("Date Created")],-1)
printHtmlPart(12)
loop:{
int i = 0
for( registrationCode in (results) ) {
printHtmlPart(13)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(14)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(registrationCode, 'token'))
})
invokeTag('link','g',33,['action':("edit"),'id':(registrationCode.id)],4)
printHtmlPart(15)
invokeTag('set','g',34,['var':("regCodeUsername"),'value':(uiPropertiesStrategy.getProperty(registrationCode, 'username'))],-1)
printHtmlPart(16)
createTagBody(4, {->
expressionOut.print(regCodeUsername)
})
invokeTag('link','g',35,['controller':("user"),'action':("edit"),'params':([username: regCodeUsername])],4)
printHtmlPart(17)
invokeTag('formatDate','g',36,['date':(uiPropertiesStrategy.getProperty(registrationCode, 'dateCreated')),'formatName':("spring.security.ui.dateFormatGsp")],-1)
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
invokeTag('paginate','s2ui',42,['total':(totalCount)],-1)
printHtmlPart(9)
}
printHtmlPart(20)
invokeTag('ajaxSearch','s2ui',45,['paramName':("username")],-1)
printHtmlPart(1)
invokeTag('ajaxSearch','s2ui',46,['paramName':("token"),'focus':("false")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(21)
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
