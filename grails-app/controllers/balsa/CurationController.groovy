package balsa

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

@Secured(['ROLE_CURATOR'])
class CurationController extends AbstractBalsaController {
	def twitter4jService

    def index() {
		def queue = Version.createCriteria().list() {
			or {
				'in'("status", [Version.Status.SUBMITTED, Version.Status.APPROVED])
//				dataset {
//					isNotNull("curator")
//				}
			}
		}
		
		[queue: queue]
	}
	
	@Transactional
	def addToMyQueue(Dataset datasetInstance) {
		if (notFound(datasetInstance)) return
		
		datasetInstance.curator = userService.current
		datasetInstance.save(flush:true)
		
		redirect action: 'index'
	}
	
	@Transactional
	def removeFromMyQueue(Dataset datasetInstance) {
		if (notFound(datasetInstance)) return
		
		datasetInstance.curator = null
		datasetInstance.save(flush:true)
		
		redirect action: 'index'
	}
	
	def tweet() {
		
	}
	
	def postTweet() {
		twitter4jService.updateStatus(params.tweetText)
		render(status: 200)
	}
}
