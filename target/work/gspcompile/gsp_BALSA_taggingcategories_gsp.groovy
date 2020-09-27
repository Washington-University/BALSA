import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_taggingcategories_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/tagging/categories.gsp" }
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
invokeTag('link','g',10,['class':("btn btn-primary"),'action':("createCategory")],2)
printHtmlPart(6)
for( _it1487381901 in (categoryInstanceList) ) {
changeItVariable(_it1487381901)
printHtmlPart(7)
expressionOut.print(it.name)
printHtmlPart(8)
createClosureForHtmlPart(9, 3)
invokeTag('link','g',14,['class':("btn btn-primary"),'action':("category"),'id':(it.id)],3)
printHtmlPart(8)
createClosureForHtmlPart(10, 3)
invokeTag('link','g',15,['class':("btn btn-default"),'action':("deleteCategory"),'id':(it.id),'onclick':("return confirm('Delete category?')")],3)
printHtmlPart(11)
}
printHtmlPart(6)
if(true && (categoryInstanceCount > 10)) {
printHtmlPart(6)
invokeTag('paginate','g',19,['omitPrev':("true"),'omitNext':("true"),'total':(categoryInstanceCount ?: 0)],-1)
printHtmlPart(6)
}
printHtmlPart(12)
})
invokeTag('captureBody','sitemesh',22,[:],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455734389701L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
