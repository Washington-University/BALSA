import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_sceneList_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_sceneList.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( sceneFile in (sceneFiles) ) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(sceneFile.filename)
printHtmlPart(3)
if(true && (sceneFile.canView())) {
printHtmlPart(4)
expressionOut.print(sceneFile.id)
printHtmlPart(5)
}
printHtmlPart(6)
})
invokeTag('link','g',9,['action':("show"),'controller':("sceneFile"),'id':(sceneFile.id)],2)
printHtmlPart(7)
if(true && (sceneFile.label)) {
printHtmlPart(8)
expressionOut.print(sceneFile.label)
printHtmlPart(9)
}
printHtmlPart(10)
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(scene.name + (scene.shortName ? ' - ' + scene.shortName : ''))
})
invokeTag('link','g',19,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(12)
if(true && (scene.canView())) {
printHtmlPart(13)
expressionOut.print(scene.index)
printHtmlPart(14)
expressionOut.print(scene.sceneLine.id)
printHtmlPart(15)
}
printHtmlPart(16)
}
printHtmlPart(17)
}
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1598418538970L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
