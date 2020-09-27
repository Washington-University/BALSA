import org.apache.commons.lang.RandomStringUtils
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='uploadr', version='1.2.6')
class gsp_uploadr_upload_demo_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/uploadr-1.2.6/grails-app/views/upload/_demo.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)

/**
 *  Uploadr, a multi-file uploader plugin
 *  Copyright (C) 2011 Jeroen Wesbeek
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

// Note that normally you should pass these variables to your view from your
// controller, as in MVC your controller should never contain business logic
// and just provide the view to your data. But for clarity sake I am breaking
// this rule and defining them in here...

def downloads = grailsApplication.config.uploadr.defaultUploadPath
def path1 = new File("${downloads}/myFirstUploadr")
def path2 = new File("${downloads}/mySecondUploadr")
def path3 = new File("${downloads}/myThirdUploadr")
def path4 = new File("${downloads}/myFourthUploadr")
def path5 = new File("${downloads}/myFifthUploadr")

printHtmlPart(1)
invokeTag('add','uploadr',52,['name':("myFirstUploadr"),'path':(path1),'maxSize':("52428800")],-1)
printHtmlPart(2)
expressionOut.print(path1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path2.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',79,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',80,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',81,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',82,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(8)
})
invokeTag('add','uploadr',84,['name':("mySecondUploadr"),'path':(path2),'allowedExtensions':("jpg,png,gif"),'direction':("up"),'maxVisible':("5"),'unsupported':(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning')),'maxSize':("52428800"),'model':([booleanOne:true, variableTwo: 'foo', variableThree: 'bar', variableFour: 4, myObject: someObject])],1)
printHtmlPart(9)
expressionOut.print(path2)
printHtmlPart(10)
expressionOut.print(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning'))
printHtmlPart(11)
for( file in (path2.listFiles()) ) {
printHtmlPart(12)
expressionOut.print(file.name)
printHtmlPart(13)
expressionOut.print(file.size())
printHtmlPart(14)
expressionOut.print(file.lastModified())
printHtmlPart(15)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(16)
}
printHtmlPart(17)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path3.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',116,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',117,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',118,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(new Random().nextFloat())
})
invokeTag('rating','uploadr',119,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',120,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(8)
})
invokeTag('add','uploadr',122,['name':("myThirdUploadr"),'path':(path3),'direction':("up"),'maxVisible':("5"),'maxSize':("512000"),'rating':("true"),'voting':("true"),'unsupported':(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning')),'maxConcurrentUploads':("2"),'maxConcurrentUploadsMethod':("pause")],1)
printHtmlPart(18)
expressionOut.print(path3)
printHtmlPart(19)
expressionOut.print(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning'))
printHtmlPart(20)
for( file in (path3.listFiles()) ) {
printHtmlPart(12)
expressionOut.print(file.name)
printHtmlPart(13)
expressionOut.print(file.size())
printHtmlPart(14)
expressionOut.print(file.lastModified())
printHtmlPart(15)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(21)
expressionOut.print(new Random().nextFloat())
printHtmlPart(22)
}
printHtmlPart(23)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path4.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',193,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',194,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',195,[:],4)
printHtmlPart(6)
createClosureForHtmlPart(24, 4)
invokeTag('color','uploadr',196,[:],4)
printHtmlPart(6)
createClosureForHtmlPart(25, 4)
invokeTag('deletable','uploadr',197,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(26)
expressionOut.print(file.name)
})
invokeTag('ratingText','uploadr',198,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',199,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('onStart','uploadr',204,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(29, 2)
invokeTag('onProgress','uploadr',208,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(30, 2)
invokeTag('onSuccess','uploadr',234,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(31, 2)
invokeTag('onFailure','uploadr',237,[:],2)
printHtmlPart(32)
createClosureForHtmlPart(33, 2)
invokeTag('onAbort','uploadr',242,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(34, 2)
invokeTag('onView','uploadr',247,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(35, 2)
invokeTag('onDownload','uploadr',252,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(36, 2)
invokeTag('onDelete','uploadr',260,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(37, 2)
invokeTag('onLike','uploadr',269,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(38, 2)
invokeTag('onUnlike','uploadr',278,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(39, 2)
invokeTag('onChangeColor','uploadr',288,[:],2)
printHtmlPart(8)
})
invokeTag('add','uploadr',289,['name':("myFourthUploadr"),'path':(path4),'maxVisible':("5"),'rating':("true"),'voting':("true"),'colorPicker':("true"),'maxSize':("52428800")],1)
printHtmlPart(40)
expressionOut.print(path4)
printHtmlPart(41)
for( file in (path4.listFiles()) ) {
printHtmlPart(12)
expressionOut.print(file.name)
printHtmlPart(13)
expressionOut.print(file.size())
printHtmlPart(14)
expressionOut.print(file.lastModified())
printHtmlPart(15)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(42)
expressionOut.print(file.name)
printHtmlPart(43)
}
printHtmlPart(44)
expressionOut.print(resource(plugin:'uploadr', dir:'css', file:'demo.scss'))
printHtmlPart(45)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path5.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',412,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',413,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',414,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',415,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(8)
})
invokeTag('add','uploadr',417,['name':("myFifthUploadr"),'path':(path5),'direction':("up"),'class':("demo"),'placeholder':("Behold: the drop area!"),'fileselect':("Behold: the fileselect!"),'maxVisible':("4"),'noSound':("true"),'maxSize':("52428800")],1)
printHtmlPart(46)
expressionOut.print(path5)
printHtmlPart(47)
for( file in (path5.listFiles()) ) {
printHtmlPart(12)
expressionOut.print(file.name)
printHtmlPart(13)
expressionOut.print(file.size())
printHtmlPart(14)
expressionOut.print(file.lastModified())
printHtmlPart(15)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(16)
}
printHtmlPart(48)
expressionOut.print(new Date().format("yyyy"))
printHtmlPart(49)
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
