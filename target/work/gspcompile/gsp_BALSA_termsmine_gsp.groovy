import balsa.security.Approval
import balsa.Dataset
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_termsmine_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/terms/mine.gsp" }
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
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',14,['class':("btn btn-default"),'controller':("profile"),'action':("mine")],2)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',15,['class':("btn btn-default"),'controller':("download"),'action':("mine")],2)
printHtmlPart(9)
for( _it1693662702 in (terms) ) {
changeItVariable(_it1693662702)
printHtmlPart(10)
expressionOut.print(it.id)
printHtmlPart(11)
expressionOut.print(it.id)
printHtmlPart(12)
expressionOut.print(it.title)
printHtmlPart(13)
expressionOut.print(it.id)
printHtmlPart(14)
expressionOut.print(it.id)
printHtmlPart(15)
expressionOut.print(it.id)
printHtmlPart(16)
createTagBody(3, {->
invokeTag('fieldValue','g',30,['bean':(it),'field':("contents")],-1)
})
invokeTag('encodeAs','g',30,['codec':("PreserveWhitespace")],3)
printHtmlPart(17)
expressionOut.print(it.id)
printHtmlPart(18)
}
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',48,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1536855066316L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
