import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_terms_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_terms.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (terms?.numberOfTerms > 0)) {
printHtmlPart(0)
if(true && (terms.notAgreed)) {
printHtmlPart(1)
}
printHtmlPart(2)
for( _it725270251 in (terms.notAgreed) ) {
changeItVariable(_it725270251)
printHtmlPart(3)
expressionOut.print(it.id)
printHtmlPart(4)
expressionOut.print(it.id)
printHtmlPart(5)
expressionOut.print(it.id)
printHtmlPart(6)
expressionOut.print(it.title)
printHtmlPart(7)
expressionOut.print(it.id)
printHtmlPart(8)
expressionOut.print(it.id)
printHtmlPart(9)
expressionOut.print(600 - terms?.numberOfTerms*42)
printHtmlPart(10)
createTagBody(3, {->
invokeTag('fieldValue','g',23,['bean':(it),'field':("contents")],-1)
})
invokeTag('encodeAs','g',23,['codec':("PreserveWhitespace")],3)
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
expressionOut.print(it.id)
printHtmlPart(14)
})
invokeTag('remoteLink','g',28,['controller':("terms"),'action':("agree"),'id':(it.id),'onSuccess':("agree(data, '${it.id}');agreementChanged=true;updateRecentActivity();"),'onFailure':("displayError();updateRecentActivity();")],4)
printHtmlPart(12)
})
invokeTag('ifLoggedIn','sec',29,[:],3)
printHtmlPart(12)
createClosureForHtmlPart(15, 3)
invokeTag('ifNotLoggedIn','sec',32,[:],3)
printHtmlPart(16)
}
printHtmlPart(17)
for( _it61780583 in (terms.notApproved) ) {
changeItVariable(_it61780583)
printHtmlPart(18)
}
printHtmlPart(17)
for( _it329895342 in (terms.agreed) ) {
changeItVariable(_it329895342)
printHtmlPart(3)
expressionOut.print(it.id)
printHtmlPart(4)
expressionOut.print(it.id)
printHtmlPart(19)
expressionOut.print(it.title)
printHtmlPart(7)
expressionOut.print(it.id)
printHtmlPart(8)
expressionOut.print(it.id)
printHtmlPart(9)
expressionOut.print(600 - terms?.numberOfTerms*42)
printHtmlPart(10)
createTagBody(3, {->
invokeTag('fieldValue','g',48,['bean':(it),'field':("contents")],-1)
})
invokeTag('encodeAs','g',48,['codec':("PreserveWhitespace")],3)
printHtmlPart(20)
}
printHtmlPart(17)
for( _it479668966 in (terms.approved) ) {
changeItVariable(_it479668966)
printHtmlPart(18)
}
printHtmlPart(18)
if(true && (terms.customTermsTitle && terms.customTermsContent)) {
printHtmlPart(21)
expressionOut.print(terms.customTermsTitle)
printHtmlPart(22)
expressionOut.print(600 - terms?.numberOfTerms*42)
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(terms.customTermsContent)
})
invokeTag('encodeAs','g',67,['codec':("PreserveWhitespace")],3)
printHtmlPart(23)
invokeTag('createLink','g',70,['action':("approveCustomTerms"),'id':(terms.datasetId)],-1)
printHtmlPart(24)
}
printHtmlPart(25)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1593110253495L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
