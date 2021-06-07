package balsa.security

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController extends grails.plugin.springsecurity.ui.UserController {
	def springSecurityService
	def userService
	
	@Secured('ROLE_USER')
	def current() {
		render userService.current as JSON
	}
	
	@Secured('ROLE_USER')
	def roles() {
		render springSecurityService.getPrincipal().authorities*.authority as JSON
	}
}
