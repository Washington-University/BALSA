import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_persistentLoginsearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/persistentLogin/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.persistentLogin.search")],-1)
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
invokeTag('message','g',11,['code':("persistentLogin.username.label"),'default':("Username")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("username"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(username)],-1)
printHtmlPart(7)
invokeTag('message','g',15,['code':("persistentLogin.token.label"),'default':("Token")],-1)
printHtmlPart(6)
invokeTag('textField','g',16,['name':("token"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(token)],-1)
printHtmlPart(7)
invokeTag('message','g',19,['code':("persistentLogin.series.label"),'default':("Series")],-1)
printHtmlPart(6)
invokeTag('textField','g',20,['name':("series"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(series)],-1)
printHtmlPart(8)
})
invokeTag('searchForm','s2ui',22,['colspan':("2")],3)
printHtmlPart(9)
})
invokeTag('formContainer','s2ui',23,['type':("search"),'beanType':("persistentLogin"),'height':("350")],2)
printHtmlPart(9)
if(true && (searched)) {
printHtmlPart(10)
invokeTag('sortableColumn','s2ui',29,['property':("series"),'titleDefault':("Series")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','s2ui',30,['property':("username"),'titleDefault':("Username")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','s2ui',31,['property':("token"),'titleDefault':("Token")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','s2ui',32,['property':("lastUsed"),'titleDefault':("Last Used")],-1)
printHtmlPart(12)
loop:{
int i = 0
for( persistentLogin in (results) ) {
printHtmlPart(13)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(14)
createTagBody(4, {->
expressionOut.print(persistentLogin.series)
})
invokeTag('link','g',38,['action':("edit"),'id':(persistentLogin.series)],4)
printHtmlPart(15)
invokeTag('set','g',39,['var':("persistentLoginUsername"),'value':(uiPropertiesStrategy.getProperty(persistentLogin, 'username'))],-1)
printHtmlPart(16)
createTagBody(4, {->
expressionOut.print(persistentLoginUsername)
})
invokeTag('link','g',40,['controller':("user"),'action':("edit"),'params':([username: persistentLoginUsername])],4)
printHtmlPart(17)
expressionOut.print(uiPropertiesStrategy.getProperty(persistentLogin, 'token'))
printHtmlPart(17)
invokeTag('formatDate','g',42,['date':(uiPropertiesStrategy.getProperty(persistentLogin, 'lastUsed')),'formatName':("spring.security.ui.dateFormatGsp")],-1)
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
invokeTag('paginate','s2ui',48,['total':(totalCount)],-1)
printHtmlPart(9)
}
printHtmlPart(20)
invokeTag('ajaxSearch','s2ui',51,['paramName':("username")],-1)
printHtmlPart(1)
invokeTag('ajaxSearch','s2ui',52,['paramName':("token"),'focus':("false")],-1)
printHtmlPart(1)
invokeTag('ajaxSearch','s2ui',53,['paramName':("series"),'focus':("false")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',54,[:],1)
printHtmlPart(21)
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
