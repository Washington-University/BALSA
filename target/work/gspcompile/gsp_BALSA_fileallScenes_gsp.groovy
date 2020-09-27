import balsa.scene.Scene
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_fileallScenes_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/file/allScenes.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('fieldValue','g',10,['bean':(file),'field':("filename")],-1)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',12,['class':("btn btn-default"),'action':("show"),'resource':(file)],2)
printHtmlPart(7)
for( scene in (scenes) ) {
printHtmlPart(8)
invokeTag('set','g',20,['var':("thumbnailSize"),'value':(1140/4)],-1)
printHtmlPart(9)
expressionOut.print(thumbnailSize)
printHtmlPart(10)
invokeTag('datasetTerm','g',22,['item':(scene)],-1)
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(12)
expressionOut.print(datasetTermUppercase('item':scene))
printHtmlPart(13)
expressionOut.print(scene.sceneFile.dataset.shortTitle ?: scene.sceneFile.dataset.title)
printHtmlPart(14)
})
invokeTag('link','g',28,['action':("show"),'controller':(datasetTerm('item':scene)),'id':(scene.sceneFile.dataset.id)],3)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
expressionOut.print(thumbnailSize-40)
printHtmlPart(17)
expressionOut.print((thumbnailSize-40)*0.65)
printHtmlPart(18)
invokeTag('createLink','g',34,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(19)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(20)
})
invokeTag('link','g',42,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(21)
}
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',50,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1510088498363L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
