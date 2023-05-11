package balsa.authorityControl


import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.AbstractBalsaController
import balsa.Study
import balsa.Version

@Transactional
@Secured("ROLE_CURATOR")
class PublicationController extends AbstractBalsaController {

    @Transactional(readOnly = true)
    def index() { 
		[publications: Publication.list(params)]
	}
	
	@Transactional(readOnly = true)
	def batchAdd() {}
	
	def addBatch() {
		def batch = params.batch
		
		batch.eachLine { line ->
			def (officialName, abbrNames) = line.tokenize(';')
			def alreadyExists = Publication.findByOfficialName(officialName.trim())
			
			if (alreadyExists) {
				alreadyExists.abbrNames = abbrNames.split(',')
				alreadyExists.approved = true
				
				alreadyExists.save flush: true
			}
			else {
				Publication newPublication = new Publication()
				newPublication.officialName = officialName.trim()
				newPublication.abbrNames = abbrNames.split(',')
				newPublication.approved = true
				
				newPublication.save flush: true
			}
		}
		
		redirect action: 'index'
	}
	
	def create(Publication publicationInstance) {
		if (notFound(publicationInstance)) return
		publicationInstance.abbrNames = params.otherNames.split(',')
		if (hasErrors(publicationInstance, 'create', 'publicationInstance')) return
		
		publicationInstance.approved = true
		publicationInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def update(Publication publicationInstance) {
		if (notFound(publicationInstance)) return
		publicationInstance.abbrNames = params.otherNames.split(',')
		if (hasErrors(publicationInstance, 'edit', 'publicationInstance')) return

		publicationInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def approve(Publication publicationInstance) {
		if (notFound(publicationInstance)) return
		if (hasErrors(publicationInstance, 'edit', 'publicationInstance')) return
		
		publicationInstance.approved = true
		publicationInstance.save flush:true
		
		redirect action: 'index'
	}
	
	def delete(Publication publicationInstance) {
		if (notFound(publicationInstance)) return
		
		def newPublication = Publication.get(params.replacement)
		if (newPublication && !newPublication.abbrNames.contains(publicationInstance.officialName)) {
			def nameList = newPublication.abbrNames as List
			nameList.add(publicationInstance.officialName)
			newPublication.abbrNames = nameList as String[]
		}
		
		// altering the objects doesn't seem to work to avoid foreign key problems, so I have to run direct update statements
		Study.executeUpdate("update Study s set s.publication=:newPub where s.publication=:currentPub", [newPub: newPublication, currentPub: publicationInstance])
		Version.executeUpdate("update Version v set v.publication=:newPub where v.publication=:currentPub", [newPub: newPublication, currentPub: publicationInstance])
		
		publicationInstance.delete flush: true
		
		redirect action: 'index'
	}
	
	@Secured("ROLE_SUBMITTER")
	def search()
	{
		def c = Publication.createCriteria()
		def results = c.list {
			projections {
				distinct("officialName")
			}
			or {
				ilike("officialName", '%' + params.searchText.replaceAll(' ', '%') + '%')
				pgArrayILike ('abbrNames', '%' + params.searchText.replaceAll(' ', '%') + '%')
			}
			order("officialName", "asc")
		}
		render results as JSON
	}
}
