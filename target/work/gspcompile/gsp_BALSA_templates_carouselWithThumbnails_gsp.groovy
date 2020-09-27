import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_carouselWithThumbnails_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_carouselWithThumbnails.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("focusSceneIndex"),'value':(datasetVersion.scenes().findIndexOf { it.sceneLine == datasetVersion.focusScene })],-1)
printHtmlPart(1)
invokeTag('set','g',3,['var':("focusSceneIndex"),'value':(focusSceneIndex >= 0 ? focusSceneIndex : 0)],-1)
printHtmlPart(2)
if(true && (datasetVersion.scenes().isEmpty() && datasetVersion.linkedScenes().isEmpty())) {
printHtmlPart(3)
}
else {
printHtmlPart(4)
loop:{
int index = 0
for( scene in (datasetVersion.scenes() + datasetVersion.linkedScenes()) ) {
printHtmlPart(5)
if(true && (index == focusSceneIndex)) {
printHtmlPart(6)
}
else {
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('createLink','g',18,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(10)
expressionOut.print(scene.name)
printHtmlPart(11)
})
invokeTag('link','g',22,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(12)
index++
}
}
printHtmlPart(13)
invokeTag('set','g',35,['var':("carouselIndex"),'value':(0)],-1)
printHtmlPart(14)
for( sceneFile in (datasetVersion.sceneFiles()) ) {
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
expressionOut.print(sceneFile.label ?: sceneFile.filename)
printHtmlPart(17)
})
invokeTag('link','g',40,['action':("show"),'controller':("sceneFile"),'id':(sceneFile.id)],3)
printHtmlPart(18)
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(19)
if(true && (carouselIndex == focusSceneIndex)) {
printHtmlPart(20)
}
else {
printHtmlPart(21)
}
printHtmlPart(22)
expressionOut.print(carouselIndex)
printHtmlPart(23)
expressionOut.print(carouselIndex)
printHtmlPart(24)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(25)
invokeTag('createLink','g',47,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(26)
invokeTag('set','g',51,['var':("carouselIndex"),'value':(carouselIndex+1)],-1)
printHtmlPart(27)
}
printHtmlPart(12)
}
printHtmlPart(28)
if(true && (datasetVersion.linkedScenes().size() > 0)) {
printHtmlPart(29)
for( scene in (datasetVersion.linkedScenes()) ) {
printHtmlPart(19)
if(true && (carouselIndex == focusSceneIndex)) {
printHtmlPart(20)
}
else {
printHtmlPart(21)
}
printHtmlPart(22)
expressionOut.print(carouselIndex)
printHtmlPart(23)
expressionOut.print(carouselIndex)
printHtmlPart(24)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(25)
invokeTag('createLink','g',66,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(26)
invokeTag('set','g',70,['var':("carouselIndex"),'value':(carouselIndex+1)],-1)
printHtmlPart(27)
}
printHtmlPart(12)
}
printHtmlPart(30)
}
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1593399775733L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
