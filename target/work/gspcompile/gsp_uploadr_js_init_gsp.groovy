import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='uploadr', version='1.2.6')
class gsp_uploadr_js_init_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/uploadr-1.2.6/grails-app/views/js/_init.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(classname)
printHtmlPart(1)
expressionOut.print(name)
printHtmlPart(2)
if(true && (handlers.onStart)) {
printHtmlPart(3)
expressionOut.print(handlers.onStart)
printHtmlPart(4)
}
if(true && (handlers.onProgress)) {
printHtmlPart(5)
expressionOut.print(handlers.onProgress)
printHtmlPart(4)
}
if(true && (handlers.onSuccess)) {
printHtmlPart(6)
expressionOut.print(handlers.onSuccess)
printHtmlPart(4)
}
if(true && (handlers.onLike)) {
printHtmlPart(7)
expressionOut.print(handlers.onLike)
printHtmlPart(4)
}
if(true && (handlers.onUnlike)) {
printHtmlPart(8)
expressionOut.print(handlers.onUnlike)
printHtmlPart(4)
}
if(true && (handlers.onChangeColor)) {
printHtmlPart(9)
expressionOut.print(handlers.onChangeColor)
printHtmlPart(4)
}
printHtmlPart(10)
if(true && (handlers.onFailure)) {
expressionOut.print(handlers.onFailure)
}
printHtmlPart(11)
if(true && (handlers.onAbort)) {
expressionOut.print(handlers.onAbort)
}
printHtmlPart(12)
if(true && (handlers.onView)) {
expressionOut.print(handlers.onView)
}
else {
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (handlers.onDelete)) {
expressionOut.print(handlers.onDelete)
}
else {
printHtmlPart(15)
invokeTag('createLink','g',26,['plugin':("uploadr"),'controller':("upload"),'action':("delete")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (handlers.onDownload)) {
expressionOut.print(handlers.onDownload)
}
else {
printHtmlPart(18)
invokeTag('createLink','g',42,['plugin':("uploadr"),'controller':("upload"),'action':("download")],-1)
printHtmlPart(19)
expressionOut.print(name)
printHtmlPart(20)
}
printHtmlPart(21)
if(true && (classname != 'uploadr')) {
printHtmlPart(22)
expressionOut.print(classname)
printHtmlPart(23)
expressionOut.print(classname)
printHtmlPart(24)
}
printHtmlPart(25)
expressionOut.print(uri)
printHtmlPart(26)
if(true && (sound)) {
printHtmlPart(27)
expressionOut.print(resource(plugin: 'uploadr', dir:'sounds', file:'notify.wav'))
printHtmlPart(28)
expressionOut.print(resource(plugin: 'uploadr', dir:'sounds', file:'error.wav'))
printHtmlPart(29)
expressionOut.print(resource(plugin: 'uploadr', dir:'sounds', file:'delete.wav'))
printHtmlPart(26)
}
printHtmlPart(30)
invokeTag('message','g',50,['code':("uploadr.label.done")],-1)
printHtmlPart(31)
invokeTag('message','g',51,['code':("uploadr.label.failed")],-1)
printHtmlPart(32)
invokeTag('message','g',52,['code':("uploadr.label.aborted")],-1)
printHtmlPart(33)
if(true && (fileselect)) {
expressionOut.print(fileselect)
}
else {
invokeTag('message','g',53,['code':("uploadr.button.select")],-1)
}
printHtmlPart(34)
if(true && (placeholder)) {
expressionOut.print(placeholder)
}
else {
invokeTag('message','g',54,['code':("uploadr.placeholder.text")],-1)
}
printHtmlPart(35)
invokeTag('message','g',55,['code':("uploadr.button.delete")],-1)
printHtmlPart(36)
invokeTag('message','g',56,['code':("uploadr.button.delete.confirm")],-1)
printHtmlPart(37)
invokeTag('message','g',57,['code':("uploadr.button.abort")],-1)
printHtmlPart(38)
invokeTag('message','g',58,['code':("uploadr.button.abort.confirm")],-1)
printHtmlPart(39)
invokeTag('message','g',59,['code':("uploadr.button.download")],-1)
printHtmlPart(40)
invokeTag('message','g',60,['code':("uploadr.button.view")],-1)
printHtmlPart(41)
invokeTag('message','g',61,['code':("uploadr.error.maxsize")],-1)
printHtmlPart(42)
invokeTag('message','g',62,['code':("uploadr.label.maxsize")],-1)
printHtmlPart(43)
invokeTag('message','g',63,['code':("uploadr.label.paused")],-1)
printHtmlPart(44)
invokeTag('message','g',64,['code':("uploadr.error.maxConcurrentUploadsExceededSingular")],-1)
printHtmlPart(45)
invokeTag('message','g',65,['code':("uploadr.error.maxConcurrentUploadsExceededPlural")],-1)
printHtmlPart(46)
invokeTag('message','g',66,['code':("uploadr.error.wrongExtension")],-1)
printHtmlPart(47)
invokeTag('message','g',67,['code':("uploadr.label.invalidFileExtension")],-1)
printHtmlPart(48)
invokeTag('message','g',68,['code':("uploadr.button.like")],-1)
printHtmlPart(49)
invokeTag('message','g',69,['code':("uploadr.button.remove")],-1)
printHtmlPart(50)
invokeTag('message','g',70,['code':("uploadr.button.unlike")],-1)
printHtmlPart(51)
invokeTag('message','g',71,['code':("uploadr.badge.tooltip.singular")],-1)
printHtmlPart(52)
invokeTag('message','g',72,['code':("uploadr.badge.tooltip.plural")],-1)
printHtmlPart(53)
invokeTag('message','g',73,['code':("uploadr.button.color.picker")],-1)
printHtmlPart(54)
expressionOut.print(maxVisible)
printHtmlPart(55)
expressionOut.print(maxConcurrentUploads)
printHtmlPart(56)
expressionOut.print(maxConcurrentUploadsMethod)
printHtmlPart(57)
expressionOut.print(rating as String)
printHtmlPart(58)
expressionOut.print(voting as String)
printHtmlPart(59)
expressionOut.print(colorPicker as String)
printHtmlPart(60)
expressionOut.print(deletable as String)
printHtmlPart(61)
expressionOut.print(viewable as String)
printHtmlPart(62)
expressionOut.print(downloadable as String)
printHtmlPart(63)
expressionOut.print(allowedExtensions as String)
printHtmlPart(64)
expressionOut.print(direction)
printHtmlPart(65)
expressionOut.print(name)
printHtmlPart(66)
loop:{
int s = 0
for( file in (files) ) {
printHtmlPart(67)
expressionOut.print(s)
printHtmlPart(68)
expressionOut.print(file.deletable)
printHtmlPart(69)
expressionOut.print(file.name.replaceAll("'","\\\\'"))
printHtmlPart(70)
expressionOut.print(file.size)
printHtmlPart(71)
expressionOut.print(file.id.replaceAll("'","\\\\'"))
printHtmlPart(72)
expressionOut.print(file.modified)
if(true && (file.color)) {
printHtmlPart(73)
expressionOut.print(file.color)
printHtmlPart(74)
}
if(true && (file.rating)) {
printHtmlPart(75)
expressionOut.print(file.rating)
}
if(true && (file.ratingText)) {
printHtmlPart(76)
expressionOut.print(file.ratingText.replaceAll("'","\\\\'"))
printHtmlPart(74)
}
if(true && (file.view)) {
printHtmlPart(77)
loop:{
int i = 0
for( info in (file.info) ) {
printHtmlPart(78)
expressionOut.print(info)
printHtmlPart(74)
if(true && ((i+1) < file.info.size())) {
printHtmlPart(79)
}
i++
}
}
printHtmlPart(80)
}
printHtmlPart(81)
if(true && ((s+1) < files.size())) {
printHtmlPart(79)
}
s++
}
}
printHtmlPart(82)
expressionOut.print(maxSize)
printHtmlPart(83)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450849804734L
public static final String EXPRESSION_CODEC = 'raw'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
