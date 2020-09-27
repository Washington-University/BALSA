import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_fileModal_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_fileModal.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(2)
}
printHtmlPart(3)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(4)
}
printHtmlPart(5)
for( file in (files) ) {
printHtmlPart(6)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(7)
if(true && (!(file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file)))) {
printHtmlPart(8)
}
printHtmlPart(9)
expressionOut.print(file.id)
printHtmlPart(10)
}
printHtmlPart(11)
expressionOut.print(shortFileTerm('filename':file.filename))
printHtmlPart(12)
expressionOut.print(file.filepath.replace('/',' / '))
printHtmlPart(13)
createTagBody(3, {->
expressionOut.print(file.filename)
})
invokeTag('link','g',38,['controller':("file"),'action':("show"),'id':(file.id)],3)
printHtmlPart(14)
expressionOut.print(file.filesize.toString().padLeft( 10, '0' ))
printHtmlPart(15)
invokeTag('displaySize','g',43,['bytes':(file.filesize)],-1)
printHtmlPart(16)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(17)
if(true && (file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file))) {
printHtmlPart(18)
}
else {
printHtmlPart(19)
}
printHtmlPart(16)
}
printHtmlPart(20)
}
printHtmlPart(21)
})
invokeTag('form','g',61,['name':("removeFilesForm"),'action':("removeFiles"),'id':(datasetInstance.id)],1)
printHtmlPart(22)
if(true && (datasetInstance.canEdit())) {
printHtmlPart(23)
expressionOut.print('Remove selected files from this ' + datasetTerm('item':datasetInstance) + '?' + 
								((versionInstance.isPublic() || versionInstance.isApproved()) ? ' Removing files creates a new working version of the dataset, but the files will remain in the current public version.' : ''))
printHtmlPart(24)
createClosureForHtmlPart(25, 2)
invokeTag('link','g',75,['class':("btn btn-default"),'action':("upload"),'id':(datasetInstance.id)],2)
printHtmlPart(26)
}
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1600115070649L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
