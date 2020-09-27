package balsa

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class AboutController {

    def index() { }
	
	def editing() { }
	
	def fileTypes() { }
	
	def help() { }
	
	def submission() { }
}
