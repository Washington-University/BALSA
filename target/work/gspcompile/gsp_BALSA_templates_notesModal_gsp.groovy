import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_notesModal_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_notesModal.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textArea','g',13,['class':("form-control"),'style':("resize: none;"),'name':("notes"),'value':(datasetInstance.notes),'rows':("20")],-1)
printHtmlPart(2)
})
invokeTag('formRemote','g',16,['url':([action: 'updateNotes', id: datasetInstance.id]),'name':("notesForm"),'class':("form-horizontal"),'action':("updateNotes"),'id':("notesForm"),'onSuccess':("jQuery('#notesModal').modal('hide');jQuery('#notesModalButton').text('Notes (saved)');updateRecentActivity();")],1)
printHtmlPart(3)
expressionOut.print(createLink(action:'readNotes',id:datasetInstance.id))
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1555971838019L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
