package balsa

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.security.BalsaUser

@Transactional(readOnly = true)
@Secured(['ROLE_CURATOR'])
class NewsController extends AbstractBalsaController {
	
	@Secured(['permitAll'])
	def index() {
		params.sort = "dateCreated"
		params.order = "desc"
		def newsList = News.list(params)
		[newsInstanceList: newsList, newsInstanceCount: newsList.size()]
	}
	
	@Secured(['permitAll'])
	def latest() {
		render template: "latest", model: [newsInstance: News.listOrderByDateCreated(max: 1, order: "desc").get(0)]
	}

	def create() {
		[newsInstance: new News(params)]
	}

	@Transactional
	def save(News newsInstance) {		
		if (notFound(newsInstance)) return
		newsInstance.dateCreated = new Date()
		newsInstance.createdBy = userService.current
		hasErrors(newsInstance, 'create', 'newsInstance')
		if (hasErrors(newsInstance, 'create', 'newsInstance')) return

		newsInstance.save flush:true
		
		redirect action: 'index'
	}

	def edit(News newsInstance) {
		if (notFound(newsInstance)) return
		
		[newsInstance: newsInstance]
	}

	@Transactional
	def update(News newsInstance) {
		if (notFound(newsInstance)) return
		newsInstance.createdBy = userService.current
		if (hasErrors(newsInstance, 'edit', 'newsInstance')) return

		newsInstance.save flush:true
		
		
		redirect action: 'index'
	}

	@Transactional
	def delete(News newsInstance) {
		if (notFound(newsInstance)) return

		newsInstance.delete flush:true

		redirect action: 'index'
	}
}
