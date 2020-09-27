package balsa

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.tagging.TagCategory

@Secured("permitAll")
@Transactional(readOnly = true)
class SearchController extends AbstractBalsaController {
	
	def datasetSearch() {
		def searchResults = searchService.datasetSearch(params)
		
		render(template: "datasetSearchResults", model:[searchResults:searchResults])
	}
	
	def sceneSearch() {
		def searchResults = searchService.sceneSearch(params)
		
		render(template: "sceneSearchResults", model:[searchResults:searchResults])
	}
	
	def searchFilter() {
		render template: 'searchFilter', model: [categories: TagCategory.list()]
	}
}
