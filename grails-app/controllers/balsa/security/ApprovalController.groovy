package balsa.security

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import balsa.AbstractBalsaController

@Transactional(readOnly = true)
@Secured("@balsaSecurityService.isApprovalOwner(#this)")
class ApprovalController extends AbstractBalsaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def queue(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		[approvalInstance: approvalInstance]
	}
	
	@Secured("ROLE_USER")
	def mine() {
		[approvalInstanceList: springSecurityService.currentUser.ownedApprovals]
	}
	
	@Secured("permitAll")
	def show(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		[approvalInstance: approvalInstance, isOwner: approvalInstance.owners.contains(springSecurityService.currentUser)]
	}

	@Secured("ROLE_USER")
	def create() {
		[approvalInstance: new Approval(params)]
	}

	@Transactional
	@Secured("ROLE_USER")
	def save(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		if (hasErrors(approvalInstance, 'create', 'approvalInstance')) return

		approvalInstance.save flush:true
		
		redirect action: 'show', id: approvalInstance.id
	}

	def edit(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		[approvalInstance: approvalInstance]
	}

	@Transactional
	def update(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		if (hasErrors(approvalInstance, 'edit', 'approvalInstance')) return

		approvalInstance.save flush:true
		
		redirect action: 'show', id: approvalInstance.id
	}

	@Transactional
	def delete(Approval approvalInstance) {
		if (notFound(approvalInstance)) return

		approvalInstance.delete flush:true

		render view: '/index'
	}
	
	@Transactional
	def approve(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		def user = BalsaUser.get(params.userId)
		user.removeFromSoughtApprovals(approvalInstance)
		user.addToGrantedApprovals(approvalInstance)
		
		render (template: "approved", model: [approvalInstance: approvalInstance, it: user])
	}
	
	@Transactional
	def deny(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		def user = BalsaUser.get(params.userId)
		if (notFound(user)) return
		
		user.removeFromSoughtApprovals(approvalInstance)
		
		render(status: 200)
	}
	
	@Transactional
	def revoke(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		def user = BalsaUser.get(params.userId)		
		if (notFound(user)) return
		
		user.removeFromGrantedApprovals(approvalInstance)
		
		render(status: 200)
	}
	
	@Transactional
	@Secured("ROLE_USER")
	def seek(Approval approvalInstance) {
		if (notFound(approvalInstance)) return
		
		springSecurityService.currentUser.addToSoughtApprovals(approvalInstance)
		
		render(status: 200)
	}
}
