package balsa

import java.net.URL
import groovy.json.JsonSlurper

class RecaptchaService {
	def grailsApplication

	def getSiteKey() {
		grailsApplication.config.recaptcha.siteKey
	}
	
	def verifyAnswer(session, params) {
		def response = params['g-recaptcha-response']?.trim()
		def queryString = 'secret=' + grailsApplication.config.recaptcha.secret + '&response=' + response
		
		def url = new URL('https://www.google.com/recaptcha/api/siteverify')
		def connection = url.openConnection()
		def text
		connection.with {
			doOutput = true
			requestMethod = 'POST'
			outputStream.withWriter { writer ->
				writer << queryString
			}
			text = content.text
		}
		
		def jsonSlurper = new JsonSlurper()
		
		jsonSlurper.parseText(text).success
		
		text
	}
}
