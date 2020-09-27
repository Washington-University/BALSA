import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_requestmapsearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/requestmap/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.requestmap.search")],-1)
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
invokeTag('message','g',11,['code':("requestmap.url.label"),'default':("URL")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("url"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(url)],-1)
printHtmlPart(7)
invokeTag('message','g',15,['code':("requestmap.configAttribute.label"),'default':("Config Attribute")],-1)
printHtmlPart(6)
invokeTag('textField','g',16,['name':("configAttribute"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(configAttribute)],-1)
printHtmlPart(8)
if(true && (hasHttpMethod)) {
printHtmlPart(5)
invokeTag('message','g',20,['code':("requestmap.httpMethod.label"),'default':("HttpMethod")],-1)
printHtmlPart(9)
invokeTag('select','g',22,['name':("httpMethod"),'from':(org.springframework.http.HttpMethod.values()),'value':(httpMethod),'noSelection':(['null': 'All'])],-1)
printHtmlPart(10)
}
printHtmlPart(4)
})
invokeTag('searchForm','s2ui',26,['colspan':("2")],3)
printHtmlPart(11)
})
invokeTag('formContainer','s2ui',27,['type':("search"),'beanType':("requestmap"),'height':("350")],2)
printHtmlPart(11)
if(true && (searched)) {
printHtmlPart(12)
invokeTag('sortableColumn','s2ui',33,['property':("url"),'titleDefault':("URL")],-1)
printHtmlPart(13)
invokeTag('sortableColumn','s2ui',34,['property':("configAttribute"),'titleDefault':("Config Attribute")],-1)
printHtmlPart(13)
if(true && (hasHttpMethod)) {
printHtmlPart(13)
invokeTag('sortableColumn','s2ui',36,['property':("httpMethod"),'titleDefault':("HttpMethod")],-1)
printHtmlPart(13)
}
printHtmlPart(14)
loop:{
int i = 0
for( requestmap in (results) ) {
printHtmlPart(15)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(16)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(requestmap, 'url'))
})
invokeTag('link','g',43,['action':("edit"),'id':(requestmap.id)],4)
printHtmlPart(17)
expressionOut.print(uiPropertiesStrategy.getProperty(requestmap, 'configAttribute'))
printHtmlPart(18)
if(true && (hasHttpMethod)) {
printHtmlPart(19)
expressionOut.print(uiPropertiesStrategy.getProperty(requestmap, 'httpMethod'))
printHtmlPart(18)
}
printHtmlPart(20)
i++
}
}
printHtmlPart(21)
invokeTag('paginate','s2ui',53,['total':(totalCount)],-1)
printHtmlPart(11)
}
printHtmlPart(22)
invokeTag('ajaxSearch','s2ui',56,['paramName':("url")],-1)
printHtmlPart(1)
invokeTag('ajaxSearch','s2ui',57,['paramName':("configAttribute"),'focus':("false")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',58,[:],1)
printHtmlPart(23)
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
