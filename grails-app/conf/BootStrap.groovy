import balsa.Dataset
import balsa.Profile
import balsa.Reference
import balsa.Study
import balsa.Version
import balsa.security.Approval
import balsa.security.BalsaUser
import balsa.security.OpenAccessTerms
import balsa.security.Role
import balsa.security.SubmissionTerms
import balsa.security.Terms
import balsa.security.UserRole

class BootStrap {
	def grailsApplication

    def init = { servletContext ->
		// set up basic roles if they don't already exist
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def curatorRole = Role.findByAuthority('ROLE_CURATOR') ?: new Role(authority: 'ROLE_CURATOR').save(flush: true)
		def submitterRole = Role.findByAuthority('ROLE_SUBMITTER') ?: new Role(authority: 'ROLE_SUBMITTER').save(flush: true)
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(flush: true)

		// create admin accounts for specified users, if they don't already exist
		if (UserRole.findByRole(adminRole) == null) {
			grailsApplication.config.balsa.defaultAdmins.each {
				def adminUser = new BalsaUser(username: it, profile: new Profile())
				adminUser.save(flush: true)
				UserRole.create(adminUser, adminRole, true)
			}
		}
		
		// set up HCP Open Access data use terms and Restricted Access approval entity if they don't already exist
		if (Terms.findByTitle(grailsApplication.config.HCP.openAccess.title) == null) {
			new OpenAccessTerms(title: grailsApplication.config.HCP.openAccess.title, contents: grailsApplication.config.HCP.openAccess.contents).save(flush: true)
		}
		if (Approval.findByTitle(grailsApplication.config.HCP.restrictedAccess.title) == null) {
			new Approval(title: grailsApplication.config.HCP.restrictedAccess.title, contents: grailsApplication.config.HCP.restrictedAccess.contents, link: grailsApplication.config.HCP.restrictedAccess.link).save(flush: true)
		}
		
		// set up BALSA Submission terms
		if (Terms.findByTitle(grailsApplication.config.BALSA.submissionTerms.title) == null) {
			new SubmissionTerms(title: grailsApplication.config.BALSA.submissionTerms.title, contents: grailsApplication.config.BALSA.submissionTerms.contents).save(flush: true)
		}
		
		// create versions for datasets that do not currently have versions
		for (study in Study.list()) {
			if (!study.versions) {
				study.addToVersions(new Version(study)).save()
			}
			if (study.status == Dataset.Status.PUBLIC && !study.publicDate) { study.publicDate = study.actualReleaseDate() ?: study.createdDate }
		}
		for (reference in Reference.list()) {
			if (!reference.versions) {
				reference.addToVersions(new Version(reference)).save()
			}
			if (reference.status == Dataset.Status.PUBLIC && !reference.publicDate) { reference.publicDate = reference.createdDate }
		}
    }
    def destroy = {
    }
	
}
