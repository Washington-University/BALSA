package balsa.security

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.CommandObject
import grails.plugin.springsecurity.ui.ForgotPasswordCommand
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.ResetPasswordCommand
import grails.transaction.Transactional

import javax.naming.NameNotFoundException

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

import balsa.Profile


@Transactional(readOnly = true)
@Secured(['permitAll'])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
	def balsaUserDetailsService
	def ldapUsernameMapper
	def springSecurityService
	def recaptchaService
	
	def userAlreadyExists() {
		render balsaUserDetailsService.userExists(params.username)
	}
	
	@Transactional
	@Override // altered to create ldap user object as well as BalsaUser
	def register(RegisterCommand registerCommand) {
		
		if (!request.post) {
			return [registerCommand: new RegisterCommand()]
		}
		
//		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
//			registerCommand.errors.reject('', 'Your reCAPTCHA answer was incorrect')
//		}
		
		if (registerCommand.username.contains('@')) {
			registerCommand.errors.reject('username', 'Usernames may not contain @ symbols')
		}
		
		/* check LDAP for existing user with same username
		 * existing constraints will have already checked against the database, so don't re-check if
		 * a user with that name is already in the database or it will double up on errors
		 */
		if (!BalsaUser.findByUsername(registerCommand.username) && balsaUserDetailsService.userExists(registerCommand.username)) {
			registerCommand.errors.rejectValue('username', 'registerCommand.username.unique')
		}
		
		if (registerCommand.hasErrors()) {
			return [registerCommand: registerCommand]
		}
		
		// create the local user object that will have a profile and so forth
		def user = BalsaUser.newInstance(username: registerCommand.username, accountLocked: false, enabled: true, profile: new Profile(emailAddress: registerCommand.email)).save()
		UserRole.create(user, Role.findByAuthority('ROLE_USER'), true) // give new user basic access by default
		
		// create the basic user object in ldap
		balsaUserDetailsService.createUser(new User(registerCommand.username, registerCommand.password, new ArrayList<GrantedAuthority>()))
		
		RegistrationCode registrationCode = new RegistrationCode(username: registerCommand.username).save()
		
		sendVerifyRegistrationMail registrationCode, user, registerCommand.email
		
		springSecurityService.reauthenticate(registerCommand.username, registerCommand.password)
		
		redirect controller: 'profile', action: 'edit'
	}
	
	@Transactional
	@Override // altered to correctly access email via user's profile
	def forgotPassword(ForgotPasswordCommand forgotPasswordCommand) {
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
	
	
	@Override // altered to change password in ldap rather than BalsaUser object
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
		
		balsaUserDetailsService.changePassword(resetPasswordCommand.username, resetPasswordCommand.password)
		
		registrationCode.delete()
		springSecurityService.reauthenticate registrationCode.username

		flash.message = message(code: 'spring.security.ui.resetPassword.success')
		
		redirect uri: registerPostResetUrl ?: successHandlerDefaultTargetUrl
	}
	
	@Secured("ROLE_USER")
	def changePassword() {
		String username = springSecurityService.getCurrentUser().username

		balsaUserDetailsService.changePassword(username, params.newPassword)
		
		render(status: 200)
	}
	
	def forgotUsername(ForgotUsernameCommand forgotUsernameCommand) {

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
		BalsaUser balsaUser = springSecurityService.currentUser
		def username = balsaUser.username
		
		UserRole.where({user==balsaUser}).deleteAll()
		balsaUser.studies.each {
			it.removeFromOwners(balsaUser)
		}
		balsaUser.ownedApprovals.each {
			it.removeFromBalsaUser(balsaUser)
		}
		balsaUser.delete(flush: true)
		
		try {
			balsaUserDetailsService.deleteUser(username)
		}
		catch(NameNotFoundException e) {
			// almost certainly indicates an HCP user, whose LDAP account we can't delete
		}
		
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
