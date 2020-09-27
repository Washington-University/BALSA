import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-ui', version='1.0-RC3')
class gsp_springSecurityUi_securityInfoconfig_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-ui-1.0-RC3/grails-app/views/securityInfo/config.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',4,['code':("spring.security.ui.menu.securityInfo.config")],-1)
})
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(1)
invokeTag('stylesheet','s2ui',5,['src':("jquery.dataTables.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',10,['code':("spring.security.ui.menu.securityInfo.config")],-1)
printHtmlPart(4)
invokeTag('message','g',13,['code':("spring.security.ui.info.config.header.name")],-1)
printHtmlPart(5)
invokeTag('message','g',14,['code':("spring.security.ui.info.config.header.value")],-1)
printHtmlPart(6)
for( entry in (conf) ) {
printHtmlPart(7)

def key = entry.key
if (key.startsWith('failureHandler.exceptionMappings.')) {
	key = key - 'failureHandler.exceptionMappings.'
	key = 'failureHandler.exceptionMappings. ' + key.replaceAll('\\.', '\\. ')
}
def value = entry.value
if (value instanceof Class) {
	value = value.name.replaceAll('\\.', '\\. ')
}

printHtmlPart(8)
expressionOut.print(key)
printHtmlPart(9)
expressionOut.print(value)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('deferredScript','s2ui',38,['src':("jquery/jquery.dataTables.js")],-1)
printHtmlPart(2)
createClosureForHtmlPart(12, 2)
invokeTag('documentReady','s2ui',41,[:],2)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',42,[:],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450678956593L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
