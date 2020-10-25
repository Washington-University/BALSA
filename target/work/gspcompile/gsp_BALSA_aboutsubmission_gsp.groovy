import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_aboutsubmission_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/about/submission.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',13,['class':("btn btn-default"),'action':("index")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',37,['controller':("register"),'action':("register"),'target':("_blank")],2)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',38,['controller':("terms"),'action':("submission"),'target':("_blank")],2)
printHtmlPart(10)
invokeTag('img','g',77,['file':("scenes.png"),'height':("20"),'width':("20")],-1)
printHtmlPart(11)
invokeTag('img','g',78,['file':("help.png"),'height':("20"),'width':("20")],-1)
printHtmlPart(12)
invokeTag('img','g',122,['file':("upload.png"),'style':("display:block;margin:auto;border:1px solid gray")],-1)
printHtmlPart(13)
invokeTag('img','g',123,['file':("create.png"),'style':("display:block;margin:auto;border:1px solid gray")],-1)
printHtmlPart(14)
invokeTag('img','g',164,['file':("manage.png"),'class':("pull-right"),'style':("padding-left:15px")],-1)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',174,['action':("editing")],2)
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',366,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1601580769763L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
