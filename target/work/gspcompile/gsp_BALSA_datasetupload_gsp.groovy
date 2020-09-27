import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_datasetupload_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/upload.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(datasetInstance.title)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',12,['class':("btn btn-default"),'action':("show"),'resource':(datasetInstance)],2)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',13,['class':("btn btn-primary"),'action':("processUpload"),'resource':(datasetInstance)],2)
printHtmlPart(9)
if(true && (datasetInstance.publicVersion() || datasetInstance.preprintVersion())) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('datasetTerm','g',23,['item':(file)],-1)
printHtmlPart(12)
invokeTag('add','uploadr',25,['name':("uploader"),'path':(datasetInstance.getId()),'allowedExtensions':("zip,txt,rtf,pdf,odt,odp,wpd,doc,docx,ppt,pptx,jpg,png,fig,m,gif,csv"),'controller':(datasetTerm('item':datasetInstance)),'action':("handleUpload"),'noSound':("true"),'viewable':("false"),'downloadable':("false"),'deletable':("false"),'params':([id:datasetInstance.id])],-1)
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',28,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1586803570648L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
