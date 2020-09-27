import balsa.security.Approval
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_termssubmission_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/terms/submission.gsp" }
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
createTagBody(3, {->
expressionOut.print(submissionTerms.title)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(submissionTerms.title)
printHtmlPart(4)
createTagBody(2, {->
invokeTag('fieldValue','g',12,['bean':(submissionTerms),'field':("contents")],-1)
})
invokeTag('encodeAs','g',12,['codec':("PreserveWhitespace")],2)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
if(true && (alreadyAgreed)) {
printHtmlPart(7)
}
else {
printHtmlPart(6)
createClosureForHtmlPart(8, 4)
invokeTag('remoteLink','g',21,['controller':("terms"),'action':("agree"),'id':(submissionTerms.id),'onSuccess':("window.location.reload(true)")],4)
printHtmlPart(6)
}
printHtmlPart(6)
})
invokeTag('ifLoggedIn','sec',23,[:],2)
printHtmlPart(6)
createClosureForHtmlPart(9, 2)
invokeTag('ifNotLoggedIn','sec',26,[:],2)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',30,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1570057410145L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
