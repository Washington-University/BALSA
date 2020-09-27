package balsa

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.transaction.Transactional

import java.util.zip.ZipFile

import balsa.file.Documentation
import balsa.file.FileMetadata
import balsa.file.SceneFile

import com.apploi.Hashids

@Secured(['ROLE_ADMIN'])
@Transactional
class TestController {
	def twitter4jService
	def fileService
	def springSecurityService
	def statsService
	def grailsApplication
	MailStrategy uiMailStrategy
	
	static UploadHandler handler
	
    def index() { }
	
	def idTest() {
		render BalsaIdGenerator.hashids.encode(params.id.toInteger())
	}
	
	def urldecode() {
		def initial = request.getHeader('X-File-Name')
		def decoded = URLDecoder.decode(request.getHeader('X-File-Name') ?: 'temp.zip', 'UTF-8') as String
		render initial + ' : ' + decoded
	}
	
	def handle() {
		handler = new UploadHandler(new ZipFile("C:\\Users\\John Smith\\Downloads\\Glasser_et_al_2016_HCP_MMP1.0_RVVG.zip"))
		handler.process()
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
	
	def ratio() {
		def uncompressed = 0
		def compressed = 0
		for (file in FileMetadata.list()) {
			uncompressed += file.filesize
			compressed += file.zipsize
		}
		render uncompressed/compressed
	}
	
	def pmrequest() {
		def xmlSlurper = new XmlSlurper()
		xmlSlurper.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
		xmlSlurper.setFeature('http://apache.org/xml/features/nonvalidating/load-external-dtd', false)
		def xml = xmlSlurper.parse('https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&format=xml&id=' + '2743757900')
		
		render xml.PubmedArticle.isEmpty()
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
	
	def testHashIds() {
		def hashids = new Hashids('afSWJVh4fPdebzb6EWOzzC0BkXKG1W', 4, 'bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890')
		
		boolean collides = false
		def sofar = new HashSet()
		
		1.upto(Integer.valueOf(params.num), {
			def hash = hashids.encode(it)
			if (sofar.contains(hash)) {
				collides = true
			}
			else {
				sofar.add(hash)
			}
		})
		
		
		render collides
	}
	
	def tweetTest() {
		twitter4jService.updateStatus('Test post.')
	}
	
	def environment() {
		render grails.util.Environment.current.name
	}
}
