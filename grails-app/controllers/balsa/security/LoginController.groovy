package balsa.security

import grails.plugin.springsecurity.SpringSecurityUtils

class LoginController extends grails.plugin.springsecurity.LoginController {
	def auth() {
		def config = SpringSecurityUtils.securityConfig

		if (springSecurityService.isLoggedIn() || (!session.SPRING_SECURITY_SAVED_REQUEST && params.reqURL)) {
			redirect uri: params.reqURL ?: config.successHandler.defaultTargetUrl
			return
		}

		String view = 'auth'
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		render view: view, model: [postUrl: postUrl,
								   rememberMeParameter: config.rememberMe.parameter]
	}
	
	def keepAlive() {}
}
