import balsa.file.SceneFile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_sceneFileshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sceneFile/show.gsp" }
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
createTagBody(3, {->
invokeTag('fieldValue','g',6,['bean':(file),'field':("filename")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('fieldValue','g',10,['bean':(file),'field':("filename")],-1)
printHtmlPart(4)
invokeTag('render','g',12,['template':("/templates/terms"),'bean':(file.terms()),'var':("terms")],-1)
printHtmlPart(5)
invokeTag('render','g',13,['template':("/download/downloadModal"),'bean':(file),'var':("item")],-1)
printHtmlPart(6)
if(true && (item?.canEdit())) {
printHtmlPart(7)
createClosureForHtmlPart(8, 3)
invokeTag('link','g',16,['class':("btn btn-default"),'action':("edit"),'id':(item.id)],3)
printHtmlPart(9)
}
printHtmlPart(9)
invokeTag('render','g',18,['template':("/templates/buttons"),'bean':(file),'var':("item")],-1)
printHtmlPart(9)
if(true && (file?.canEdit())) {
printHtmlPart(9)
createClosureForHtmlPart(10, 3)
invokeTag('link','g',20,['class':("btn btn-default"),'controller':(datasetTerm('item':file)),'action':("editScenes"),'id':(file.dataset.id),'params':([startId: file.id])],3)
printHtmlPart(9)
}
printHtmlPart(11)
if(true && (file?.canEdit())) {
printHtmlPart(12)
if(true && (file?.isPublic())) {
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('createLink','g',25,['action':("remove"),'id':(file.id)],-1)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('render','g',30,['template':("/templates/fileModal"),'model':(['files':dependencies,'datasetInstance':file.dataset])],-1)
printHtmlPart(17)
invokeTag('datasetTerm','g',33,['item':(file)],-1)
printHtmlPart(18)
invokeTag('render','g',34,['template':("carouselWithThumbnails"),'model':(['sceneFile': file, 'focusScene': file.focusScene(versionId)])],-1)
printHtmlPart(19)
expressionOut.print(datasetTerm('item':file))
printHtmlPart(20)
if(true && (versionId)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(file.dataset.title)
})
invokeTag('link','g',39,['action':("show"),'controller':(datasetTerm('item':file)),'id':(file.dataset.id),'params':([version: versionId])],3)
printHtmlPart(7)
}
else {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(file.dataset.title)
})
invokeTag('link','g',42,['action':("show"),'controller':(datasetTerm('item':file)),'id':(file.dataset.id)],3)
printHtmlPart(7)
}
printHtmlPart(21)
if(true && (file.canEdit())) {
printHtmlPart(22)
expressionOut.print(file.added.format('yyyy-MM-dd h:mm a z'))
printHtmlPart(23)
}
printHtmlPart(24)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(21)
if(true && (file.label)) {
printHtmlPart(25)
expressionOut.print(file.label)
printHtmlPart(23)
}
printHtmlPart(26)
for( scene in (file.scenesSorted()) ) {
printHtmlPart(27)
createTagBody(3, {->
expressionOut.print(scene.name?.encodeAsHTML())
})
invokeTag('link','g',70,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(28)
if(true && (scene.canView())) {
printHtmlPart(29)
expressionOut.print(scene.index)
printHtmlPart(30)
expressionOut.print(scene.sceneLine.id)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(33)
})
invokeTag('captureBody','sitemesh',80,[:],1)
printHtmlPart(34)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1603667302009L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
