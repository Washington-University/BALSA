import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='twitter4j', version='4.0.4.3')
class gsp_twitter4j_twitter4jindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/twitter4j-4.0.4.3/grails-app/views/twitter4j/index.gsp" }
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
createTagBody(2, {->
printHtmlPart(5)
invokeTag('textField','g',10,['name':("consumerKey"),'value':(consumerKey)],-1)
printHtmlPart(6)
invokeTag('textField','g',11,['name':("consumerSecret"),'value':(consumerSecret)],-1)
printHtmlPart(7)
invokeTag('actionSubmit','g',12,['action':("requestToken"),'value':("Request token")],-1)
printHtmlPart(8)
})
invokeTag('form','g',13,['method':("get")],2)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',14,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1410636820768L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
