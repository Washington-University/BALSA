import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_download_downloadModalContents_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/download/_downloadModalContents.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('createLink','g',1,['action':("download"),'id':(versionInstance.id)],-1)
printHtmlPart(1)
expressionOut.print(versionInstance.dataset.id)
printHtmlPart(2)
expressionOut.print(versionInstance.dataset.id)
printHtmlPart(3)
expressionOut.print(versionInstance.files.id.join(','))
printHtmlPart(4)
expressionOut.print(versionInstance.dataset.id)
printHtmlPart(5)
expressionOut.print(versionInstance.dataset.shortTitle ?: versionInstance.dataset.title)
printHtmlPart(6)
for( sceneFile in (versionInstance.sceneFiles()) ) {
printHtmlPart(7)
expressionOut.print(sceneFile.id)
printHtmlPart(2)
expressionOut.print(sceneFile.id)
printHtmlPart(3)
expressionOut.print(sceneFile.id)
printHtmlPart(8)
expressionOut.print(sceneFile.id)
printHtmlPart(5)
expressionOut.print(sceneFile.label ?: sceneFile.filename)
printHtmlPart(9)
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(10)
expressionOut.print(scene.id)
printHtmlPart(2)
expressionOut.print(scene.id)
printHtmlPart(3)
expressionOut.print(scene.dependencies().id.join(','))
printHtmlPart(11)
expressionOut.print(sceneFile.zipsize/sceneFile.scenesSorted().size())
printHtmlPart(12)
expressionOut.print(scene.id)
printHtmlPart(13)
expressionOut.print(scene?.id)
printHtmlPart(14)
expressionOut.print(scene.shortName ?: scene.name)
printHtmlPart(15)
}
printHtmlPart(16)
}
printHtmlPart(17)
for( file in (versionInstance.files) ) {
printHtmlPart(18)
expressionOut.print(file.id)
printHtmlPart(2)
expressionOut.print(file.id)
printHtmlPart(11)
expressionOut.print(file.zipsize)
printHtmlPart(19)
expressionOut.print(shortFileTerm('filename':file.filename))
printHtmlPart(20)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(21)
expressionOut.print(file.filename)
printHtmlPart(22)
expressionOut.print(file.filesize.toString().padLeft( 10, '0' ))
printHtmlPart(23)
invokeTag('displaySize','g',70,['bytes':(file.filesize)],-1)
printHtmlPart(24)
}
printHtmlPart(25)
expressionOut.print(versionInstance.documentation().id.join(','))
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1585609919963L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
