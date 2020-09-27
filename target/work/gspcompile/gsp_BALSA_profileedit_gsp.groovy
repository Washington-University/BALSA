import balsa.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_profileedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/profile/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('username','sec',11,[:],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('hiddenField','g',13,['name':("version"),'value':(profileInstance?.version)],-1)
printHtmlPart(7)
invokeTag('textField','g',16,['class':("form-control"),'name':("fullname"),'value':(profileInstance?.fullname)],-1)
printHtmlPart(8)
invokeTag('textField','g',20,['class':("form-control"),'name':("emailAddress"),'value':(profileInstance?.emailAddress)],-1)
printHtmlPart(9)
invokeTag('textField','g',24,['class':("form-control"),'name':("phone"),'value':(profileInstance?.phone)],-1)
printHtmlPart(10)
invokeTag('checkBox','g',29,['name':("contactInfoPublic"),'value':(profileInstance?.contactInfoPublic)],-1)
printHtmlPart(11)
createClosureForHtmlPart(12, 3)
invokeTag('link','g',36,['class':("btn btn-default"),'action':("show"),'id':(profileInstance.user.id)],3)
printHtmlPart(13)
})
invokeTag('form','g',39,['url':([resource:profileInstance, action:'update']),'method':("PUT")],2)
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486166057818L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
