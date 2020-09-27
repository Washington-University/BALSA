import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BALSA_securityInfousercache_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/securityInfo/usercache.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutUi)],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',4,['code':("spring.security.ui.menu.securityInfo.usercache")],-1)
})
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
if(true && (cache)) {
printHtmlPart(3)
invokeTag('message','g',9,['code':("spring.security.ui.info.usercache.classname"),'args':([cache.getClass().name])],-1)
printHtmlPart(1)
createTagBody(3, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("spring.security.ui.info.usercache.label.size")],-1)
printHtmlPart(5)
expressionOut.print(cache.size)
printHtmlPart(6)
invokeTag('message','g',16,['code':("spring.security.ui.info.usercache.label.status")],-1)
printHtmlPart(5)
expressionOut.print(cache.status)
printHtmlPart(7)
invokeTag('message','g',20,['code':("spring.security.ui.info.usercache.label.name")],-1)
printHtmlPart(5)
expressionOut.print(cache.name)
printHtmlPart(6)
invokeTag('message','g',24,['code':("spring.security.ui.info.usercache.label.guid")],-1)
printHtmlPart(5)
expressionOut.print(cache.guid - 'Precision.M4800/')
printHtmlPart(8)
createTagBody(4, {->
printHtmlPart(9)
invokeTag('message','g',31,['code':("spring.security.ui.info.usercache.label.stats.cacheHits")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.cacheHitCount())
printHtmlPart(11)
invokeTag('message','g',35,['code':("spring.security.ui.info.usercache.label.stats.memoryHits")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.localHeapHitCount())
printHtmlPart(11)
invokeTag('message','g',39,['code':("spring.security.ui.info.usercache.label.stats.diskHits")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.localDiskHitCount())
printHtmlPart(11)
invokeTag('message','g',43,['code':("spring.security.ui.info.usercache.label.stats.cacheMisses")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.cacheMissCount())
printHtmlPart(11)
invokeTag('message','g',47,['code':("spring.security.ui.info.usercache.label.stats.objectCount")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.size)
printHtmlPart(11)
invokeTag('message','g',51,['code':("spring.security.ui.info.usercache.label.stats.memoryObjectCount")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.localHeapSize)
printHtmlPart(11)
invokeTag('message','g',55,['code':("spring.security.ui.info.usercache.label.stats.diskObjectCount")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.localDiskSize)
printHtmlPart(11)
invokeTag('message','g',59,['code':("spring.security.ui.info.usercache.label.stats.evictionCount")],-1)
printHtmlPart(10)
expressionOut.print(cache.statistics.cacheEvictedCount())
printHtmlPart(12)
})
invokeTag('securityInfoTable','s2ui',62,['type':("usercache.statistics"),'headerCodes':("attribute,value")],4)
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
expressionOut.print(it)
printHtmlPart(15)
expressionOut.print(cache.get(it)?.value)
printHtmlPart(16)
})
invokeTag('securityInfoTable','s2ui',70,['type':("usercache.cachedUsers"),'items':(cache.keys),'headerCodes':("username,user"),'captionArgs':([cache.size])],4)
printHtmlPart(17)
})
invokeTag('securityInfoTable','s2ui',73,['type':("usercache"),'headerCodes':("attribute,value")],3)
printHtmlPart(1)
}
else {
printHtmlPart(18)
invokeTag('message','g',76,['code':("spring.security.ui.info.usercache.disabled")],-1)
printHtmlPart(19)
}
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',78,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1451942259897L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
