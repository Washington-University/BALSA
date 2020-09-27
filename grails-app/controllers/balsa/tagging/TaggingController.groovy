package balsa.tagging

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.AbstractBalsaController

@Transactional(readOnly = true)
@Secured("ROLE_CURATOR")
class TaggingController extends AbstractBalsaController {
	
	static defaultAction = "categories"

    def categories() {
		params.max = 10
		def categoryInstanceList = TagCategory.list(params)
		[categoryInstanceList: categoryInstanceList, categoryInstanceCount: categoryInstanceList.totalCount]
	}
	
	def category(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		[categoryInstance: categoryInstance]
	}
	
	def createCategory() {
		[categoryInstance: new TagCategory(params)]
	}
	
	@Transactional
	def saveCategory(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		if (hasErrors(categoryInstance, 'createCategory', 'categoryInstance')) return
		
		categoryInstance.save flush:true

		redirect action: 'category', id: categoryInstance.id
	}
	
	@Transactional
	def deleteCategory(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		categoryInstance.delete flush:true

		redirect action: 'categories'
	}
	
	@Transactional
	def updateOptions(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		categoryInstance.options = params.options.split("\r\n")
		categoryInstance.save flush:true
		
		redirect action: 'category', id: categoryInstance.id
	}
	
	@Transactional
	def updateDescription(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		
		categoryInstance.description = params.description
		categoryInstance.save flush:true
		
		redirect action: 'category', id: categoryInstance.id
	}
	
	@Transactional
	def changeType(TagCategory categoryInstance) {
		if (notFound(categoryInstance)) return
		def searchType = params.searchType as TagCategory.SearchType
		categoryInstance.searchType = searchType
		
		render(status: 200)
	}
	
	@Transactional
	def addHandle() {
		def handleInstance = new TagHandle(params)
		if (hasErrors(handleInstance.category, 'category', 'categoryInstance')) return
		
		def categoryId = handleInstance.category.id
		handleInstance.save flush:true
		
		redirect action: 'category', id: categoryId
	}
	
	@Transactional
	def deleteHandle(TagHandle handleInstance) {
		if (notFound(handleInstance)) return
		
		def categoryId = handleInstance.category.id
		handleInstance.delete flush:true
		
		redirect action: 'category', id: categoryId
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
