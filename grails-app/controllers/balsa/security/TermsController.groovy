package balsa.security

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import javax.naming.NamingException

import balsa.AbstractBalsaController

@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class TermsController extends AbstractBalsaController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Terms termsInstance) {
		if (notFound(termsInstance)) return
		
        [termsInstance: termsInstance]
    }

	@Transactional
	def agree(Terms termsInstance) {
		if (notFound(termsInstance)) return
		
		BalsaUser user = springSecurityService.currentUser
		try {
			termsInstance.agree(user)
			render(status: 200)
		}
		catch (Exception e) {
			if (termsInstance instanceof OpenAccessTerms && e instanceof NamingException) {
				render(status:409)
			}
			else {
				render(status: 500)
			}
		}
	}
	
	@Secured("permitAll")
	def submission() {
		def submissionTerms = Terms.findByTitle(grailsApplication.config.BALSA.submissionTerms.title)
		
		if (params.type =='JSON') {
			render submissionTerms.contents
			return
		}
		
		[submissionTerms: submissionTerms, alreadyAgreed: springSecurityService.currentUser?.agreedTerms?.contains(submissionTerms)]
	}
	
	def mine() {
		[terms: springSecurityService.currentUser?.agreedTerms.sort{it.title}]
	}
}
