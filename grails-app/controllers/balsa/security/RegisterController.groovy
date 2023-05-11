package balsa.security

import grails.async.Promise
import grails.async.Promises
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.CommandObject
import grails.plugin.springsecurity.ui.ForgotPasswordCommand
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.ResetPasswordCommand
import grails.gorm.transactions.Transactional

import javax.naming.NameNotFoundException

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

import balsa.Profile


@Transactional(readOnly = true)
@Secured(['permitAll'])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
	def springSecurityService
	def recaptchaService
	def userService

	def test() {
		Promise asyncEmail = Promises.task {
			Thread.sleep(10000)
			uiMailStrategy.sendVerifyRegistrationMail(
					to: 'john.smith@wustl.edu',
					from: 'noreply@balsa.wustl.edu',
					subject: 'BALSA Username',
					html: '<p>Your <a href="http://balsa.wustl.edu">BALSA</a> username is <b>PLEASE</b></p>.')
		}
		asyncEmail.onComplete() {}
		render "success?"
	}

	@Transactional
	def register(RegisterCommand registerCommand) {
		
		if (!request.post) {
			return [registerCommand: new RegisterCommand()]
		}
		
		if (!recaptchaService.verifyAnswer(session, params)) {
			registerCommand.errors.reject('', 'Your reCAPTCHA answer was incorrect')
		}

		if (BalsaUser.findByUsername(registerCommand.username)) {
			registerCommand.errors.rejectValue('username', 'registerCommand.username.unique')
		}
		
		if (registerCommand.hasErrors()) {
			return [registerCommand: registerCommand]
		}
		
		// create the local user object that will have a profile and so forth
		def user = BalsaUser.newInstance(username: registerCommand.username, password: springSecurityService.encodePassword(registerCommand.password), accountLocked: false, enabled: true, profile: new Profile(emailAddress: registerCommand.email)).save()
		UserRole.create(user, Role.findByAuthority('ROLE_USER'), true) // give new user basic access by default

		def body = registerEmailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user])
		}

		Promise asyncEmail = Promises.task {
			uiMailStrategy.sendVerifyRegistrationMail(
					to: registerCommand.email,
					from: registerEmailFrom,
					subject: registerEmailSubject,
					html: body)
		}
		asyncEmail.onComplete() {}

		springSecurityService.reauthenticate(registerCommand.username, registerCommand.password)
		
		redirect uri: '/'
	}
	
	@Transactional
	@Override // altered to correctly access email via user's profile
	def forgotPassword(ForgotPasswordCommand forgotPasswordCommand) {
		if (!recaptchaService.verifyAnswer(session, params)) {
			render(status: 418)
			return
		}

		if (!request.post) {
			return [forgotPasswordCommand: new ForgotPasswordCommand()]
		}

		if (forgotPasswordCommand.hasErrors()) {
			return [forgotPasswordCommand: forgotPasswordCommand]
		}

		def user = findUserByUsername(forgotPasswordCommand.username)
		if (!user) {
			forgotPasswordCommand.errors.rejectValue 'username',
				'spring.security.ui.forgotPassword.user.notFound'
			return [forgotPasswordCommand: forgotPasswordCommand]
		}

		String email = user.profile.emailAddress
		if (!email) {
			forgotPasswordCommand.errors.rejectValue 'username',
				'spring.security.ui.forgotPassword.noEmail'
			return [forgotPasswordCommand: forgotPasswordCommand]
		}

		RegistrationCode registrationCode = uiRegistrationCodeStrategy.sendForgotPasswordMail(
				forgotPasswordCommand.username, email) { String registrationCodeToken ->

			String url = generateLink('resetPassword', [t: registrationCodeToken])
			String body = forgotPasswordEmailBody
			if (body.contains('$')) {
				body = evaluate(body, [user: user, url: url])
			}

			body
		}
		[emailSent: true, forgotPasswordCommand: forgotPasswordCommand]
	}

	@Transactional
	def resetPassword(ResetPasswordCommand resetPasswordCommand) {
		String token = params.t

		def registrationCode = token ? RegistrationCode.findByToken(token) : null
		if (!registrationCode) {
			flash.error = message(code: 'spring.security.ui.resetPassword.badCode')
			redirect uri: successHandlerDefaultTargetUrl
			return
		}

		if (!request.post) {
			return [token: token, resetPasswordCommand: new ResetPasswordCommand()]
		}

		resetPasswordCommand.username = registrationCode.username
		resetPasswordCommand.validate()
		if (resetPasswordCommand.hasErrors()) {
			return [token: token, resetPasswordCommand: resetPasswordCommand]
		}

		def user = uiRegistrationCodeStrategy.resetPassword(resetPasswordCommand, registrationCode)
		if (user.hasErrors()) {}

		registrationCode.delete()
		springSecurityService.reauthenticate registrationCode.username

		flash.message = message(code: 'spring.security.ui.resetPassword.success')
		
		redirect uri: registerPostResetUrl ?: successHandlerDefaultTargetUrl
	}
	
	@Secured("ROLE_USER")
	@Transactional
	def changePassword() {
		BalsaUser user = userService.current
		user.password = springSecurityService.encodePassword(params.newPassword)
		user.save()
		render(status: 200)
	}
	
	def forgotUsername(ForgotUsernameCommand forgotUsernameCommand) {
		if (!recaptchaService.verifyAnswer(session, params)) {
			render(status: 418)
			return
		}

		if (!request.post) {
			return [forgotUsernameCommand: new ForgotUsernameCommand()]
		}

		if (forgotUsernameCommand.hasErrors()) {
			return [forgotUsernameCommand: forgotUsernameCommand]
		}

		def user = Profile.findByEmailAddress(forgotUsernameCommand.email)?.user
		if (!user) {
			forgotUsernameCommand.errors.rejectValue 'email',
				'spring.security.ui.forgotPassword.user.notFound'
			return [forgotUsernameCommand: forgotUsernameCommand]
		}
		
		uiMailStrategy.sendVerifyRegistrationMail(
			to: forgotUsernameCommand.email,
			from: 'noreply@balsa.wustl.edu',
			subject: 'BALSA Username',
			html: '<p>Your <a href="http://balsa.wustl.edu">BALSA</a> username is <b>' + user.username + '</b></p>.')
		
		[emailSent: true, forgotUsernameCommand: forgotUsernameCommand]
	}
	
	@Transactional
	@Secured("ROLE_USER")
	def delete()
	{
		BalsaUser balsaUser = userService.current
		def username = balsaUser.username
		
		UserRole.where({user==balsaUser}).deleteAll()
		balsaUser.studies.each {
			it.removeFromOwners(balsaUser)
		}
		balsaUser.ownedApprovals.each {
			it.removeFromBalsaUser(balsaUser)
		}
		balsaUser.delete(flush: true)
		
		request.logout()
		
		redirect uri: '/'
	}
}

class ForgotUsernameCommand implements CommandObject {
	String email
	
	static constraints = {
		email email: true
	}
}
