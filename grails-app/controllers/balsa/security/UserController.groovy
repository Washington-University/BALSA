package balsa.security

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController extends grails.plugin.springsecurity.ui.UserController {
	def springSecurityService
	
	@Secured('ROLE_USER')
	def current() {
		render springSecurityService.currentUser as JSON
	}
	
	@Secured('ROLE_USER')
	def roles() {
		render springSecurityService.getPrincipal().authorities*.authority as JSON
	}
}
