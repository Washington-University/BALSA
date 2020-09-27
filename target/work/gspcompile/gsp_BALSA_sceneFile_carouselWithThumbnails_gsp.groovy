import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_sceneFile_carouselWithThumbnails_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sceneFile/_carouselWithThumbnails.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("focusSceneIndex"),'value':(sceneFile.scenesSorted().findIndexOf { it == focusScene })],-1)
printHtmlPart(1)
invokeTag('set','g',3,['var':("focusSceneIndex"),'value':(focusSceneIndex >= 0 ? focusSceneIndex : 0)],-1)
printHtmlPart(2)
loop:{
int index = 0
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(3)
if(true && (index == focusSceneIndex)) {
printHtmlPart(4)
}
else {
printHtmlPart(5)
}
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('createLink','g',11,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(8)
expressionOut.print(scene.name)
printHtmlPart(9)
})
invokeTag('link','g',15,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],2)
printHtmlPart(10)
index++
}
}
printHtmlPart(11)
loop:{
int index = 0
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(12)
if(true && (index == focusSceneIndex)) {
printHtmlPart(13)
}
else {
printHtmlPart(14)
}
printHtmlPart(15)
expressionOut.print(index)
printHtmlPart(16)
expressionOut.print(index)
printHtmlPart(17)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(18)
invokeTag('createLink','g',32,['controller':("scene"),'action':("image"),'id':(scene?.id)],-1)
printHtmlPart(19)
index++
}
}
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1586378356916L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
