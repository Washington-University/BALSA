package balsa

import balsa.file.FileMetadata
import balsa.scene.Scene
import grails.async.Promise
import grails.async.Promises
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.gorm.transactions.Transactional
import balsa.scene.SceneLine
import groovy.sql.Sql
import org.hibernate.SessionFactory
import groovy.time.TimeCategory
import java.sql.Connection

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
		render '2.1'
	}

	def stat() {
		def dataset = Study.get('RVVG')
		def inDataset = dataset ? " where dataset_id = '" + dataset.id + "' and date >= '" + dataset.publicDate.format('yyyy-MM-dd') + "'" : ""
		def sql = new Sql(sessionFactory.currentSession.connection())
		def results = sql.rows("select to_char(date_trunc('month', date), '''YY-MM''') as month, count(id) as count from download" + inDataset + " group by date_trunc('month', date) order by date_trunc('month', date) asc limit 12")
		render results as JSON
	}

	def fileCount() {
		//def query = "select file.id, count(d.id) as dcount from FileMetadata file left join file.downloads d where file.dataset.id='RVVG' and size(d.scenes) = 0 group by file order by count(d.id) desc"
		//def a = FileMetadata.executeQuery(query, [max: 20])

		def dataset = null// Study.get('RVVG')
		def max = 10
		def sql = new Sql(sessionFactory.currentSession.connection())
		def fileIds = sql.rows("select id from file_metadata" + (dataset ? " where dataset_id = '" + dataset.id + "'" : ""))
		def sceneIds = sql.rows("select id from scene where scene_file_id in (" + formatList(fileIds) + ")")
		def sceneDownloadIds = sql.rows("select download_id from scene_download where scene_downloads_id in (" + formatList(sceneIds) + ")")
		def nonSceneDownloadIds = sql.rows("select id from download where" + (dataset ? " dataset_id = '" + dataset.id + "' and" : "") + " id not in (" + formatList(sceneDownloadIds) + ")")
		def results = sql.rows("select f.id, f.filename, fd.dc from (select file_metadata_downloads_id as id, count(download_id) as dc from file_metadata_download where download_id in (" + formatList(nonSceneDownloadIds) + ") group by file_metadata_downloads_id) fd join file_metadata f on f.id = fd.id order by fd.dc desc limit " + max)

		render results as JSON
	}

	def command() {
		def stagingArea = grailsApplication.config.staging.location
		def bin = grailsApplication.config.staging.bin
		FileMetadata file = FileMetadata.get(params.id)
		Connection conn = sessionFactory.getCurrentSession().connection().unwrap(org.postgresql.PGConnection)
		def lom = conn.getLargeObjectAPI()
		def data = FileData.get(file.fileDataId)?.data
		InputStream is = lom.open(data).getInputStream()
		def stagedFile = new File(stagingArea + 'fileInfo/' + file.filename)
		def os = stagedFile.newOutputStream()
		os << is
		def command = stagingArea + bin + 'wb_command -file-information ' + stagedFile.getAbsolutePath() + ' -no-map-info'
		def sout = new StringBuffer(), serr = new StringBuffer()
		def proc = command.execute()
		proc.waitForProcessOutput(sout, serr)

		//def results = command.execute().text
		render command + '<br>' + sout + '<br>' +serr
	}

	def formatList(map) {
		def flatList = []
		for (item in map) {
			flatList.addAll(item.values())
		}
		def formattedList = "'" + flatList.join("','") + "'"
		return formattedList
	}

	@Transactional
	def fileInfo() {
		def stagingArea = grailsApplication.config.staging.location
		def bin = grailsApplication.config.staging.bin
		FileMetadata file = FileMetadata.get(params.id)
		Connection conn = sessionFactory.getCurrentSession().connection().unwrap(org.postgresql.PGConnection)
		def lom = conn.getLargeObjectAPI()
		def data = FileData.get(file.fileDataId)?.data
		InputStream is = lom.open(data).getInputStream()
		def stagedFile = new File(stagingArea + 'fileInfo/' + file.filename)
		def os = stagedFile.newOutputStream()
		os << is
		def command = stagingArea + bin + 'wb_command -file-information ' + stagedFile.getAbsolutePath() + ' -no-map-info'
		def lines = command.execute().text.split('\n')
		def fileInfo = [:]
		def holderStack = [fileInfo]
		def indentStack = [-1]
		for (String line in lines) {
			if (line.contains(':')) {
				def indent = line.length() - line.stripIndent().length()
				while (indent <= indentStack.first()) {
					indentStack.pop()
					holderStack.pop()
				}
				def parts = line.split(':')
				def name = parts[0].trim()
				def obj = [value:parts[1].trim()]

				holderStack.first()[name] = obj
				indentStack.push(indent)
				holderStack.push(obj)
			}
			else {
				holderStack.first().value += ', ' + line.trim()
			}
		}
		file.fileInfo = fileInfo
		stagedFile.delete()
		render fileInfo as JSON
	}

	def massEmail() {
		def max = params.maximum?.toInteger() ?: 10000
		def offset = params.offset?.toInteger() ?: 0
		def c = Profile.createCriteria()
		def emailAddresses = c.list() {
			isNotNull("emailAddress")
			projections {
				distinct("emailAddress")
			}
			firstResult(offset)
			maxResults(max)
			order("emailAddress", "asc")
		}

		for (emailAddress in emailAddresses) {
			mailService.sendMail {
				to  emailAddress
				from 'noreply@brainvis.wustl.edu'
				subject 'Password Reset Request for BALSA'
				html '<p>You are receiving this email because our records indicate that you have an account on the BALSA neuroimaging database. Effective today, BALSA has transitioned to a new authentication system backend for handling logins. Due to the difference in encryption, we cannot simply transfer your existing password, so we need you to reset it. We apologize for the inconvenience.</p>' +
					 '<p>We request that you address this proactively by going to balsa.wustl.edu, clicking "login", then "forgotten password", and typing in your username. You will immediately receive an email with a password reset link. You can reset to the same password you were already using or take this opportunity to change to something new.</p>' +
					 '<p>If you don’t reset your password, the next time you attempt to login directly to BALSA, you will receive the same instructions for going through the password reset process before you can login. If you instead attempt to login via Connectome Workbench (in order to upload a scene file), you will simply get a “login failed” message without any further explanation. If that occurs, we hope you will recall this email and remember to follow the steps in the preceding paragraph.</p>' +
					 '<p>The BALSA Team</p>'
			}
		}
		render emailAddresses
	}

	@Secured(['ROLE_USER'])
	def user() {
		render userService.current
	}
	
	def configValue() {
		render grailsApplication.config.recaptcha.secret
	}
	
	def recaptcha() {
		[siteKey: recaptchaService.siteKey]
	}
	
	def verify() {
		render recaptchaService.verifyAnswer(session, params)
	}
	
	def scene() {
		def id = 'XPNr'
		def scene = SceneLine.get(id)
		render scene
	}
	
	def idTest() {
		render BalsaIdGenerator.hashids.encode(params.id.toInteger())
	}
	
	def urldecode() {
		def initial = request.getHeader('X-File-Name')
		def decoded = URLDecoder.decode(request.getHeader('X-File-Name') ?: 'temp.zip', 'UTF-8') as String
		render initial + ' : ' + decoded
	}
	
	def progress() {
		String result = ''
		handler.statuses.each { k, v -> result += "${k}: ${v.bytesHashed/v.size}<br>" }
	}
	
	def downloadStats() {
		def dataset = Dataset.get('R7q2')
		render statsService.getDownloadStats(dataset)
	}
	
	def downloadInfo(Download downloadInstance) {
		render downloadInstance as JSON
	}
	
	def booleanlist() {
		def a = ['thing':true]
		def b = ['thing':false]
		def c = ['thing':true]
		def d = ['thing':true]
		def l = [a,b,c,d]
		
		if (!l.thing.contains(false)) {
			render 'True'
		}
		else {
			render 'False'
		}
	}
	
	def xml() {
		def xmlSlurper = new XmlSlurper()
		// two features need to be set because the pubmed xml has a doctype at the beginning
		xmlSlurper.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
		xmlSlurper.setFeature('http://apache.org/xml/features/nonvalidating/load-external-dtd', false)
		def xml = xmlSlurper.parse('https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&format=xml&id=' + '27437579')
		
		
		def doi = xml.PubmedArticle.PubmedData.ArticleIdList.ArticleId.find({it.@IdType == 'doi'}).text()
		render doi
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
	
	def contains() {
		def version = Version.get('Z4661')
		render version.files.contains(version.files.iterator()[0])
		
	}
}
