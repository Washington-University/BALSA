package balsa


import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import grails.util.Environment

@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class ContactController extends AbstractBalsaController {
	def recaptchaService
	
	@Transactional()
	def tech() {
		if (!recaptchaService.verifyAnswer(session, params)) {
			render(status: 418)
			return
		}
		
		def techMessage = new TechMessage()
		techMessage.createdBy = userService.current
		techMessage.title = params.title
		techMessage.contents = params.contents
		techMessage.createdDate = new Date()
		techMessage.save flush:true
		
		if (Environment.current == Environment.PRODUCTION) {
			mailService.sendMail {
				to  grailsApplication.config.balsa.techContacts
				from 'noreply@balsa.wustl.edu'
				subject 'BALSA Technical Issue (' + techMessage.id + ')' + (techMessage.title ? (': ' + techMessage.title.replaceAll('<','&lt;').replaceAll('>','&gt;')) : '')
				html 'A BALSA technical issue has been submitted:<br><br>"' +
					techMessage.contents?.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('\n','<br>') + 
					'"<br><br>The submitter\'s username is ' + techMessage.createdBy.username + ' and their email address is <a href="mailto:' + 
					techMessage.createdBy?.profile?.emailAddress + '">' + techMessage.createdBy?.profile?.emailAddress + '</a>'
			}
		}
		
		render(status: 200)
	}
	
	def curators() {
		if (!recaptchaService.verifyAnswer(session, params)) {
			render(status: 418)
			return
		}

		def contents = params.contents?.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('\n','<br>')
		def emailAddress = params.emailAddress ?: userService.current?.profile?.emailAddress
		def emailClause = emailAddress ? ('<br><br>Replies should be sent to <a href="mailto:' +
				emailAddress.replaceAll('<','&lt;').replaceAll('>','&gt;') + '">' +
				emailAddress.replaceAll('<','&lt;').replaceAll('>','&gt;') + '</a>') : ''

		mailService.sendMail {
			to  grailsApplication.config.balsa.curatorContacts
			from 'noreply@balsa.wustl.edu'
			subject 'BALSA Message: ' + params.subject
			html userService.current.username + ' sent the following message to the BALSA curators:<br><br>"' + contents + '"' + emailClause
		}
		
		render(status: 200)
	}
		
	@Secured(['ROLE_ADMIN'])
	def config() {}
	
	@Secured(['ROLE_ADMIN'])
	def techMessages() {
		[messages: TechMessage.findAllByResolvedIsNull()]
	}
	
	@Secured(['ROLE_ADMIN'])
	def resolvedTechMessages() {
		[messages: TechMessage.findAllByResolvedIsNotNull()]
	}
	
	@Transactional()
	@Secured(['ROLE_ADMIN'])
	def resolveTechMessage(TechMessage techMessage) {
		if (notFound(techMessage)) return
		
		techMessage.resolvedReason = params.resolvedReason
		techMessage.resolved = new Date()
		techMessage.save flush:true
		
		if (Environment.current == Environment.PRODUCTION) {
			mailService.sendMail {
				to  grailsApplication.config.balsa.techContacts
				from 'noreply@balsa.wustl.edu'
				subject 'BALSA Technical Issue (' + techMessage.id + ')' + (techMessage.title ? (': ' + techMessage.title.replaceAll('<','&lt;').replaceAll('>','&gt;')) : '')
				html 'A BALSA technical issue has been marked resolved.<br><br>The original message was:<br><br>"' +
					techMessage.contents?.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('\n','<br>') +
					(techMessage.resolvedReason ? ('"<br><br>The reason for resolving is:<br><br>"' + techMessage.resolvedReason.replaceAll('\n','<br>')) : '') + 
					'"<br><br>The submitter\'s username is ' + techMessage.createdBy.username + ' and their email address is <a href="mailto:' + 
					techMessage.createdBy?.profile?.emailAddress + '">' + techMessage.createdBy?.profile?.emailAddress + '</a>'
			}
		}
		
		redirect action: 'techMessages'
	}	
	
	@Transactional()
	@Secured(['ROLE_ADMIN'])
	def deleteTechMessage(TechMessage techMessage) {
		if (notFound(techMessage)) return
		
		techMessage.delete()
		
		redirect action: 'techMessages'
	}
}
