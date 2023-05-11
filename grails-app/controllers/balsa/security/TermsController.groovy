package balsa.security


import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

import javax.naming.NamingException

import balsa.AbstractBalsaController

@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class TermsController extends AbstractBalsaController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Terms termsInstance) {
		if (notFound(termsInstance)) return
		
        [termsInstance: termsInstance, alreadyAgreed: userService.current?.agreedTerms?.contains(termsInstance)]
    }

	@Transactional
	def agree(Terms termsInstance) {
		if (notFound(termsInstance)) return
		
		BalsaUser user = userService.current
		try {
			termsInstance.agree(user)
			user.save(failOnError: true)
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
		def submissionTerms = Terms.findByTitle(grailsApplication.config.HCP.submissionTerms.title)
		
		if (params.type =='JSON') {
			render submissionTerms.contents
			return
		}
		
		[submissionTerms: submissionTerms, alreadyAgreed: userService.current?.agreedTerms?.contains(submissionTerms)]
	}
	
	def mine() {
		[terms: userService.current?.agreedTerms.sort{it.title}]
	}
}
