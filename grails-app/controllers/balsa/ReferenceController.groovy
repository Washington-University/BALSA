package balsa

import balsa.file.Documentation
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
		List speciesList = ensureList(params.species)
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
		List visibleDocList = ensureList(params.visibleDocs)
		for (Documentation doc in workingVersion.documentation()) {
			if (visibleDocList.contains(doc.id)) {
				doc.visible = true
			}
			else {
				doc.visible = false
			}
		}

		render 'Save successful.'
	}

	@Transactional
	@Secured("@balsaSecurityService.canEdit(#this, 'dataset')")
	def delete(Reference referenceInstance) {
		if (notFound(referenceInstance)) return

		referenceInstance.delete flush:true

		render view: '/index'
	}

	List ensureList(data) {
		if(!data) return []
		if(data.getClass().isArray()) return data
		[data]
	}
//	protected def emailOwners(Dataset datasetInstance, String message) {
//		// reference datasets don't have owners
//	}
}
