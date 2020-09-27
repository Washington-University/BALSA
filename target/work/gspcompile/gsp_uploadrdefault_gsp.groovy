import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='uploadr', version='1.2.6')
class gsp_uploadrdefault_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/uploadr-1.2.6/grails-app/views/default.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('javascript','asset',5,['src':("uploadr.manifest.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',6,['src':("uploadr.demo.manifest.js")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',7,['href':("uploadr.manifest.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',8,['href':("uploadr.demo.manifest.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('include','g',11,['view':("upload/_default.gsp")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',12,[:],1)
printHtmlPart(3)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450849804734L
public static final String EXPRESSION_CODEC = 'raw'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
