import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclEntrysearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclEntry/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.aclEntry.search")],-1)
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
invokeTag('message','g',11,['code':("aclEntry.aclObjectIdentity.label"),'default':("AclObjectIdentity")],-1)
printHtmlPart(6)
invokeTag('textField','g',12,['name':("aclObjectIdentity.id"),'id':("aclObjectIdentity"),'size':("50"),'maxlength':("255"),'value':(aclObjectIdentity)],-1)
printHtmlPart(7)
invokeTag('message','g',15,['code':("aclEntry.aceOrder.label"),'default':("Ace Order")],-1)
printHtmlPart(6)
invokeTag('textField','g',16,['name':("aceOrder"),'size':("50"),'maxlength':("255"),'value':(aceOrder)],-1)
printHtmlPart(7)
invokeTag('message','g',19,['code':("aclEntry.sid.label"),'default':("SID")],-1)
printHtmlPart(8)
invokeTag('select','g',21,['name':("sid.id"),'id':("sid"),'from':(sids),'optionKey':("id"),'optionValue':("sid"),'value':(sid),'noSelection':(['null': 'All'])],-1)
printHtmlPart(9)
invokeTag('message','g',25,['code':("aclEntry.mask.label"),'default':("Mask")],-1)
printHtmlPart(6)
invokeTag('textField','g',26,['name':("mask"),'size':("50"),'maxlength':("255"),'value':(mask)],-1)
printHtmlPart(10)
invokeTag('message','g',30,['code':("spring.security.ui.search.true")],-1)
printHtmlPart(11)
invokeTag('message','g',31,['code':("spring.security.ui.search.false")],-1)
printHtmlPart(11)
invokeTag('message','g',32,['code':("spring.security.ui.search.either")],-1)
printHtmlPart(7)
invokeTag('message','g',35,['code':("aclEntry.granting.label"),'default':("Granting")],-1)
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
out.print(it.radio)
printHtmlPart(14)
})
invokeTag('radioGroup','g',38,['name':("granting"),'labels':(['','','']),'values':([1,-1,0]),'value':(granting ?: 0)],4)
printHtmlPart(15)
invokeTag('message','g',41,['code':("aclEntry.auditSuccess.label"),'default':("Audit Success")],-1)
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
out.print(it.radio)
printHtmlPart(14)
})
invokeTag('radioGroup','g',44,['name':("auditSuccess"),'labels':(['','','']),'values':([1,-1,0]),'value':(auditSuccess ?: 0)],4)
printHtmlPart(15)
invokeTag('message','g',47,['code':("aclEntry.auditFailure.label"),'default':("Audit Failure")],-1)
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
out.print(it.radio)
printHtmlPart(14)
})
invokeTag('radioGroup','g',50,['name':("auditFailure"),'labels':(['','','']),'values':([1,-1,0]),'value':(auditFailure ?: 0)],4)
printHtmlPart(16)
})
invokeTag('searchForm','s2ui',52,['colspan':("4")],3)
printHtmlPart(17)
})
invokeTag('formContainer','s2ui',53,['type':("search"),'beanType':("aclEntry"),'focus':("aclObjectIdentity"),'height':("500")],2)
printHtmlPart(17)
if(true && (searched)) {
printHtmlPart(18)
invokeTag('sortableColumn','s2ui',59,['property':("id"),'titleDefault':("ID")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',60,['property':("aclObjectIdentity.id"),'titleDefault':("AclObjectIdentity")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',61,['property':("aceOrder"),'titleDefault':("Ace Order")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',62,['property':("sid.id"),'titleDefault':("SID")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',63,['property':("mask"),'titleDefault':("Mask")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',64,['property':("granting"),'titleDefault':("Granting")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',65,['property':("auditSuccess"),'titleDefault':("Audit Success")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','s2ui',66,['property':("auditFailure"),'titleDefault':("Audit Failure")],-1)
printHtmlPart(20)
loop:{
int i = 0
for( entry in (results) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(22)
invokeTag('set','g',72,['var':("entryAclObjectIdentity"),'value':(uiPropertiesStrategy.getProperty(entry, "aclObjectIdentity"))],-1)
printHtmlPart(19)
invokeTag('set','g',73,['var':("entrySid"),'value':(uiPropertiesStrategy.getProperty(entry, "sid"))],-1)
printHtmlPart(23)
createTagBody(4, {->
expressionOut.print(entry.id)
})
invokeTag('link','g',74,['action':("edit"),'id':(entry.id)],4)
printHtmlPart(24)
createTagBody(4, {->
expressionOut.print(entryAclObjectIdentity.id)
})
invokeTag('link','g',75,['action':("edit"),'controller':("aclObjectIdentity"),'id':(entryAclObjectIdentity.id)],4)
printHtmlPart(24)
expressionOut.print(entry.aceOrder)
printHtmlPart(24)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(entrySid, 'sid'))
})
invokeTag('link','g',77,['action':("edit"),'controller':("aclSid"),'id':(entrySid.id)],4)
printHtmlPart(24)
expressionOut.print(permissionFactory.buildFromMask(entry.mask))
printHtmlPart(24)
invokeTag('formatBoolean','s2ui',79,['bean':(entry),'name':("granting")],-1)
printHtmlPart(24)
invokeTag('formatBoolean','s2ui',80,['bean':(entry),'name':("auditSuccess")],-1)
printHtmlPart(24)
invokeTag('formatBoolean','s2ui',81,['bean':(entry),'name':("auditFailure")],-1)
printHtmlPart(25)
i++
}
}
printHtmlPart(26)
invokeTag('paginate','s2ui',87,['total':(totalCount)],-1)
printHtmlPart(17)
}
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',90,[:],1)
printHtmlPart(28)
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
