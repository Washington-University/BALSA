import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_search_datasetSearchResults_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/search/_datasetSearchResults.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( dataset in (searchResults.datasets) ) {
printHtmlPart(1)
invokeTag('render','g',3,['template':("datasetSearchResult"),'model':(['dataset':dataset])],-1)
printHtmlPart(2)
}
printHtmlPart(2)
if(true && (searchResults.datasets.size() < params.max)) {
printHtmlPart(2)
loop:{
int i = 0
for( thing in ((searchResults.datasets.size() .. (params.max - 1))) ) {
printHtmlPart(1)
invokeTag('render','g',7,['template':("emptySearchResult")],-1)
printHtmlPart(2)
i++
}
}
printHtmlPart(2)
}
printHtmlPart(3)
if(true && (searchResults.totalCount > Integer.valueOf(params.max))) {
printHtmlPart(4)
invokeTag('remotePaginate','util',14,['controller':("search"),'action':("datasetSearch"),'total':(searchResults.totalCount),'params':(params),'update':("datasetSearchResults"),'prev':("&lt;&lt;"),'next':("&gt;&gt;"),'onsuccess':("updateRecentActivity();")],-1)
printHtmlPart(5)
}
else {
printHtmlPart(6)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1589072972135L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
