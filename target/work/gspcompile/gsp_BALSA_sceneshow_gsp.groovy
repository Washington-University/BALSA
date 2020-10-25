import balsa.scene.Scene
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_sceneshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/scene/show.gsp" }
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
expressionOut.print(sceneInstance.name)
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
invokeTag('fieldValue','g',10,['bean':(sceneInstance),'field':("name")],-1)
printHtmlPart(4)
if(true && (sceneInstance.shortName)) {
printHtmlPart(5)
invokeTag('fieldValue','g',10,['bean':(sceneInstance),'field':("shortName")],-1)
}
printHtmlPart(6)
invokeTag('createLink','g',21,['controller':("scene"),'action':("image"),'id':(sceneInstance?.id)],-1)
printHtmlPart(7)
invokeTag('render','g',27,['template':("/templates/terms"),'bean':(sceneInstance.terms()),'var':("terms")],-1)
printHtmlPart(8)
invokeTag('render','g',28,['template':("/download/downloadModal"),'bean':(sceneInstance),'var':("item")],-1)
printHtmlPart(9)
if(true && (item?.canEdit())) {
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('link','g',31,['class':("btn btn-default"),'action':("edit"),'id':(item.id)],3)
printHtmlPart(12)
}
printHtmlPart(12)
invokeTag('render','g',33,['template':("/templates/buttons"),'bean':(sceneInstance),'var':("item")],-1)
printHtmlPart(13)
invokeTag('render','g',36,['template':("/templates/fileModal"),'model':(['files':sceneInstance.dependencies(),'datasetInstance':sceneInstance.sceneFile.dataset])],-1)
printHtmlPart(14)
invokeTag('datasetTerm','g',38,['item':(sceneInstance)],-1)
printHtmlPart(15)
invokeTag('createLink','g',41,['controller':("scene"),'action':("image"),'id':(sceneInstance?.id)],-1)
printHtmlPart(16)
expressionOut.print(datasetTerm('item':sceneInstance))
printHtmlPart(17)
createTagBody(2, {->
expressionOut.print(sceneInstance.sceneFile.dataset.shortTitle ?: sceneInstance.sceneFile.dataset.title)
})
invokeTag('link','g',47,['action':("show"),'controller':(datasetTerm('item':sceneInstance)),'id':(sceneInstance.sceneFile.dataset.id)],2)
printHtmlPart(18)
createTagBody(2, {->
expressionOut.print(sceneInstance.sceneFile.filename.replace('.scene',''))
})
invokeTag('link','g',52,['action':("show"),'controller':("sceneFile"),'id':(sceneInstance.sceneFile.id)],2)
printHtmlPart(19)
invokeTag('fieldValue','g',57,['bean':(sceneInstance),'field':("name")],-1)
printHtmlPart(20)
createTagBody(2, {->
invokeTag('fieldValue','g',62,['bean':(sceneInstance),'field':("description")],-1)
})
invokeTag('encodeAs','g',62,['codec':("PreserveWhitespace")],2)
printHtmlPart(21)
invokeTag('join','g',67,['in':(sceneInstance.tags),'delimiter':(", ")],-1)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',71,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1603667287180L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
