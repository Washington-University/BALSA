import balsa.scene.Scene
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_downloadstats_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/download/stats.gsp" }
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
expressionOut.print(downloadStats.totalDownloads)
printHtmlPart(5)
expressionOut.print(downloadStats.uniqueUsers[0])
printHtmlPart(6)
expressionOut.print(downloadStats.uniqueIPs[0])
printHtmlPart(7)
invokeTag('formatDate','g',27,['date':(downloadStats.mostRecentDownload),'type':("datetime"),'style':("LONG")],-1)
printHtmlPart(8)
invokeTag('displaySize','g',31,['bytes':(downloadStats.totalDownloadSize)],-1)
printHtmlPart(9)
for( dataset in (downloadStats.popularDatasets) ) {
printHtmlPart(10)
expressionOut.print(dataset.downloads.size())
printHtmlPart(11)
expressionOut.print(datasetTermUppercase('item':dataset))
printHtmlPart(12)
expressionOut.print(dataset.id)
printHtmlPart(13)
createTagBody(3, {->
expressionOut.print(dataset.shortTitle ?: dataset.title)
})
invokeTag('link','g',56,['action':("show"),'controller':(datasetTerm('item':dataset)),'id':(dataset.id)],3)
printHtmlPart(14)
}
printHtmlPart(15)
for( scene in (downloadStats.popularScenes) ) {
printHtmlPart(10)
expressionOut.print(scene.downloads.size())
printHtmlPart(16)
expressionOut.print(scene.id)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':scene))
printHtmlPart(18)
expressionOut.print(scene.sceneLine.dataset.shortTitle ?: scene.sceneLine.dataset.title)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':scene))
printHtmlPart(19)
expressionOut.print(scene.sceneLine.dataset.id)
printHtmlPart(20)
expressionOut.print(scene.sceneFile.filename)
printHtmlPart(21)
expressionOut.print(scene?.id)
printHtmlPart(22)
createTagBody(3, {->
expressionOut.print(scene.shortName ?: scene.name)
})
invokeTag('link','g',84,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(14)
}
printHtmlPart(23)
for( file in (downloadStats.popularSceneFiles) ) {
printHtmlPart(10)
expressionOut.print(file.downloads.size())
printHtmlPart(24)
expressionOut.print(file.id)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':file))
printHtmlPart(18)
expressionOut.print(file.dataset.shortTitle ?: file.dataset.title)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':file))
printHtmlPart(19)
expressionOut.print(file.dataset.id)
printHtmlPart(25)
expressionOut.print(file.filepath)
printHtmlPart(26)
createTagBody(3, {->
expressionOut.print(file.filename)
})
invokeTag('link','g',111,['controller':("sceneFile"),'action':("show"),'id':(file.id)],3)
printHtmlPart(14)
}
printHtmlPart(27)
for( file in (downloadStats.popularFiles) ) {
printHtmlPart(10)
expressionOut.print(file.downloads.size())
printHtmlPart(11)
expressionOut.print(shortFileTerm('filename':file.filename))
printHtmlPart(24)
expressionOut.print(file.id)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':file))
printHtmlPart(18)
expressionOut.print(file.dataset.shortTitle ?: file.dataset.title)
printHtmlPart(17)
expressionOut.print(datasetTermUppercase('item':file))
printHtmlPart(19)
expressionOut.print(file.dataset.id)
printHtmlPart(25)
expressionOut.print(file.filepath)
printHtmlPart(28)
createTagBody(3, {->
expressionOut.print(file.filename)
})
invokeTag('link','g',144,['controller':("file"),'action':("show"),'id':(file.id)],3)
printHtmlPart(14)
}
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',155,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1551197115155L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
