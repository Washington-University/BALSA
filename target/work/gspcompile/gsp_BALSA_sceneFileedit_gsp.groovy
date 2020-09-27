import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_sceneFileedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sceneFile/edit.gsp" }
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
createTagBody(3, {->
printHtmlPart(2)
invokeTag('fieldValue','g',5,['bean':(file),'field':("filename")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('fieldValue','g',9,['bean':(file),'field':("filename")],-1)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',12,['class':("btn btn-default"),'action':("show"),'resource':(file)],2)
printHtmlPart(7)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('field','g',21,['class':("form-control"),'type':("text"),'name':("label"),'value':(file?.label)],-1)
printHtmlPart(10)
})
invokeTag('form','g',23,['url':([resource:file, action:'update']),'method':("PUT"),'id':("updateForm")],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',26,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1518718071825L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
