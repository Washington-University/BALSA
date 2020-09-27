import balsa.file.FileMetadata
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_dataseteditScenes_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/editScenes.gsp" }
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
createClosureForHtmlPart(5, 2)
invokeTag('link','g',13,['class':("btn btn-default"),'action':("show"),'resource':(datasetInstance)],2)
printHtmlPart(6)
invokeTag('datasetTerm','g',19,['item':(datasetInstance)],-1)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
printHtmlPart(9)
expressionOut.print(!startId ? 'in' : '')
printHtmlPart(10)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(11)
for( sceneFile in (versionInstance.sceneFiles()) ) {
printHtmlPart(12)
expressionOut.print(sceneFile.id)
printHtmlPart(13)
expressionOut.print(sceneFile.id)
printHtmlPart(14)
expressionOut.print(sceneFile.id)
printHtmlPart(15)
expressionOut.print(sceneFile.filename)
printHtmlPart(16)
expressionOut.print(sceneFile.id)
printHtmlPart(17)
expressionOut.print((sceneFile.id == startId || (sceneFile.scenes*.id).contains(startId)) ? 'in': '')
printHtmlPart(18)
expressionOut.print(sceneFile.id)
printHtmlPart(19)
expressionOut.print(sceneFile.id)
printHtmlPart(20)
expressionOut.print(sceneFile.id)
printHtmlPart(21)
expressionOut.print(sceneFile.id)
printHtmlPart(22)
expressionOut.print(sceneFile.id)
printHtmlPart(23)
expressionOut.print(sceneFile.id)
printHtmlPart(24)
expressionOut.print(sceneFile.id)
printHtmlPart(25)
expressionOut.print(sceneFile.id == startId ? 'in': '')
printHtmlPart(26)
expressionOut.print(sceneFile.id)
printHtmlPart(27)
expressionOut.print(sceneFile.id)
printHtmlPart(28)
invokeTag('field','g',60,['class':("form-control"),'type':("text"),'name':("${sceneFile.id}label"),'value':(sceneFile.label)],-1)
printHtmlPart(29)
expressionOut.print(sceneFile.id)
printHtmlPart(30)
expressionOut.print(sceneFile.id)
printHtmlPart(31)
for( scene in (sceneFile.scenesSorted()) ) {
printHtmlPart(32)
expressionOut.print(scene.id)
printHtmlPart(33)
expressionOut.print(sceneFile.id)
printHtmlPart(22)
expressionOut.print(scene.id)
printHtmlPart(14)
expressionOut.print(scene.id)
printHtmlPart(34)
expressionOut.print(scene.name)
printHtmlPart(35)
expressionOut.print(scene.id)
printHtmlPart(36)
expressionOut.print(scene.id)
printHtmlPart(37)
expressionOut.print(scene.id == startId ? 'in': '')
printHtmlPart(38)
expressionOut.print(scene.id)
printHtmlPart(39)
expressionOut.print(scene.id)
printHtmlPart(40)
invokeTag('field','g',83,['class':("form-control"),'type':("text"),'name':("${scene.id}shortName"),'value':(scene.shortName)],-1)
printHtmlPart(41)
for( _it532299734 in (scene.tags) ) {
changeItVariable(_it532299734)
printHtmlPart(42)
expressionOut.print(it.substring(0, it.indexOf(':')))
printHtmlPart(43)
invokeTag('field','g',89,['type':("hidden"),'name':("${scene.id}tags"),'value':(it)],-1)
printHtmlPart(44)
expressionOut.print(it)
printHtmlPart(45)
expressionOut.print(it)
printHtmlPart(46)
}
printHtmlPart(47)
expressionOut.print('/scene/image/' + scene.id)
printHtmlPart(48)
}
printHtmlPart(49)
}
printHtmlPart(50)
})
invokeTag('form','g',112,['name':("editScenesForm"),'action':("saveSceneEdits"),'id':(datasetInstance.id)],2)
printHtmlPart(51)
invokeTag('select','g',118,['class':("form-control"),'style':("float:right;width:200px"),'id':("filterCategory"),'name':("filterCategory"),'from':(tagCategories*.name),'noSelection':(['':'Filter by category']),'onchange':("filterTags()")],-1)
printHtmlPart(52)
for( category in (tagCategories) ) {
printHtmlPart(53)
for( option in (category.options) ) {
printHtmlPart(54)
expressionOut.print(category.name)
printHtmlPart(55)
expressionOut.print(category.name + ':' + option)
printHtmlPart(56)
expressionOut.print(category.name + ':' + option)
printHtmlPart(57)
expressionOut.print(category.name + ':' + option)
printHtmlPart(58)
}
printHtmlPart(53)
}
printHtmlPart(59)
invokeTag('field','g',216,['type':("hidden"),'name':("SCENEIDVALUEtags"),'value':("TAGVALUE")],-1)
printHtmlPart(60)
})
invokeTag('captureBody','sitemesh',225,[:],1)
printHtmlPart(61)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1598462382453L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
