import balsa.Dataset
import balsa.Study
import balsa.scene.SceneLine
import balsa.Dataset.Status
import balsa.Study.DateRedirect
import balsa.security.Terms
import balsa.security.Approval
import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.Species
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_datasetedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dataset/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',14,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(3)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
})
invokeTag('captureTitle','sitemesh',15,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',15,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',16,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
printHtmlPart(6)
expressionOut.print(datasetInstance.shortTitle ?: datasetInstance.title)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
})
invokeTag('link','g',22,['class':("btn btn-default"),'action':("show"),'resource':(datasetInstance)],2)
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',23,['class':("btn btn-default"),'controller':("about"),'action':("editing"),'target':("_blank")],2)
printHtmlPart(11)
expressionOut.print(datasetTerm('item':datasetInstance))
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
expressionOut.print(datasetTermUppercase('item':datasetInstance))
printHtmlPart(14)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(15)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'pmid', 'error'))
printHtmlPart(16)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'doi', 'error'))
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(19)
}
printHtmlPart(20)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'title', 'error'))
printHtmlPart(21)
invokeTag('field','g',42,['class':("form-control"),'type':("text"),'name':("title"),'value':(datasetInstance.title),'maxlength':("200")],-1)
printHtmlPart(22)
for( species in (Species.list()) ) {
printHtmlPart(23)
expressionOut.print(species.id)
printHtmlPart(24)
if(true && (datasetInstance.species.contains(species))) {
printHtmlPart(25)
}
printHtmlPart(26)
expressionOut.print(species.name)
printHtmlPart(27)
}
printHtmlPart(28)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'shortTitle', 'error'))
printHtmlPart(29)
invokeTag('field','g',65,['class':("form-control"),'type':("text"),'name':("shortTitle"),'value':(datasetInstance.shortTitle),'maxlength':("100")],-1)
printHtmlPart(30)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'extract', 'error'))
printHtmlPart(31)
invokeTag('field','g',71,['class':("form-control"),'type':("text"),'name':("extract"),'value':(datasetInstance.extract),'maxlength':("50")],-1)
printHtmlPart(32)
expressionOut.print(datasetInstance.id)
printHtmlPart(33)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(34)
invokeTag('textArea','g',84,['class':("form-control"),'name':("studyAbstract"),'id':("studyAbstract"),'value':(versionInstance.studyAbstract),'style':("max-width:100%;resize:none"),'rows':("9")],-1)
printHtmlPart(35)
}
printHtmlPart(36)
invokeTag('textArea','g',91,['class':("form-control"),'name':("description"),'value':(versionInstance.description),'style':("max-width:100%;resize:none"),'rows':("9")],-1)
printHtmlPart(37)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(38)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'pmid', 'error'))
printHtmlPart(39)
invokeTag('field','g',100,['class':("form-control"),'type':("text"),'name':("pmid"),'value':(versionInstance.pmid),'id':("pmidField"),'oninput':("updatePubMedButton(this)")],-1)
printHtmlPart(40)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'doi', 'error'))
printHtmlPart(41)
invokeTag('field','g',113,['class':("form-control"),'type':("text"),'name':("doi"),'value':(versionInstance.doi),'id':("doi")],-1)
printHtmlPart(42)
invokeTag('checkBox','g',118,['name':("preprint"),'value':(versionInstance.preprint)],-1)
printHtmlPart(43)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'dateRedirect', 'error'))
printHtmlPart(44)
invokeTag('select','g',126,['class':("form-control"),'name':("dateRedirect"),'from':(DateRedirect.values()),'optionKey':("key"),'value':(versionInstance.dateRedirect),'noSelection':(['':'Select release date'])],-1)
printHtmlPart(45)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'preprintDate', 'error'))
printHtmlPart(46)
expressionOut.print(versionInstance.preprintDate?.format('MM/dd/yyyy h:mm a'))
printHtmlPart(47)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'epubDate', 'error'))
printHtmlPart(48)
expressionOut.print(versionInstance.epubDate?.format('MM/dd/yyyy h:mm a'))
printHtmlPart(47)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'journalDate', 'error'))
printHtmlPart(49)
expressionOut.print(versionInstance.journalDate?.format('MM/dd/yyyy h:mm a'))
printHtmlPart(50)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'releaseDate', 'error'))
printHtmlPart(51)
expressionOut.print(versionInstance.releaseDate?.format('MM/dd/yyyy h:mm a'))
printHtmlPart(52)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'publication', 'error'))
printHtmlPart(53)
expressionOut.print(versionInstance.publication?.officialName)
printHtmlPart(54)
invokeTag('field','g',171,['class':("form-control"),'type':("text"),'id':("publicationSearch"),'name':("publicationSearch"),'style':("margin-bottom:8px")],-1)
printHtmlPart(55)
invokeTag('select','g',172,['class':("form-control"),'multiple':("true"),'id':("publicationSearchResults"),'name':("publicationSearchResults"),'from':(""),'style':("margin-bottom:8px;height:195px")],-1)
printHtmlPart(56)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'authors', 'error'))
printHtmlPart(57)
invokeTag('join','g',181,['in':(versionInstance.authors),'delimiter':("\r\n")],-1)
printHtmlPart(58)
invokeTag('join','g',188,['in':(versionInstance.institutions*.canonicalName.sort()),'delimiter':("\r\n")],-1)
printHtmlPart(59)
invokeTag('field','g',192,['class':("form-control"),'type':("text"),'id':("institutionSearch"),'name':("institutionSearch"),'style':("margin-bottom:8px")],-1)
printHtmlPart(60)
invokeTag('select','g',193,['class':("form-control"),'id':("institutionSearchResults"),'name':("institutionSearchResults"),'from':(""),'multiple':("true"),'style':("margin-bottom:8px;height:199px")],-1)
printHtmlPart(61)
expressionOut.print(createLink(action: 'getPubMedData'))
printHtmlPart(62)
}
printHtmlPart(63)
if(true && (datasetInstance instanceof Study)) {
printHtmlPart(64)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'owners', 'error'))
printHtmlPart(65)
invokeTag('join','g',309,['in':(datasetInstance.owners*.username.sort()),'delimiter':("\r\n")],-1)
printHtmlPart(66)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'viewer', 'error'))
printHtmlPart(67)
invokeTag('join','g',328,['in':(datasetInstance.viewers*.username.sort()),'delimiter':("\r\n")],-1)
printHtmlPart(68)
}
printHtmlPart(69)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'accessAgreement', 'error'))
printHtmlPart(70)
for( terms in (Terms.findAllByTitleNotEqual('Terms and Conditions for Uploading Data to the Washington University (WU) BALSA Database')) ) {
printHtmlPart(71)
expressionOut.print(terms.id)
printHtmlPart(24)
if(true && (datasetInstance.accessAgreements.contains(terms))) {
printHtmlPart(25)
}
printHtmlPart(72)
expressionOut.print(terms.title)
printHtmlPart(73)
expressionOut.print(terms.id)
printHtmlPart(74)
expressionOut.print(terms.id)
printHtmlPart(75)
expressionOut.print(terms.title)
printHtmlPart(76)
createTagBody(4, {->
expressionOut.print(terms.contents)
})
invokeTag('encodeAs','g',354,['codec':("PreserveWhitespace")],4)
printHtmlPart(77)
}
printHtmlPart(78)
expressionOut.print(datasetInstance.customTermsTitle ?: 'No new terms')
printHtmlPart(79)
if(true && (versionInstance.sceneFiles().size() == 0)) {
printHtmlPart(80)
}
printHtmlPart(81)
for( sceneFile in (versionInstance.sceneFiles()) ) {
printHtmlPart(82)
expressionOut.print(sceneFile.filename)
printHtmlPart(83)
expressionOut.print(sceneFile.label ?: sceneFile.filename)
printHtmlPart(84)
}
printHtmlPart(85)
expressionOut.print(hasErrors(bean: datasetInstance, field: 'focusScene', 'error'))
printHtmlPart(86)
invokeTag('select','g',399,['class':("form-control"),'style':("width:850px;display:inline-block"),'id':("focusScene"),'name':("focusScene"),'from':(versionInstance.scenes()),'optionKey':({ scene -> "${scene.sceneLine.id}\" data-sceneId=\"${scene.id}"}),'optionValue':("name")],-1)
printHtmlPart(87)
invokeTag('field','g',413,['class':("form-control"),'type':("text"),'id':("customTermsTitle"),'name':("customTermsTitle"),'value':(datasetInstance.customTermsTitle)],-1)
printHtmlPart(88)
invokeTag('textArea','g',417,['class':("form-control"),'id':("customTermsContent"),'name':("customTermsContent"),'value':(datasetInstance.customTermsContent),'style':("max-width:100%;resize:none"),'rows':("20")],-1)
printHtmlPart(89)
})
invokeTag('formRemote','g',430,['id':("datasetForm"),'name':("datasetForm"),'url':([controller: datasetTerm('item':datasetInstance), action: 'update', id: datasetInstance.id, params: [version: versionInstance.id]]),'method':("POST"),'onSuccess':("successfulSave(data);updateRecentActivity();")],2)
printHtmlPart(90)
expressionOut.print(versionInstance.focusScene?.id)
printHtmlPart(91)
expressionOut.print(versionInstance.focusScene?.id)
printHtmlPart(92)
expressionOut.print(createLink('controller':'profile','action':'search'))
printHtmlPart(93)
expressionOut.print(createLink('controller':'institution','action':'search'))
printHtmlPart(94)
expressionOut.print(createLink('controller':'publication','action':'search'))
printHtmlPart(95)
})
invokeTag('captureBody','sitemesh',575,[:],1)
printHtmlPart(96)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1598462123778L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
