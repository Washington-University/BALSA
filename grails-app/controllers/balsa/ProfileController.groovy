package balsa

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.security.BalsaUser


@Transactional(readOnly = true)
	@Secured("ROLE_USER")
class ProfileController extends AbstractBalsaController {
	static defaultAction = 'show'

    def show(BalsaUser balsaUser) {
		if (notFound(balsaUser)) return
		
        [profileInstance: balsaUser.profile, ownProfile: (balsaUser == springSecurityService.currentUser)]
    }
	
	def mine() {
		render view: 'show', model: [profileInstance: springSecurityService.currentUser.profile, ownProfile: true]
	}
	
	def downloads() {
		redirect controller: 'download', action: 'mine'
	}
	
	def terms() {
		redirect controller: 'terms', action: 'mine'
	}
	
    def edit() {
        [profileInstance: springSecurityService.currentUser.profile]
    }

    @Transactional
	@Secured("@balsaSecurityService.isOwnProfile(#this) || hasRole('ROLE_ADMIN')")
    def update(Profile profileInstance) {
		if (notFound(profileInstance)) return
		if (hasErrors(profileInstance, 'edit', 'profileInstance')) return

        profileInstance.save flush:true

		redirect action: 'show', id: profileInstance.user.id
    }
	
	@Secured("ROLE_SUBMITTER")
	def search()
	{
		def c = Profile.createCriteria()
		def results = c.list {
			or {
				ilike("fullname", '%' + params.searchText.replaceAll(' ', '%') + '%')
				ilike("emailAddress", '%' + params.searchText.replaceAll(' ', '%') + '%')
				user {
					ilike("username", '%' + params.searchText.replaceAll(' ', '%') + '%')
				}
			}
			user {
				projections {
					distinct("username")
				}
				order("username", "asc")
			}
		}
		if (results.size() > 7) render (['Too many results. Please refine your search.'] as JSON)
		else render results as JSON
	}
}
