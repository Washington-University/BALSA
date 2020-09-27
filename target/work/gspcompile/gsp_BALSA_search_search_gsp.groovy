import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_search_search_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/search/_search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('select','g',23,['class':("form-control"),'style':("margin-left:5px;display:inline-block;width:auto"),'name':("rows"),'from':(1..20),'value':("2"),'onchange':("refreshSearchResults()")],-1)
printHtmlPart(1)
invokeTag('select','g',26,['class':("form-control"),'style':("margin-left:5px;display:inline-block;width:auto"),'name':("columns"),'from':(1..4),'value':("3"),'onchange':("refreshSearchResults()")],-1)
printHtmlPart(2)
invokeTag('select','g',29,['class':("form-control"),'style':("margin-left:5px;display:inline-block;width:auto"),'name':("sortBy"),'from':(['Newest','Oldest','Name']),'onchange':("refreshSearchResults()")],-1)
printHtmlPart(3)
invokeTag('select','g',32,['id':("visibilityControl"),'class':("form-control"),'style':("margin-left:5px;display:inline-block;width:auto"),'name':("sceneFileVisibility"),'from':(['Hide', 'Show']),'onchange':("changeSceneFileVisibility()")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1555971501580L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
