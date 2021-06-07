package balsa

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import balsa.security.Approval
import balsa.security.BalsaUser
import balsa.security.OpenAccessTerms
import balsa.security.Role
import balsa.security.SubmissionTerms
import balsa.security.Terms
import balsa.security.UserRole

class BootStrap {
	GrailsApplication grailsApplication
	
    def init = { servletContext ->
		setUpRoles()
		setUpTerms()
    }
    def destroy = {
    }
	
	@Transactional
	def setUpRoles() {
		// set up basic roles if they don't already exist
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
		def curatorRole = Role.findByAuthority('ROLE_CURATOR') ?: new Role(authority: 'ROLE_CURATOR').save()
		def submitterRole = Role.findByAuthority('ROLE_SUBMITTER') ?: new Role(authority: 'ROLE_SUBMITTER').save()
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()

		// create admin accounts for specified users, if they don't already exist
		if (UserRole.findByRole(adminRole) == null) {
			grailsApplication.config.balsa.defaultAdmins.each {
				def adminUser = new BalsaUser(username: it, profile: new Profile())
				adminUser.save()
				UserRole.create(adminUser, adminRole, true)
			}
		}
	}
	
	@Transactional
	def setUpTerms() {
		// set up HCP Open Access data use terms and Restricted Access approval entity if they don't already exist
		if (Terms.findByTitle(grailsApplication.config.HCP.openAccess.title) == null) {
			new OpenAccessTerms(title: grailsApplication.config.HCP.openAccess.title, contents: grailsApplication.config.HCP.openAccess.contents).save()
		}
		if (Approval.findByTitle(grailsApplication.config.HCP.restrictedAccess.title) == null) {
			new Approval(title: grailsApplication.config.HCP.restrictedAccess.title, contents: grailsApplication.config.HCP.restrictedAccess.contents, link: grailsApplication.config.HCP.restrictedAccess.link).save()
		}
		
		// set up BALSA Submission terms
		if (Terms.findByTitle(grailsApplication.config.HCP.submissionTerms.title) == null) {
			new SubmissionTerms(title: grailsApplication.config.HCP.submissionTerms.title, contents: grailsApplication.config.HCP.submissionTerms.contents).save()
		}
	}
}
