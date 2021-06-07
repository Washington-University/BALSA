package balsa

import grails.plugin.springsecurity.SpringSecurityUtils
import net.kaleidos.hibernate.usertype.ArrayType

import balsa.authorityControl.Institution
import balsa.authorityControl.Publication
import balsa.security.BalsaUser
import grails.databinding.BindingFormat

class Study extends Dataset {
	@Deprecated
	String preprintDoi
	@Deprecated
	String doi
	@Deprecated
	String pmid
	@Deprecated
	String studyAbstract
	@Deprecated
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date preprintDate
	@Deprecated
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date epubDate
	@Deprecated
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date journalDate
	@Deprecated
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date releaseDate
	@Deprecated
	Publication publication
	@Deprecated
	String[] authors = []
	@Deprecated
	boolean displayPreprint = true
	@Deprecated
	DateRedirect dateRedirect
	@Deprecated
	@BindingFormat('MM/dd/yyyy h:mm a')
	Date submittedDate
	
	static hasMany = [owners: BalsaUser, viewers: BalsaUser, institutions:Institution]
	static belongsTo = BalsaUser
	
    static constraints = {
		pmid nullable: true, matches: "\\A[0-9]+\\z"
		preprintDoi nullable: true, matches: '\\A(10[.][0-9]{4,}(?:[.][0-9]+)*/(?:(?![%"\'&<>#? ])\\S)+)\\z'
		doi nullable: true, matches: '\\A(10[.][0-9]{4,}(?:[.][0-9]+)*/(?:(?![%"\'&<>#? ])\\S)+)\\z'
		studyAbstract nullable: true
		publication nullable: true
		preprintDate nullable: true
		epubDate nullable: true
		journalDate nullable: true
		releaseDate nullable: true
		dateRedirect nullable: true
		submittedDate nullable: true
    }
	static mapping = {
		studyAbstract type: "text"
		authors type:ArrayType, params: [type: String]
	}
	
	boolean canEdit() {
		( owners?.contains(userService.current) && SpringSecurityUtils.ifAnyGranted('ROLE_SUBMITTER') ) || SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN, ROLE_CURATOR')
	}
	
	boolean canView() {
		owners?.contains(userService.current) || viewers?.contains(userService.current) || SpringSecurityUtils.ifAnyGranted('ROLE_CURATOR, ROLE_ADMIN')
	}
	
	@Deprecated
	enum DateRedirect {
		PREPRINT ("Preprint Date"), 
		EPUB ("Epub Date"), 
		JOURNAL ("Journal Date"), 
		CUSTOM ("Custom Date")
		
		final String value
		
		DateRedirect(String value) { this.value = value }
		
		String toString() { value }
		String getKey() { name() } 
	}
	
	@Deprecated
	Date actualReleaseDate() {
		if (dateRedirect == DateRedirect.PREPRINT) {
			return preprintDate
		}
		else if (dateRedirect == DateRedirect.EPUB) {
			return epubDate
		}
		else if (dateRedirect == DateRedirect.JOURNAL) {
			return journalDate
		}
		else if (dateRedirect == DateRedirect.CUSTOM) {
			return releaseDate
		}
		else {
			return null
		}
	}
}
