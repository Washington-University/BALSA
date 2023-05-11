package balsa

import grails.plugin.springsecurity.SpringSecurityUtils
import net.kaleidos.hibernate.usertype.ArrayType

import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.security.BalsaUser
import grails.databinding.BindingFormat

class Study extends Dataset {
	String viewToken
	
	static hasMany = [owners: BalsaUser, viewers: BalsaUser, institutions:Institution]
	static belongsTo = BalsaUser
	
    static constraints = {
		viewToken nullable: true, blank: true
    }
	
	boolean canEdit() {
		( owners?.contains(userService.current) && SpringSecurityUtils.ifAnyGranted('ROLE_SUBMITTER') ) || SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN, ROLE_CURATOR')
	}
	
	boolean canView() {
		owners?.contains(userService.current) || viewers?.contains(userService.current) || SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')
	}
}
