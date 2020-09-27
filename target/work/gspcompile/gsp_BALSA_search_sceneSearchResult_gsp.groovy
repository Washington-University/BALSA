import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_search_sceneSearchResult_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/search/_sceneSearchResult.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('set','g',1,['var':("thumbnailSize"),'value':(820/params.columns.toInteger())],-1)
printHtmlPart(0)
expressionOut.print(thumbnailSize)
printHtmlPart(1)
invokeTag('datasetTerm','g',3,['item':(scene)],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(datasetTermUppercase('item':scene))
printHtmlPart(4)
expressionOut.print(scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title)
printHtmlPart(5)
})
invokeTag('link','g',9,['action':("show"),'controller':(datasetTerm('item':scene)),'id':(scene.sceneFile.dataset.id)],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(scene.sceneFile.filename)
printHtmlPart(8)
})
invokeTag('link','g',17,['class':("sceneFileAbove"),'action':("show"),'controller':("sceneFile"),'id':(scene.sceneFile.id),'style':("display:none")],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
expressionOut.print(thumbnailSize-40)
printHtmlPart(11)
expressionOut.print((thumbnailSize-40)*0.65)
printHtmlPart(12)
invokeTag('createLink','g',23,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(13)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(14)
})
invokeTag('link','g',31,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1510088546600L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
