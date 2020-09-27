import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_templates_linkedSceneList_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_linkedSceneList.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( dataset in (currentDatasetVersion.linkedDatasets()) ) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(dataset.shortTitle ?: dataset.title)
printHtmlPart(3)
})
invokeTag('link','g',6,['action':("show"),'controller':(datasetTerm('item':dataset)),'id':(dataset.id)],2)
printHtmlPart(4)
for( scene in (currentDatasetVersion.linkedScenesByDataset(dataset)) ) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(scene.name?.encodeAsHTML())
})
invokeTag('link','g',10,['controller':("scene"),'action':("show"),'id':(scene.sceneLine.id)],3)
printHtmlPart(6)
}
printHtmlPart(7)
}
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1586386884616L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
