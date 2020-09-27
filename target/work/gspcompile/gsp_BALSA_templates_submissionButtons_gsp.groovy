import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_submissionButtons_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_submissionButtons.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (!(versionInstance.isSubmitted() || versionInstance.isApproved() || versionInstance.isPublic()))) {
printHtmlPart(0)
if(true && (versionInstance.isReadyForSubmission())) {
printHtmlPart(0)
if(true && (versionInstance.dataset instanceof balsa.Study && !versionInstance.submitter)) {
printHtmlPart(1)
invokeTag('createLink','g',11,['action':("submitForCuration"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(2)
}
else {
printHtmlPart(3)
invokeTag('createLink','g',24,['action':("submitForCuration"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(4)
}
printHtmlPart(0)
}
else {
printHtmlPart(5)
}
printHtmlPart(0)
}
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(0)
if(true && (versionInstance.isSubmitted())) {
printHtmlPart(7)
invokeTag('createLink','g',39,['action':("withdrawFromCuration"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(8)
}
printHtmlPart(0)
})
invokeTag('ifNotGranted','sec',43,['roles':("ROLE_CURATOR")],1)
printHtmlPart(6)
if(true && (versionInstance.isApproved())) {
printHtmlPart(9)
invokeTag('createLink','g',47,['action':("removeFromApproved"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(10)
}
printHtmlPart(6)
if(true && (versionInstance.isPublic())) {
printHtmlPart(11)
invokeTag('createLink','g',54,['action':("removeFromPublic"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(10)
}
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(0)
if(true && (versionInstance.isSubmitted())) {
printHtmlPart(0)
if(true && (versionInstance.isReadyForApproval())) {
printHtmlPart(12)
invokeTag('createLink','g',63,['action':("approve"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(13)
}
else {
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('createLink','g',71,['action':("revise"),'id':(versionInstance.dataset.id),'params':([version: versionInstance.id])],-1)
printHtmlPart(16)
}
printHtmlPart(0)
})
invokeTag('ifAnyGranted','sec',1,['roles':("ROLE_CURATOR,ROLE_ADMIN")],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1589310934275L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
