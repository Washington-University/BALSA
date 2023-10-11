package balsa

import grails.util.Environment
import groovy.time.TimeCategory
import twitter4j.StatusUpdate
import grails.gorm.transactions.Transactional

class AutoPublishJob {
	def twitter4jService
	
    static triggers = {
		simple repeatInterval: 60000, repeatCount: -1
    }

	@Transactional
    def execute() {
		def now = new Date()
		def approvedVersions = Version.findAllByStatus(Version.Status.APPROVED)
		approvedVersions.each { approvedVersion -> 
			if (approvedVersion.isReference() || (approvedVersion.releaseDate && approvedVersion.releaseDate < now)) {
				Dataset versionDataset = approvedVersion.dataset
				
				// if there is already a public version that matches preprint status, this is considered an update, and the prior version becomes nonpublic
				// a dataset can have one public preprint and one public non-preprint at a time
				Version priorPublic = versionDataset.versions.find({ it.isPublic() && it.preprint == approvedVersion.preprint })
				if (priorPublic) { priorPublic.status = Version.Status.NONPUBLIC }
				
				approvedVersion.publicDate = new Date()
				approvedVersion.status = Version.Status.PUBLIC
				if (!versionDataset.publicDate) { versionDataset.publicDate = new Date() } // for sorting purposes
				
				// only tweet if it's been at least a week since the last time this study went public
				def weekApart = !priorPublic ?: (TimeCategory.minus( approvedVersion.publicDate, priorPublic.publicDate ).days >= 7)
				if (weekApart) {
					// prepare message for tweet
					def newOrUpdated = priorPublic ? 'Updated ' : 'New '
					def preprint = approvedVersion.preprint ? 'preprint ' : ''
					def term = approvedVersion.isReference() ? 'reference' : 'study'
					def title = versionDataset.shortTitle ?: (versionDataset.title.length() < 101 ? versionDataset.title : (versionDataset.title.take(99) + '\u2026'))
					def message = newOrUpdated + preprint + term + ' dataset released on BALSA: ' + title + '\nView it here: https://balsa.wustl.edu/' + term + '/' + versionDataset.id
					
					StatusUpdate status = new StatusUpdate(message)
//					def preview = approvedVersion.safeFocusScene()?.preview
//					if (preview) {
//						status.setMedia(approvedVersion.id + '.' + preview.imageFormat, new ByteArrayInputStream(preview.image))
//					}
					try {
						if (Environment.current == Environment.PRODUCTION) {
							twitter4jService.updateStatus(status)
						}
					}
					catch(Exception e) { // Twitter API may be down or inaccessible
					}
				}
			}
		}
    }
}
