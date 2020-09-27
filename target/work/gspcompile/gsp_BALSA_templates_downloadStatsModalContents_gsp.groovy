import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_downloadStatsModalContents_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_downloadStatsModalContents.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(downloadStats.totalDownloads)
printHtmlPart(1)
expressionOut.print(downloadStats.uniqueUsers[0])
printHtmlPart(2)
expressionOut.print(downloadStats.uniqueIPs[0])
printHtmlPart(3)
invokeTag('formatDate','g',15,['date':(downloadStats.mostRecentDownload),'type':("datetime"),'style':("LONG")],-1)
printHtmlPart(4)
invokeTag('displaySize','g',19,['bytes':(downloadStats.totalDownloadSize)],-1)
printHtmlPart(5)
for( scene in (downloadStats.popularScenes) ) {
printHtmlPart(6)
expressionOut.print(scene.downloads.size())
printHtmlPart(7)
expressionOut.print(scene.id)
printHtmlPart(8)
expressionOut.print(scene.sceneFile.filename)
printHtmlPart(9)
expressionOut.print(scene?.id)
printHtmlPart(10)
createTagBody(2, {->
expressionOut.print(scene.shortName ?: scene.name)
})
invokeTag('link','g',41,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],2)
printHtmlPart(11)
}
printHtmlPart(12)
for( file in (downloadStats.popularSceneFiles) ) {
printHtmlPart(6)
expressionOut.print(file.downloads.size())
printHtmlPart(13)
expressionOut.print(file.id)
printHtmlPart(14)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(15)
createTagBody(2, {->
expressionOut.print(file.filename)
})
invokeTag('link','g',69,['controller':("sceneFile"),'action':("show"),'id':(file.id)],2)
printHtmlPart(11)
}
printHtmlPart(16)
for( file in (downloadStats.popularFiles) ) {
printHtmlPart(6)
expressionOut.print(file.downloads.size())
printHtmlPart(17)
expressionOut.print(shortFileTerm('filename':file.filename))
printHtmlPart(13)
expressionOut.print(file.id)
printHtmlPart(14)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(15)
createTagBody(2, {->
expressionOut.print(file.filename)
})
invokeTag('link','g',101,['controller':("file"),'action':("show"),'id':(file.id)],2)
printHtmlPart(11)
}
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1551200210930L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
