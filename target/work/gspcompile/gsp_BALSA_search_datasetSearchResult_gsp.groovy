import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_search_datasetSearchResult_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/search/_datasetSearchResult.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('set','g',1,['var':("thumbnailSize"),'value':(820/params.columns.toInteger())],-1)
printHtmlPart(0)
invokeTag('set','g',2,['var':("datasetVersion"),'value':(dataset.publicVersion() ?: dataset.preprintVersion())],-1)
printHtmlPart(1)
expressionOut.print(thumbnailSize)
printHtmlPart(2)
invokeTag('datasetTerm','g',4,['item':(datasetVersion)],-1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(datasetTermUppercase('item':datasetVersion))
printHtmlPart(5)
expressionOut.print(dataset.shortTitle ?: dataset.title)
printHtmlPart(6)
})
invokeTag('link','g',9,['action':("show"),'controller':(datasetTerm('item':datasetVersion)),'id':(dataset.id),'params':(datasetVersion.preprint ? "[version: 'preprint']" : "[]")],1)
printHtmlPart(7)
expressionOut.print(datasetVersion.id)
printHtmlPart(8)
invokeTag('set','g',15,['var':("focusSceneIndex"),'value':(datasetVersion.scenes().findIndexOf { it.sceneLine == datasetVersion.focusScene })],-1)
printHtmlPart(9)
invokeTag('set','g',16,['var':("focusSceneIndex"),'value':(focusSceneIndex >= 0 ? focusSceneIndex : 0)],-1)
printHtmlPart(10)
expressionOut.print(datasetVersion.id)
printHtmlPart(11)
expressionOut.print(focusSceneIndex)
printHtmlPart(12)
expressionOut.print(datasetVersion.id)
printHtmlPart(11)
expressionOut.print(datasetVersion.scenes().size() + datasetVersion.linkedScenes().size() - 1)
printHtmlPart(13)
expressionOut.print(datasetVersion.id)
printHtmlPart(14)
expressionOut.print(datasetVersion.id)
printHtmlPart(15)
expressionOut.print(datasetVersion.id)
printHtmlPart(16)
expressionOut.print(datasetVersion.id)
printHtmlPart(17)
expressionOut.print(datasetVersion.id)
printHtmlPart(18)
expressionOut.print(datasetVersion.id)
printHtmlPart(19)
expressionOut.print(datasetVersion.id)
printHtmlPart(20)
expressionOut.print(datasetVersion.id)
printHtmlPart(15)
expressionOut.print(datasetVersion.id)
printHtmlPart(21)
expressionOut.print(datasetVersion.id)
printHtmlPart(22)
expressionOut.print(datasetVersion.id)
printHtmlPart(23)
expressionOut.print(datasetVersion.id)
printHtmlPart(24)
expressionOut.print(datasetVersion.id)
printHtmlPart(25)
expressionOut.print(datasetVersion.id)
printHtmlPart(26)
expressionOut.print(datasetVersion.id)
printHtmlPart(27)
expressionOut.print(datasetVersion.id)
printHtmlPart(28)
loop:{
int index = 0
for( scene in (datasetVersion.scenes() + datasetVersion.linkedScenes()) ) {
printHtmlPart(29)
expressionOut.print(datasetVersion.id)
printHtmlPart(30)
expressionOut.print(index)
printHtmlPart(31)
if(true && (index == focusSceneIndex)) {
printHtmlPart(32)
}
else {
printHtmlPart(33)
}
printHtmlPart(34)
if(true && (params.sceneFileVisibility=='Hide')) {
printHtmlPart(35)
}
printHtmlPart(36)
createTagBody(2, {->
printHtmlPart(37)
expressionOut.print(scene.sceneFile.label ?: scene.sceneFile.filename)
printHtmlPart(38)
})
invokeTag('link','g',47,['class':("sceneFileAbove"),'action':("show"),'controller':("sceneFile"),'id':(scene.sceneFile.id)],2)
printHtmlPart(39)
createTagBody(2, {->
printHtmlPart(40)
expressionOut.print(scene?.id)
printHtmlPart(41)
expressionOut.print(thumbnailSize-40)
printHtmlPart(42)
expressionOut.print((thumbnailSize-40)*0.65)
printHtmlPart(43)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(44)
})
invokeTag('link','g',60,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id),'style':("display:inline-block;")],2)
printHtmlPart(45)
index++
}
}
printHtmlPart(46)
expressionOut.print(datasetVersion.id)
printHtmlPart(47)
expressionOut.print(datasetVersion.id)
printHtmlPart(48)
expressionOut.print((thumbnailSize-40)*0.325)
printHtmlPart(49)
expressionOut.print(datasetVersion.id)
printHtmlPart(50)
expressionOut.print(datasetVersion.id)
printHtmlPart(51)
expressionOut.print((thumbnailSize-40)*0.325)
printHtmlPart(52)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1589073131759L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
