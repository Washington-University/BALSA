import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_rolesearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/role/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.role.search")],-1)
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
invokeTag('message','g',11,['code':("role.authority.label"),'default':("Authority")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("authority"),'class':("textField"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(authority)],-1)
printHtmlPart(7)
})
invokeTag('searchForm','s2ui',14,['colspan':("2")],3)
printHtmlPart(8)
})
invokeTag('formContainer','s2ui',15,['type':("search"),'beanType':("role"),'height':("300")],2)
printHtmlPart(8)
if(true && (searched)) {
printHtmlPart(9)
invokeTag('sortableColumn','s2ui',21,['property':("authority"),'titleDefault':("Authority")],-1)
printHtmlPart(10)
loop:{
int i = 0
for( role in (results) ) {
printHtmlPart(11)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(12)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(role, 'authority'))
})
invokeTag('link','g',27,['action':("edit"),'id':(role.id)],4)
printHtmlPart(7)
i++
}
}
printHtmlPart(13)
invokeTag('paginate','s2ui',33,['total':(totalCount)],-1)
printHtmlPart(8)
}
printHtmlPart(14)
invokeTag('ajaxSearch','s2ui',36,['paramName':("authority"),'minLength':("2")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',37,[:],1)
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
