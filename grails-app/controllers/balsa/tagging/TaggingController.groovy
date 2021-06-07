package balsa.tagging

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.AbstractBalsaController

@Transactional(readOnly = true)
@Secured("ROLE_CURATOR")
class TaggingController extends AbstractBalsaController {
    def index() {
		def categoryInstanceList = TagCategory.list(params)
		[categoryInstanceList: categoryInstanceList, openCategory: params.openCategory]
	}
	
	@Transactional
	def saveCategory(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		categoryInstance.save flush:true

		redirect action: 'index'
	}
	
	@Transactional
	def deleteCategory(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		categoryInstance.delete flush:true

		redirect action: 'index'
	}
	
	@Transactional
	def updateCategory() {
		TagCategory categoryInstance = TagCategory.get(params.id)
		if (notFound(categoryInstance)) return
		
		categoryInstance.description = params.description
		categoryInstance.searchType = params.searchType
		categoryInstance.options = params.options.split("\\r\\n")
		categoryInstance.handles.collect().each { 
			categoryInstance.removeFromHandles(it)
			it.delete(flush: true)
		}
		params.list('handle').each {
			categoryInstance.addToHandles(new TagHandle(it))
		}
		
		categoryInstance.save flush:true
		
		render 'Save successful.'
	}
	
	@Secured(['permitAll'])
	def categoryOptions() {
		def category = TagCategory.findByName(params.category)
		if (category == null) {
			render ""
			return
		}
		render template: category.searchType.template(), model: [category: category]
	}
}
