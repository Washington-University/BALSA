package balsa

import static org.springframework.http.HttpStatus.*

abstract class AbstractBalsaController {
	def grailsApplication
	def springSecurityService
	def balsaSecurityService
	def fileService
	def searchService
	def grailsMimeUtility
	def statsService
	def mailService
	def grailsLinkGenerator
	
	protected def notFound(Object ob) {
		if (ob == null) {
			render view: '/404', status: NOT_FOUND
		}
		ob == null
	}

	protected def hasErrors(Object ob, String view, String beanName) {
		ob.validate()
		if (ob.hasErrors()) {
			render view: view, model:[(beanName): ob]
		}
		ob.hasErrors()
	}	
}
