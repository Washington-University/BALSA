import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='twitter4j', version='4.0.4.3')
class gsp_twitter4j_twitter4jstatus_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/twitter4j-4.0.4.3/grails-app/views/twitter4j/status.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
if(true && (flash.error)) {
printHtmlPart(5)
expressionOut.print(flash.error)
printHtmlPart(6)
}
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(7)
expressionOut.print(flash.message)
printHtmlPart(8)
}
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('textField','g',17,['name':("statusMessage"),'value':(statusMessage)],-1)
printHtmlPart(10)
invokeTag('actionSubmit','g',18,['action':("update"),'value':("Update status")],-1)
printHtmlPart(11)
})
invokeTag('form','g',19,['method':("get")],2)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',20,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1355392142768L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
