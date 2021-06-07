package balsa

import javax.annotation.PostConstruct
import twitter4j.StatusUpdate
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class Twitter4jService {
	def grailsApplication
	Twitter twitter
	
	@PostConstruct
	def init() {
		ConfigurationBuilder cb = new ConfigurationBuilder()
		cb.OAuthConsumerKey = grailsApplication.config.getProperty('twitter4j.OAuthConsumerKey')
		cb.OAuthConsumerSecret = grailsApplication.config.getProperty('twitter4j.OAuthConsumerSecret')
		cb.OAuthAccessToken = grailsApplication.config.getProperty('twitter4j.OAuthAccessToken')
		cb.OAuthAccessTokenSecret = grailsApplication.config.getProperty('twitter4j.OAuthAccessTokenSecret')
		
		twitter = new TwitterFactory(cb.build()).getInstance()
	}
	
	def updateStatus(StatusUpdate status) {
		twitter.updateStatus(status)
	}
	
	def updateStatus(String status) {
		twitter.updateStatus(status)
	}
}
