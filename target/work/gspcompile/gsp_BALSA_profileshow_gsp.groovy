import balsa.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_profileshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/profile/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('fieldValue','g',7,['bean':(profileInstance),'field':("fullname")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(profileInstance.fullname ?: profileInstance.user.username)
printHtmlPart(5)
if(true && (ownProfile)) {
printHtmlPart(6)
createClosureForHtmlPart(7, 3)
invokeTag('link','g',15,['class':("btn btn-default"),'action':("edit"),'resource':(profileInstance)],3)
printHtmlPart(8)
createClosureForHtmlPart(9, 3)
invokeTag('link','g',17,['class':("btn btn-default"),'controller':("terms"),'action':("mine")],3)
printHtmlPart(6)
createClosureForHtmlPart(10, 3)
invokeTag('link','g',18,['class':("btn btn-default"),'controller':("download"),'action':("mine")],3)
printHtmlPart(11)
invokeTag('createLink','g',20,['controller':("register"),'action':("delete")],-1)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('fieldValue','g',30,['bean':(profileInstance),'field':("fullname")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',35,['bean':(profileInstance),'field':("user.username")],-1)
printHtmlPart(15)
if(true && (ownProfile || profileInstance.contactInfoPublic)) {
printHtmlPart(6)
if(true && (profileInstance.emailAddress)) {
printHtmlPart(16)
expressionOut.print(profileInstance.emailAddress)
printHtmlPart(17)
invokeTag('fieldValue','g',42,['bean':(profileInstance),'field':("emailAddress")],-1)
printHtmlPart(18)
}
printHtmlPart(6)
if(true && (profileInstance.phone)) {
printHtmlPart(19)
invokeTag('fieldValue','g',48,['bean':(profileInstance),'field':("phone")],-1)
printHtmlPart(20)
}
printHtmlPart(6)
}
printHtmlPart(21)
if(true && (profileInstance.citednames && profileInstance.citednames.size() > 0)) {
printHtmlPart(22)
for( _it663531845 in (profileInstance.citednames) ) {
changeItVariable(_it663531845)
printHtmlPart(23)
expressionOut.print(it)
printHtmlPart(24)
}
printHtmlPart(25)
}
printHtmlPart(21)
if(true && (profileInstance.institutions && profileInstance.institutions.size() > 0)) {
printHtmlPart(26)
for( _it715194034 in (profileInstance.institutions) ) {
changeItVariable(_it715194034)
printHtmlPart(23)
expressionOut.print(it.canonicalName)
printHtmlPart(24)
}
printHtmlPart(25)
}
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('formRemote','g',102,['class':("form-horizontal"),'url':([controller: 'register', action: 'changePassword']),'method':("POST"),'id':("changePasswordForm"),'name':("changePasswordForm"),'autocomplete':("off"),'onSuccess':("closeModal();updateRecentActivity();")],2)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',106,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1555971800058L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
