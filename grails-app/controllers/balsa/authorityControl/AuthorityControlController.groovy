package balsa.authorityControl

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import balsa.AbstractBalsaController;
import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.tagging.TagCategory

@Transactional(readOnly = true)
@Secured("ROLE_CURATOR")
class AuthorityControlController extends AbstractBalsaController {
	def institutions() {
		redirect controller: 'institutions', action: 'index'
	}
	
	def publications() {
		redirect controller: 'publications', action: 'index'
	}
}
