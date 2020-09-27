package balsa.authorityControl

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.AbstractBalsaController
import balsa.Study

@Transactional
@Secured("ROLE_CURATOR")
class InstitutionController extends AbstractBalsaController {

	@Transactional(readOnly = true)
    def index() { 
		[institutions: Institution.list(params)]
	}
	
	@Transactional(readOnly = true)
	def batchAdd() {}
	
	def addBatch() {
		def batch = params.batch
		
		batch.eachLine { line ->
			def (canonicalName, names) = line.tokenize(';')
			def alreadyExists = Institution.findByCanonicalName(canonicalName.trim())
			
			if (alreadyExists) {
				alreadyExists.names = names.split(',')
				alreadyExists.approved = true
				
				alreadyExists.save flush: true
			}
			else {
				Institution newInstitution = new Institution()
				newInstitution.canonicalName = canonicalName.trim()
				newInstitution.names = names.split(',')
				newInstitution.approved = true
				
				newInstitution.save flush: true
			}
		}
		
		redirect action: 'index'
	}
	
	def create(Institution institutionInstance) {
		if (notFound(institutionInstance)) return
		institutionInstance.names = params.otherNames.split(',')
		if (hasErrors(institutionInstance, 'create', 'institutionInstance')) return
		
		institutionInstance.approved = true
		institutionInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def update(Institution institutionInstance) {
		if (notFound(institutionInstance)) return
		institutionInstance.names = params.otherNames.split(',')
		if (hasErrors(institutionInstance, 'edit', 'institutionInstance')) return

		institutionInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def approve(Institution institutionInstance) {
		if (notFound(institutionInstance)) return
		if (hasErrors(institutionInstance, 'edit', 'institutionInstance')) return
		
		institutionInstance.approved = true
		institutionInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def delete(Institution institutionInstance) {
		if (notFound(institutionInstance)) return
		
		def newInstitution = Institution.get(params.replacement)
		if (!newInstitution?.names.contains(institutionInstance.canonicalName)) {
			def nameList = newInstitution.names as List
			nameList.add(institutionInstance.canonicalName)
			newInstitution.names = nameList as String[]
		}
		
		// find all studies currently using this institution
		def c = Study.createCriteria()
		def studiesUsingInstitution = c.list() {
			institutions {
				idEq(institutionInstance.id)
			}
		}
		// change them to a replacement
		for (study in studiesUsingInstitution) {
			study.removeFromInstitutions(institutionInstance)
			
			if (params.replacement != 'delete') {
				study.addToInstitutions(newInstitution)
			}
		}
		
		institutionInstance.delete flush:true
		
		redirect action: 'index'
	}
	
	@Secured("ROLE_SUBMITTER")
	def search()
	{
		def c = Institution.createCriteria()
		def results = c.list {
			projections {
				distinct("canonicalName")
			}
			or {
				ilike("canonicalName", '%' + params.searchText.replaceAll(' ', '%') + '%')
				pgArrayILike ('names', '%' + params.searchText.replaceAll(' ', '%') + '%')
			}
			order("canonicalName", "asc")
		}
		render results as JSON
	}
}
