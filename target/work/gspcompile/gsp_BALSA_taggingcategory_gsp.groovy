import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_taggingcategory_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/tagging/category.gsp" }
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
expressionOut.print(categoryInstance.name)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('link','g',9,['class':("btn btn-default"),'action':("categories")],2)
printHtmlPart(5)
expressionOut.print(categoryInstance.name)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('textArea','g',14,['name':("description"),'rows':("5"),'cols':("50"),'value':(categoryInstance.description)],-1)
printHtmlPart(8)
invokeTag('submitButton','g',16,['name':("updateDescription"),'value':("Update")],-1)
printHtmlPart(9)
})
invokeTag('form','g',18,['action':("updateDescription"),'id':(categoryInstance.id)],2)
printHtmlPart(10)
expressionOut.print(categoryInstance.id)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
expressionOut.print(it.radio)
printHtmlPart(13)
expressionOut.print(it.label)
printHtmlPart(14)
})
invokeTag('radioGroup','g',29,['name':("searchType"),'labels':(['Dropdown menu','Text field','Radio buttons', 'Checkboxes']),'values':(['DROPDOWN','FIELD','RADIO','CHECKBOX']),'value':(categoryInstance.searchType),'onchange':("jQuery.ajax({type:'POST',data:jQuery(this.form).serialize(), url:'/tagging/changeType',success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){}});return false")],2)
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
invokeTag('join','g',35,['in':(categoryInstance.options),'delimiter':("\r\n")],-1)
printHtmlPart(17)
invokeTag('submitButton','g',37,['name':("updateOptions"),'value':("Update")],-1)
printHtmlPart(9)
})
invokeTag('form','g',39,['action':("updateOptions"),'id':(categoryInstance.id)],2)
printHtmlPart(18)
for( _it961648587 in (categoryInstance.handles) ) {
changeItVariable(_it961648587)
printHtmlPart(19)
expressionOut.print(it.value)
printHtmlPart(20)
expressionOut.print(it.handle)
printHtmlPart(21)
createClosureForHtmlPart(22, 3)
invokeTag('link','g',43,['action':("deleteHandle"),'id':(it.id)],3)
printHtmlPart(23)
}
printHtmlPart(24)
for( option in (categoryInstance.options) ) {
printHtmlPart(25)
if(true && (!categoryInstance.handles.find({it.value == option}))) {
printHtmlPart(26)
expressionOut.print(option)
printHtmlPart(27)
}
printHtmlPart(25)
}
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(29)
expressionOut.print(categoryInstance.id)
printHtmlPart(30)
})
invokeTag('form','g',65,['class':("form-inline"),'action':("addHandle")],2)
printHtmlPart(31)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',68,['class':("btn btn-default"),'action':("deleteCategory"),'id':(categoryInstance.id),'onclick':("return confirm('Delete category?')")],2)
printHtmlPart(33)
})
invokeTag('captureBody','sitemesh',70,[:],1)
printHtmlPart(34)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457989047416L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
