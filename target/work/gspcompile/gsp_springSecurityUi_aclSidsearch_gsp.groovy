import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclSidsearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclSid/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.aclSid.search")],-1)
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
invokeTag('message','g',11,['code':("aclSid.sid.label"),'default':("SID")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("sid"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(sid)],-1)
printHtmlPart(7)
invokeTag('message','g',16,['code':("spring.security.ui.search.true")],-1)
printHtmlPart(8)
invokeTag('message','g',17,['code':("spring.security.ui.search.false")],-1)
printHtmlPart(8)
invokeTag('message','g',18,['code':("spring.security.ui.search.either")],-1)
printHtmlPart(9)
invokeTag('message','g',21,['code':("aclSid.principal.label"),'default':("Principal")],-1)
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
out.print(it.radio)
printHtmlPart(12)
})
invokeTag('radioGroup','g',24,['name':("principal"),'labels':(['','','']),'values':([1,-1,0]),'value':(principal ?: 0)],4)
printHtmlPart(13)
})
invokeTag('searchForm','s2ui',26,['colspan':("4")],3)
printHtmlPart(14)
})
invokeTag('formContainer','s2ui',27,['type':("search"),'beanType':("aclSid")],2)
printHtmlPart(14)
if(true && (searched)) {
printHtmlPart(15)
invokeTag('sortableColumn','s2ui',33,['property':("sid"),'titleDefault':("SID")],-1)
printHtmlPart(16)
invokeTag('sortableColumn','s2ui',34,['property':("principal"),'titleDefault':("Principal")],-1)
printHtmlPart(17)
loop:{
int i = 0
for( aclSid in (results) ) {
printHtmlPart(18)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(19)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(aclSid, 'sid'))
})
invokeTag('link','g',40,['action':("edit"),'id':(aclSid.id)],4)
printHtmlPart(8)
invokeTag('formatBoolean','s2ui',41,['bean':(aclSid),'name':("principal")],-1)
printHtmlPart(20)
i++
}
}
printHtmlPart(21)
invokeTag('paginate','s2ui',47,['total':(totalCount)],-1)
printHtmlPart(14)
}
printHtmlPart(22)
invokeTag('ajaxSearch','s2ui',50,['paramName':("sid")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',51,[:],1)
printHtmlPart(23)
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
