import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_buttons_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_buttons.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (item?.hasAccess())) {
printHtmlPart(0)
}
else {
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('ifLoggedIn','sec',7,[:],2)
printHtmlPart(1)
createClosureForHtmlPart(3, 2)
invokeTag('ifNotLoggedIn','sec',10,[:],2)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (item?.terms()?.numberOfTerms > 0)) {
printHtmlPart(6)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1586384827696L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
