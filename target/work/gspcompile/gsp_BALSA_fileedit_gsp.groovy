import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_fileedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/file/edit.gsp" }
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
createTagBody(3, {->
printHtmlPart(2)
invokeTag('fieldValue','g',5,['bean':(file),'field':("filename")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('fieldValue','g',9,['bean':(file),'field':("filename")],-1)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',12,['class':("btn btn-default"),'action':("show"),'resource':(file)],2)
printHtmlPart(7)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
for( _it1991656106 in (file.tags) ) {
changeItVariable(_it1991656106)
printHtmlPart(10)
invokeTag('field','g',23,['type':("hidden"),'name':("tags"),'value':(it)],-1)
printHtmlPart(11)
expressionOut.print(it)
printHtmlPart(12)
}
printHtmlPart(13)
})
invokeTag('form','g',29,['url':([resource:file, action:'update']),'method':("PUT"),'id':("updateForm")],2)
printHtmlPart(14)
invokeTag('render','g',30,['template':("/tagging/tagAdder"),'model':([itemId: file.id])],-1)
printHtmlPart(15)
})
invokeTag('captureBody','sitemesh',33,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1518712872225L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
