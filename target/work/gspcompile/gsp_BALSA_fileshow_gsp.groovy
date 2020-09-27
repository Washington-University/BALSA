import balsa.file.FileMetadata
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_fileshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/file/show.gsp" }
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
invokeTag('render','g',15,['template':("/templates/buttons"),'bean':(file),'var':("item")],-1)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',16,['class':("btn btn-default"),'action':("allScenes"),'resource':(file)],2)
printHtmlPart(7)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',17,['class':("btn btn-default"),'controller':("download"),'action':("downloadFile"),'id':(file.id)],2)
printHtmlPart(10)
if(true && (file?.canEdit())) {
printHtmlPart(11)
if(true && (file?.isPublic())) {
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('createLink','g',21,['action':("remove"),'id':(file.id)],-1)
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('datasetTerm','g',30,['item':(file)],-1)
printHtmlPart(16)
expressionOut.print(datasetTerm('item':file))
printHtmlPart(17)
if(true && (versionId)) {
printHtmlPart(18)
createTagBody(3, {->
expressionOut.print(file.dataset.title)
})
invokeTag('link','g',34,['action':("show"),'controller':(datasetTerm('item':file)),'id':(file.dataset.id),'params':([version: versionId])],3)
printHtmlPart(18)
}
else {
printHtmlPart(18)
createTagBody(3, {->
expressionOut.print(file.dataset.title)
})
invokeTag('link','g',37,['action':("show"),'controller':(datasetTerm('item':file)),'id':(file.dataset.id)],3)
printHtmlPart(18)
}
printHtmlPart(19)
if(true && (file.canEdit())) {
printHtmlPart(20)
expressionOut.print(file.added.format('yyyy-MM-dd h:mm a z'))
printHtmlPart(21)
}
printHtmlPart(22)
createTagBody(2, {->
expressionOut.print(fileCategory('filename':file.filename))
printHtmlPart(23)
expressionOut.print(fullFileTerm('filename':file.filename))
})
invokeTag('link','g',50,['action':("fileTypes"),'controller':("about"),'fragment':(fileCategory('filename':file.filename))],2)
printHtmlPart(24)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(25)
expressionOut.print(displaySize('bytes':file.filesize))
printHtmlPart(26)
invokeTag('join','g',65,['in':(file.tags),'delimiter':(", ")],-1)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',70,[:],1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1587759711216L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
