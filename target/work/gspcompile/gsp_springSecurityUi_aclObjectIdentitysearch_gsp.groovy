import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_aclObjectIdentitysearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/aclObjectIdentity/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
invokeTag('title','s2ui',4,['messageCode':("spring.security.ui.aclObjectIdentity.search")],-1)
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
invokeTag('message','g',11,['code':("aclObjectIdentity.aclClass.label"),'default':("AclClass")],-1)
printHtmlPart(6)
invokeTag('select','g',13,['name':("aclClass.id"),'id':("aclClass"),'from':(classes),'optionKey':("id"),'optionValue':("className"),'value':(aclClass),'noSelection':(['null': 'All'])],-1)
printHtmlPart(7)
invokeTag('message','g',17,['code':("aclObjectIdentity.objectId.label"),'default':("Object ID")],-1)
printHtmlPart(8)
invokeTag('textField','g',18,['name':("objectId"),'size':("50"),'maxlength':("255"),'value':(objectId)],-1)
printHtmlPart(9)
invokeTag('message','g',21,['code':("aclObjectIdentity.owner.label"),'default':("Owner")],-1)
printHtmlPart(6)
invokeTag('select','g',23,['name':("owner.id"),'id':("owner"),'from':(sids),'optionKey':("id"),'optionValue':("sid"),'value':(pageScope.owner),'noSelection':(['null': 'All'])],-1)
printHtmlPart(7)
invokeTag('message','g',27,['code':("aclObjectIdentity.parent.label"),'default':("Parent")],-1)
printHtmlPart(8)
invokeTag('textField','g',28,['name':("parent"),'size':("50"),'maxlength':("255"),'value':(parent)],-1)
printHtmlPart(10)
invokeTag('message','g',32,['code':("spring.security.ui.search.true")],-1)
printHtmlPart(11)
invokeTag('message','g',33,['code':("spring.security.ui.search.false")],-1)
printHtmlPart(11)
invokeTag('message','g',34,['code':("spring.security.ui.search.either")],-1)
printHtmlPart(9)
invokeTag('message','g',37,['code':("aclObjectIdentity.entriesInheriting.label"),'default':("Entries Inheriting")],-1)
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
out.print(it.radio)
printHtmlPart(14)
})
invokeTag('radioGroup','g',40,['name':("entriesInheriting"),'labels':(['','','']),'values':([1,-1,0]),'value':(entriesInheriting ?: 0)],4)
printHtmlPart(15)
})
invokeTag('searchForm','s2ui',42,['colspan':("4")],3)
printHtmlPart(16)
})
invokeTag('formContainer','s2ui',43,['type':("search"),'beanType':("aclObjectIdentity"),'focus':("objectId")],2)
printHtmlPart(16)
if(true && (searched)) {
printHtmlPart(17)
invokeTag('sortableColumn','s2ui',49,['property':("id"),'titleDefault':("ID")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','s2ui',50,['property':("aclClass.className"),'titleDefault':("AclClass")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','s2ui',51,['property':("objectId"),'titleDefault':("Object ID")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','s2ui',52,['property':("entriesInheriting"),'titleDefault':("Entries Inheriting")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','s2ui',53,['property':("owner.sid"),'titleDefault':("Owner")],-1)
printHtmlPart(19)
invokeTag('message','g',54,['code':("parent.label"),'default':("Parent")],-1)
printHtmlPart(20)
loop:{
int i = 0
for( oid in (results) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(22)
createTagBody(4, {->
expressionOut.print(oid.id)
})
invokeTag('link','g',60,['action':("edit"),'id':(oid.id)],4)
printHtmlPart(23)
invokeTag('set','g',61,['var':("oidAclClass"),'value':(uiPropertiesStrategy.getProperty(oid, "aclClass"))],-1)
printHtmlPart(24)
createTagBody(4, {->
expressionOut.print(uiPropertiesStrategy.getProperty(oidAclClass, 'className'))
})
invokeTag('link','g',62,['action':("edit"),'controller':("aclClass"),'id':(oidAclClass.id)],4)
printHtmlPart(25)
expressionOut.print(uiPropertiesStrategy.getProperty(oid, 'objectId'))
printHtmlPart(25)
invokeTag('formatBoolean','s2ui',64,['bean':(oid),'name':("entriesInheriting")],-1)
printHtmlPart(26)
invokeTag('set','g',66,['var':("oidOwner"),'value':(uiPropertiesStrategy.getProperty(oid, "owner"))],-1)
printHtmlPart(18)
invokeTag('set','g',67,['var':("isPrincipal"),'value':(uiPropertiesStrategy.getProperty(oidOwner, "principal"))],-1)
printHtmlPart(18)
invokeTag('set','g',68,['var':("oidOwnerSid"),'value':(uiPropertiesStrategy.getProperty(oidOwner, "sid"))],-1)
printHtmlPart(18)
if(true && (oidOwner && isPrincipal)) {
printHtmlPart(27)
createTagBody(5, {->
expressionOut.print(oidOwnerSid)
})
invokeTag('link','g',70,['action':("edit"),'controller':("user"),'params':([username: oidOwnerSid])],5)
printHtmlPart(18)
}
printHtmlPart(18)
if(true && (oidOwner && !isPrincipal)) {
printHtmlPart(27)
createTagBody(5, {->
expressionOut.print(oidOwnerSid)
})
invokeTag('link','g',73,['action':("edit"),'controller':("role"),'params':([authority: oidOwnerSid])],5)
printHtmlPart(18)
}
printHtmlPart(28)
invokeTag('set','g',78,['var':("oidParent"),'value':(uiPropertiesStrategy.getProperty(oid, 'parent'))],-1)
printHtmlPart(18)
if(true && (oidParent)) {
printHtmlPart(27)
createTagBody(5, {->
expressionOut.print(oidParent.id)
})
invokeTag('link','g',80,['action':("edit"),'id':(oidParent.id)],5)
printHtmlPart(18)
}
printHtmlPart(29)
i++
}
}
printHtmlPart(30)
invokeTag('paginate','s2ui',89,['total':(totalCount)],-1)
printHtmlPart(16)
}
printHtmlPart(31)
})
invokeTag('captureBody','sitemesh',92,[:],1)
printHtmlPart(32)
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
