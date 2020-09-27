import org.apache.commons.lang.RandomStringUtils
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='uploadr', version='1.2.6')
class gsp_uploadr_upload_default_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/uploadr-1.2.6/grails-app/views/upload/_default.gsp" }
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
invokeTag('add','uploadr',35,['name':("myFirstUploadr"),'path':(path1),'maxSize':("52428800")],-1)
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
invokeTag('fileSize','uploadr',44,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',45,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',46,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',47,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(0)
})
invokeTag('add','uploadr',49,['name':("mySecondUploadr"),'path':(path2),'allowedExtensions':("jpg,png,gif"),'direction':("up"),'maxVisible':("5"),'unsupported':(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning')),'maxSize':("52428800"),'model':([booleanOne:true, variableTwo: 'foo', variableThree: 'bar', variableFour: 4, myObject: someObject])],1)
printHtmlPart(8)
expressionOut.print(path2)
printHtmlPart(9)
expressionOut.print(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning'))
printHtmlPart(10)
for( file in (path2.listFiles()) ) {
printHtmlPart(11)
expressionOut.print(file.name)
printHtmlPart(12)
expressionOut.print(file.size())
printHtmlPart(13)
expressionOut.print(file.lastModified())
printHtmlPart(14)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(15)
}
printHtmlPart(16)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path3.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',66,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',67,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',68,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(new Random().nextFloat())
})
invokeTag('rating','uploadr',69,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',70,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(0)
})
invokeTag('add','uploadr',72,['name':("myThirdUploadr"),'path':(path3),'direction':("up"),'maxVisible':("5"),'maxSize':("512000"),'rating':("true"),'voting':("true"),'unsupported':(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning')),'maxConcurrentUploads':("2"),'maxConcurrentUploadsMethod':("pause")],1)
printHtmlPart(17)
expressionOut.print(path3)
printHtmlPart(18)
expressionOut.print(createLink(plugin: 'uploadr', controller: 'upload', action: 'warning'))
printHtmlPart(19)
for( file in (path3.listFiles()) ) {
printHtmlPart(11)
expressionOut.print(file.name)
printHtmlPart(12)
expressionOut.print(file.size())
printHtmlPart(13)
expressionOut.print(file.lastModified())
printHtmlPart(14)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(20)
expressionOut.print(new Random().nextFloat())
printHtmlPart(21)
}
printHtmlPart(22)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path4.listFiles()) ) {
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
createClosureForHtmlPart(23, 4)
invokeTag('color','uploadr',119,[:],4)
printHtmlPart(6)
createClosureForHtmlPart(24, 4)
invokeTag('deletable','uploadr',120,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(25)
expressionOut.print(file.name)
})
invokeTag('ratingText','uploadr',121,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',122,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('onStart','uploadr',127,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(28, 2)
invokeTag('onProgress','uploadr',131,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(29, 2)
invokeTag('onSuccess','uploadr',157,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(30, 2)
invokeTag('onFailure','uploadr',160,[:],2)
printHtmlPart(31)
createClosureForHtmlPart(32, 2)
invokeTag('onAbort','uploadr',165,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(33, 2)
invokeTag('onView','uploadr',170,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(34, 2)
invokeTag('onDownload','uploadr',175,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(35, 2)
invokeTag('onDelete','uploadr',183,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(36, 2)
invokeTag('onLike','uploadr',192,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(37, 2)
invokeTag('onUnlike','uploadr',201,[:],2)
printHtmlPart(4)
createClosureForHtmlPart(38, 2)
invokeTag('onChangeColor','uploadr',211,[:],2)
printHtmlPart(0)
})
invokeTag('add','uploadr',212,['name':("myFourthUploadr"),'path':(path4),'maxVisible':("5"),'rating':("true"),'voting':("true"),'colorPicker':("true"),'maxSize':("52428800")],1)
printHtmlPart(39)
expressionOut.print(path4)
printHtmlPart(40)
for( file in (path4.listFiles()) ) {
printHtmlPart(11)
expressionOut.print(file.name)
printHtmlPart(41)
expressionOut.print(file.size())
printHtmlPart(42)
expressionOut.print(file.lastModified())
printHtmlPart(43)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(44)
expressionOut.print(file.name)
printHtmlPart(45)
}
printHtmlPart(46)
expressionOut.print(resource(plugin:'uploadr', dir:'css', file:'demo.scss'))
printHtmlPart(47)
createTagBody(1, {->
printHtmlPart(4)
for( file in (path5.listFiles()) ) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.size())
})
invokeTag('fileSize','uploadr',320,[:],4)
printHtmlPart(6)
createTagBody(4, {->
expressionOut.print(file.lastModified())
})
invokeTag('fileModified','uploadr',321,[:],4)
printHtmlPart(6)
createTagBody(4, {->
printHtmlPart(7)
expressionOut.print(RandomStringUtils.random(32, true, true))
})
invokeTag('fileId','uploadr',322,[:],4)
printHtmlPart(5)
})
invokeTag('file','uploadr',323,['name':(file.name)],3)
printHtmlPart(4)
}
printHtmlPart(0)
})
invokeTag('add','uploadr',325,['name':("myFifthUploadr"),'path':(path5),'direction':("up"),'class':("demo"),'placeholder':("Behold: the drop area!"),'fileselect':("Behold: the fileselect!"),'maxVisible':("4"),'noSound':("true"),'maxSize':("52428800")],1)
printHtmlPart(48)
expressionOut.print(path5)
printHtmlPart(49)
for( file in (path5.listFiles()) ) {
printHtmlPart(11)
expressionOut.print(file.name)
printHtmlPart(41)
expressionOut.print(file.size())
printHtmlPart(42)
expressionOut.print(file.lastModified())
printHtmlPart(43)
expressionOut.print(RandomStringUtils.random(32, true, true))
printHtmlPart(50)
}
printHtmlPart(51)
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
