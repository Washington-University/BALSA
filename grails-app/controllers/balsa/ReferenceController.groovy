package balsa

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

import org.apache.commons.lang.ArrayUtils


@Transactional(readOnly = true)
@Secured("ROLE_CURATOR")
class ReferenceController extends AbstractDatasetController {
	
	Class getDatasetType() {
		Reference.class
	}
	
	@Transactional
	def save(Reference referenceInstance) {
		if (notFound(referenceInstance)) return
		
		referenceInstance.addToVersions(new Version())
		
		referenceInstance.save flush:true
		
		if (params.type == 'json') {
			render referenceInstance as JSON
			return
		}

		redirect action: 'edit', id: referenceInstance.id
	}

	@Transactional
	def create(Reference referenceInstance) {
		if (notFound(referenceInstance)) return
		
		referenceInstance.addToVersions(new Version())
		
		referenceInstance.save flush:true
		
		if (params.type == 'json') {
			render referenceInstance as JSON
			return
		}

		redirect action: 'edit', id: referenceInstance.id
	}

	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def update() {
		Reference referenceInstance = Reference.get(params.id)
		if (notFound(referenceInstance)) return
		
		Version workingVersion = referenceInstance.workingVersion()
		
		// Dataset
		referenceInstance.title = params.title
		referenceInstance.shortTitle = params.shortTitle
		referenceInstance.extract = params.extract
		referenceInstance.customTermsTitle = params.customTermsTitle
		referenceInstance.customTermsContent = params.customTermsContent
		
		referenceInstance.species.collect().each { referenceInstance.removeFromSpecies(it) }
		List speciesList = params.species + [] ?: [params.species]
		speciesList.each { speciesId ->
			referenceInstance.addToSpecies(Species.get(speciesId))
		}
		
		referenceInstance.accessAgreements.collect().each { referenceInstance.removeFromAccessAgreements(it) }
		List accessAgreementsList = params.accessAgreements + [] ?: [params.accessAgreements]
		accessAgreementsList.each { agreementId ->
			referenceInstance.addToAccessAgreements(Terms.get(agreementId))
		}
		
		workingVersion.updatedDate = new Date()
		workingVersion.description = params.description
		workingVersion.comments = params.comments
		workingVersion.focusScene = params.focusScene
		workingVersion.sceneFileOrder = ArrayUtils.removeElement(params.sceneFileOrder.split(","), "")
		
		render 'Save successful.'
	}

	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def delete(Reference referenceInstance) {
		if (notFound(referenceInstance)) return

		referenceInstance.delete flush:true

		render view: '/index'
	}
	
//	protected def emailOwners(Dataset datasetInstance, String message) {
//		// reference datasets don't have owners
//	}
}
