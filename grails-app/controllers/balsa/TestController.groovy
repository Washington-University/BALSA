package balsa

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.gorm.transactions.Transactional
import org.hibernate.SessionFactory
import balsa.file.nifti.Nifti

@Secured(['ROLE_ADMIN'])
@Transactional
class TestController extends AbstractBalsaController {
	def twitter4jService
	MailStrategy uiMailStrategy
	SessionFactory sessionFactory
	
	static UploadHandler handler

    def index() { }

	@Secured(['permitAll'])
	def version() {
		render '2.2.3'
	}

	def count() {
		def volumeFiles = (Nifti.createCriteria().list() {isNull('ciftiVersion')})
		render volumeFiles.size()
	}
	
	def idTest() {
		render BalsaIdGenerator.hashids.encode(params.id.toInteger())
	}
	
	def error() {
		render request.XML
	}
	
	def environment() {
		render grails.util.Environment.current.name
	}
	
	def notFound() {
		render(status: 404)
	}
}
