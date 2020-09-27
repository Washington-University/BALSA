import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSAindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/index.gsp" }
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
createTagBody(2, {->
printHtmlPart(5)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',25,['url':("/about")],3)
printHtmlPart(7)
createClosureForHtmlPart(8, 3)
invokeTag('link','g',26,['url':("/about/submission")],3)
printHtmlPart(9)
})
invokeTag('ifNotLoggedIn','sec',28,[:],2)
printHtmlPart(10)
expressionOut.print(createLink(controller: 'news', action: 'latest'))
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',39,['controller':("news")],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',46,['controller':("about")],3)
printHtmlPart(15)
createClosureForHtmlPart(16, 3)
invokeTag('link','g',47,['controller':("about"),'action':("help")],3)
printHtmlPart(15)
createClosureForHtmlPart(8, 3)
invokeTag('link','g',48,['controller':("about"),'action':("submission")],3)
printHtmlPart(17)
createTagBody(3, {->
printHtmlPart(18)
createClosureForHtmlPart(19, 4)
invokeTag('link','g',50,['controller':("terms"),'action':("submission")],4)
printHtmlPart(17)
})
invokeTag('ifNotGranted','sec',51,['roles':("ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER")],3)
printHtmlPart(20)
createTagBody(3, {->
printHtmlPart(21)
createClosureForHtmlPart(22, 4)
invokeTag('link','g',55,['controller':("study"),'action':("mine")],4)
printHtmlPart(17)
})
invokeTag('ifAnyGranted','sec',56,['roles':("ROLE_ADMIN,ROLE_CURATOR,ROLE_SUBMITTER")],3)
printHtmlPart(23)
createClosureForHtmlPart(24, 3)
invokeTag('link','g',58,['controller':("study")],3)
printHtmlPart(15)
createClosureForHtmlPart(25, 3)
invokeTag('link','g',59,['controller':("reference")],3)
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(27)
createClosureForHtmlPart(28, 4)
invokeTag('link','g',65,['url':("/curation")],4)
printHtmlPart(29)
createClosureForHtmlPart(30, 4)
invokeTag('link','g',67,['controller':("tagging"),'action':("categories")],4)
printHtmlPart(31)
createClosureForHtmlPart(32, 4)
invokeTag('link','g',68,['controller':("news"),'action':("create")],4)
printHtmlPart(31)
createClosureForHtmlPart(33, 4)
invokeTag('link','g',69,['controller':("curation"),'action':("tweet")],4)
printHtmlPart(34)
createClosureForHtmlPart(35, 4)
invokeTag('link','g',75,['controller':("institution")],4)
printHtmlPart(31)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',76,['controller':("publication")],4)
printHtmlPart(37)
})
invokeTag('ifAnyGranted','sec',79,['roles':("ROLE_CURATOR")],3)
printHtmlPart(20)
createTagBody(3, {->
printHtmlPart(38)
createClosureForHtmlPart(39, 4)
invokeTag('link','g',85,['controller':("download"),'action':("stats")],4)
printHtmlPart(31)
createClosureForHtmlPart(40, 4)
invokeTag('link','g',86,['controller':("contact"),'action':("techMessages")],4)
printHtmlPart(31)
createClosureForHtmlPart(41, 4)
invokeTag('link','g',87,['controller':("contact"),'action':("resolvedTechMessages")],4)
printHtmlPart(31)
createClosureForHtmlPart(42, 4)
invokeTag('link','g',88,['controller':("securityInfo"),'action':("config")],4)
printHtmlPart(37)
})
invokeTag('ifAnyGranted','sec',91,['roles':("ROLE_ADMIN")],3)
printHtmlPart(20)
createClosureForHtmlPart(43, 3)
invokeTag('ifAnyGranted','sec',101,['roles':("ROLE_USER")],3)
printHtmlPart(44)
})
invokeTag('ifLoggedIn','sec',106,[:],2)
printHtmlPart(45)
invokeTag('render','g',112,['template':("/search/search")],-1)
printHtmlPart(46)
})
invokeTag('captureBody','sitemesh',121,[:],1)
printHtmlPart(47)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1598417859753L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
