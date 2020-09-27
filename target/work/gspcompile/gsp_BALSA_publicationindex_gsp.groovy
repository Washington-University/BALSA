import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_publicationindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/publication/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
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
invokeTag('link','g',12,['class':("btn btn-default"),'action':("batchAdd")],2)
printHtmlPart(6)
for( publication in (publications) ) {
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
expressionOut.print(publication.officialName)
printHtmlPart(9)
expressionOut.print(publication.abbrNames.join(','))
printHtmlPart(10)
if(true && (!publication.approved)) {
printHtmlPart(11)
createClosureForHtmlPart(12, 5)
invokeTag('link','g',46,['action':("approve"),'id':(publication.id),'class':("btn btn-default"),'type':("submit"),'style':("width:70px")],5)
printHtmlPart(11)
}
printHtmlPart(13)
invokeTag('createLink','g',49,['action':("delete"),'id':(publication.id)],-1)
printHtmlPart(14)
})
invokeTag('form','g',54,['action':("update"),'id':(publication.id),'style':("display: table-row")],3)
printHtmlPart(7)
}
printHtmlPart(7)
createClosureForHtmlPart(15, 2)
invokeTag('form','g',70,['action':("create"),'style':("display: table-row")],2)
printHtmlPart(16)
for( publication in (publications) ) {
printHtmlPart(17)
expressionOut.print(publication.officialName)
printHtmlPart(18)
expressionOut.print(publication.id)
printHtmlPart(19)
}
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',77,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1521150272202L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
