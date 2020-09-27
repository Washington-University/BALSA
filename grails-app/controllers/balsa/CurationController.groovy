package balsa

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_CURATOR'])
class CurationController extends AbstractBalsaController {
	def twitter4jService

    def index() {
        params.max = 10
		def mainQueue = Version.createCriteria().list() {
			eq("status", Version.Status.SUBMITTED)
		}
		def myQueue = Dataset.createCriteria().list() {
			eq('curator', springSecurityService.currentUser)
		}
		def approvedQueue = Version.createCriteria().list() {
			eq("status", Version.Status.APPROVED)
		}
		
		[mainQueue: mainQueue, myQueue: myQueue, approvedQueue: approvedQueue]
	}
	
	@Transactional
	def addToMyQueue(Dataset datasetInstance) {
		if (notFound(datasetInstance)) return
		
		datasetInstance.curator = springSecurityService.currentUser
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
