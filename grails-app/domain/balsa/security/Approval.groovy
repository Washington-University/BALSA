package balsa.security

class Approval {
	String id
	String title
	String contents
	String link
	
	static hasMany = [owners: BalsaUser, approvals: BalsaUser, approvalSeekers: BalsaUser]
	static mappedBy = [owners: 'ownedApprovals', approvals: 'grantedApprovals']
	static belongsTo = BalsaUser

    static constraints = {
		title size: 5..100
		contents size: 10..5000
		link nullable: true, url: true
    }
	
	static mapping = {
		id generator: "balsa.BalsaIdGenerator"
	}
}
