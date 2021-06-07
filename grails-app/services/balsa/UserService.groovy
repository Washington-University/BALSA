package balsa

import grails.gorm.transactions.Transactional
import balsa.security.BalsaUser

@Transactional(readOnly = true)
class UserService {
	def springSecurityService
	
	def getCurrent() {
		BalsaUser.findByUsername(springSecurityService.principal.username)
	}
}