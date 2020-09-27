import balsa.scene.Scene
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_sceneedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/scene/edit.gsp" }
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
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(7)
expressionOut.print(error.field)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',14,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',15,['bean':(sceneInstance),'var':("error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',17,['bean':(sceneInstance)],2)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
invokeTag('field','g',21,['class':("form-control"),'type':("text"),'name':("name"),'value':(sceneInstance?.name)],-1)
printHtmlPart(14)
invokeTag('field','g',25,['class':("form-control"),'type':("text"),'name':("shortName"),'value':(sceneInstance?.shortName)],-1)
printHtmlPart(15)
invokeTag('textArea','g',29,['class':("form-control"),'name':("description"),'value':(sceneInstance?.description),'rows':("30")],-1)
printHtmlPart(16)
for( _it1651372403 in (sceneInstance.tags) ) {
changeItVariable(_it1651372403)
printHtmlPart(17)
invokeTag('field','g',34,['type':("hidden"),'name':("tags"),'value':(it)],-1)
printHtmlPart(18)
expressionOut.print(it)
printHtmlPart(19)
}
printHtmlPart(20)
})
invokeTag('form','g',40,['url':([resource:sceneInstance, action:'update']),'method':("PUT"),'id':("updateForm")],2)
printHtmlPart(21)
invokeTag('render','g',42,['template':("/tagging/tagAdder"),'model':([itemId: sceneInstance.id])],-1)
printHtmlPart(21)
invokeTag('submitButton','g',44,['name':("submitUpdate"),'class':("btn btn-primary"),'action':("update"),'value':("Update"),'form':("updateForm")],-1)
printHtmlPart(12)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',45,['class':("btn btn-default"),'controller':("scene"),'action':("show"),'id':(sceneInstance.sceneLine.id)],2)
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468860627328L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
