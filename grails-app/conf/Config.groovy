// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml'],
	png: 		   'image/png', 
	bmp:		   'image/bmp', 
	gif:           'image/gif', 
	jpg:           'image/jpeg', 
	jpeg:          'image/jpeg'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}

grails.app.context = "/"
grails.views.javascript.library="jquery"

grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false


environments {
    development {
        grails.logging.jul.usebridge = true
		grails.serverURL = ""
		// absolute path to staging location, ending with a slash
		staging.location=""
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = ""
		// absolute path to staging location, ending with a slash
		staging.location=""
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'balsa.security.BalsaUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'balsa.security.UserRole'
grails.plugin.springsecurity.authority.className = 'balsa.security.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/assets/**':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll'],
	'/plugins/**':					  ['permitAll'],
	'/j_spring_security_logout':      ['permitAll']
]
grails.plugin.springsecurity.roleHierarchy = '''
	ROLE_ADMIN > ROLE_CURATOR
	ROLE_CURATOR > ROLE_SUBMITTER
	ROLE_SUBMITTER > ROLE_USER
'''
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.auth.ajaxLoginFormUrl = '/login/_auth'

// required for spring security ldap
grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'balsa.PersistentLogin'

// settings for ldap
grails.plugin.springsecurity.ldap.context.managerDn = ''
grails.plugin.springsecurity.ldap.context.managerPassword = ''
grails.plugin.springsecurity.ldap.context.server = ''
grails.plugin.springsecurity.ldap.search.filter = "(CN={0})"
grails.plugin.springsecurity.ldap.authorities.groupSearchFilter = 'member={0}'
grails.plugin.springsecurity.ldap.authorities.retrieveDatabaseRoles = false
grails.plugin.springsecurity.ldap.authorities.retrieveGroupRoles = true
grails.plugin.springsecurity.ldap.authorities.ignorePartialResultException = true
grails.plugin.springsecurity.ldap.useRememberMe = true
grails.plugin.springsecurity.ldap.rememberMe.usernameMapper.usernameAttribute = 'CN'
grails.plugin.springsecurity.ldap.rememberMe.detailsManager.groupMemberAttributeName = 'member'
grails.plugin.springsecurity.ldap.mapper.passwordAttributeName = 'unicodePwd'
grails.plugin.springsecurity.ldap.authenticator.dnPatterns = []

// these must be identical
grails.plugin.springsecurity.ldap.authorities.groupSearchBase = ''
grails.plugin.springsecurity.ldap.rememberMe.detailsManager.groupSearchBase = ''

// same with these
grails.plugin.springsecurity.ldap.search.base = ''
grails.plugin.springsecurity.ldap.rememberMe.usernameMapper.userDnBase = ''

// email settings for spring security UI
grails.plugin.springsecurity.ui.register.emailBody = '<p>You have successfully registered. Please take a moment to add detail to your <a href="http://balsa.wustl.edu/profile/edit/$user.profile.id">profile</a>.</p><p>Have questions about BALSA? Go <a href="http://balsa.wustl.edu/about">here</a>.</p>'
grails.plugin.springsecurity.ui.register.emailFrom = 'noreply@balsa.wustl.edu'
grails.plugin.springsecurity.ui.register.emailSubject = 'Welcome to BALSA'
grails.plugin.springsecurity.ui.forgotPassword.emailBody = '<p>Follow <a href=$url>this link</a> to reset your password.</p>'
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = 'noreply@balsa.wustl.edu'
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'BALSA Password Reset'
grails.plugin.springsecurity.ui.forgotUsername.emailBody = 'Your username is'
grails.plugin.springsecurity.ui.forgotUsername.emailFrom = 'noreply@balsa.wustl.edu'
grails.plugin.springsecurity.ui.forgotUsername.emailSubject = 'BALSA Username'
grails.plugin.springsecurity.ui.register.defaultRoleNames = ['ROLE_USER']

grails.plugins.twitterbootstrap.fixtaglib = truegrails.plugin.springsecurity.rememberMe.persistent = true

HCP.openAccess.title = 'WU-Minn HCP Consortium Open Access Data Use Terms'
HCP.openAccess.contents = "I request access to data collected by the Washington University - University of Minnesota Consortium of the Human Connectome Project (WU-Minn HCP), and I agree to the following:\n\n1. I will not attempt to establish the identity of or attempt to contact any of the included human subjects.\n\n2. I understand that under no circumstances will the code that would link these data to Protected Health Information be given to me, nor will any additional information about individual human subjects be released to me under these Open Access Data Use Terms.\n\n3. I will comply with all relevant rules and regulations imposed by my institution. This may mean that I need my research to be approved or declared exempt by a committee that oversees research on human subjects, e.g. my IRB or Ethics Committee. The released HCP data are not considered de-identified, insofar as certain combinations of HCP Restricted Data (available through a separate process) might allow identification of individuals.  Different committees operate under different national, state and local laws and may interpret regulations differently, so it is important to ask about this. If needed and upon request, the HCP will provide a certificate stating that you have accepted the HCP Open Access Data Use Terms.\n\n4. I may redistribute original WU-Minn HCP Open Access data and any derived data as long as the data are redistributed under these same Data Use Terms.\n\n5. I will acknowledge the use of WU-Minn HCP data and data derived from WU-Minn HCP data when publicly presenting any results or algorithms that benefitted from their use.\n\n\t1. Papers, book chapters, books, posters, oral presentations, and all other printed and digital presentations of results derived from HCP data should contain the following wording in the acknowledgments section: 'Data were provided [in part] by the Human Connectome Project, WU-Minn Consortium (Principal Investigators: David Van Essen and Kamil Ugurbil; 1U54MH091657) funded by the 16 NIH Institutes and Centers that support the NIH Blueprint for Neuroscience Research; and by the McDonnell Center for Systems Neuroscience at Washington University.'\n\n\t2. Authors of publications or presentations using WU-Minn HCP data should cite relevant publications describing the methods used by the HCP to acquire and process the data. The specific publications that are appropriate to cite in any given study will depend on what HCP data were used and for what purposes. An annotated and appropriately up-to-date list of publications that may warrant consideration is available at http://www.humanconnectome.org/about/acknowledgehcp.html\n\n\t3. The WU-Minn HCP Consortium as a whole should not be included as an author of publications or presentations if this authorship would be based solely on the use of WU-Minn HCP data.\n\n6. Failure to abide by these guidelines will result in termination of my privileges to access WU-Minn HCP data."
HCP.restrictedAccess.title = 'Restricted Data Use Terms'
HCP.restrictedAccess.contents = 'Please fill out and submit the form found at the link below.'
HCP.restrictedAccess.link = 'http://humanconnectome.org/data/data-use-terms/DataUseTerms_HCP_RestrictedAccess_22Jul2014.pdf'
BALSA.submissionTerms.title = 'Terms and Conditions for Uploading Data to the Washington University (WU) BALSA Database'
BALSA.submissionTerms.contents = "Terms and Conditions for Uploading Data to the Washington University (WU) BALSA Database\n\nBy uploading data to the BALSA Database (Database) and using this website, you agree to the following BALSA Data Submission Terms and Conditions: \n\n1) You represent that you have the full power and authority to accept these Terms and Conditions.\n\n2) You represent that uploading the data into the Database, and the storage, use and distribution of the data by WU, will not infringe or invade any existing rights of third parties. \n\n3) You will upload into the Database only data that you have the legal right to upload. \n\n4) You have obtained all approvals, including any regulatory or ethics approvals, necessary to upload the data into the Database and the data’s storage, use and distribution by WU.\n\n5) For data associated with individual human subjects, you will upload only:\n\n    i) anonymized data, meaning that the data cannot in any way be linked to an individual person, or \n\n    ii) coded, de-identified data, meaning that a) no HIPAA elements of Protected Health Information (PHI), including visit date, are included in the data; and b) the subject code has not been created using elements of PHI: and c) the data cannot be linked to an individual person without access to the coding table and/or without disproportional efforts. \n\n6) If required by an Institutional Review Board, your institution’s policies or other authority, you have uploaded the data with the appropriate data access terms or a data access approval policy.\n\n7) Your use of the Database does not cause WU to be your Business Associate as that term is defined by HIPAA.\n\n8) You will not share your BALSA account or password with anyone.\n\n9) You will establish only a single BALSA account.\n\n10) You will cooperate with the Database curators to address problems with erroneous or inappropriate submitted data or with impact of the data on operation of the Database. \n\n11) You will not interfere with the operation of the Database.\n\n12) WU retains the sole discretion to deny, disable or terminate your BALSA account and your access to all or any portions of the BALSA website.\n\n13) WU retains the sole discretion to reject or remove all data or any part of a data set from the Database.  \n\n14) WU owns the Database and retains the right to cease supporting the Database or terminate the Database at any time without any obligation to make or store copies of data. \n\n15) You grant WU and its designees a perpetual, irrevocable, non-exclusive, royalty free license to store, reproduce and distribute your data and to exercise any and all rights to manage the data in the Database.  All right, title and interest in any inventions, discoveries, improvements or other intellectual property related to the development and operation of the Database are vested in WU.\n\n16) You will indemnify, defend and hold WU, its employees and agents, harmless against all claims, demands, costs and expense, including attorney fees incurred for any claims related to your use of the Database and the data uploaded by you into the Database including, without limitation, claims related to intellectual property rights, patient privacy or any other legal and regulatory action. \n\n17) WU is not responsible for the integrity or security of data uploaded into the database, and makes no representations regarding the data, the maintenance of the data or operation of the Database.\n\n18) WU makes no warranties or representations of any kind, express or implied, concerning any services, information, activities, management or intellectual property rights related to the data, the Database or the BALSA website, including whether the Database or the BALSA website is free of any viruses, harmful components or adverse attributes. Your use of the Database and the BALSA website are at your own risk. \n\n19) IN NO EVENT WILL WU BE LIABLE FOR DIRECT, INDIRECT, INCIDENTAL, CONSEQUENTIAL, PUNITIVE, OR SPECIAL DAMAGES OF ANY KIND ARISING OUT OF THESE TERMS AND CONDITIONS, THE UPLOADING, STORAGE, MAINTENANCE OR DISTRIBUTION OF DATA IN THE DATABASE OR THE OPERATION OF THE DATABASE REGARDLESS OF THE CLAIM OR THEORY OF LIABILITY AND REGARDLESS OF WHETHER WU KNEW OR SHOULD HAVE KNOWN ABOUT THE POSSIBILITY OF SUCH DAMAGES.  \n\n20)  WU retains the right to amend these Terms and Conditions at any time. WU will  make a good-faith effort to notify data owners of such changes via email. Use of the Database indicates acceptance of the Terms and Conditions in effect at the time of use.  \n\n21) These Terms and Conditions will be governed by the laws of the State of Missouri."
balsa.defaultAdmins = []
balsa.curatorContacts = []
balsa.techContacts = []

grails.plugins.remotepagination.enableBootstrap=true

grails.mail.host = "localhost"

grails.assets.minifyJs = false
grails.assets.minifyCss = false

// Added by the Recaptcha plugin:
recaptcha {
    // These keys are generated by the ReCaptcha service
    publicKey = ""
    privateKey = ""

    // Include the noscript tags in the generated captcha
    includeNoScript = true

    // Include the required script tag with the generated captcha
    includeScript = true

    // Set to false to disable the display of captcha
    enabled = true
}

twitter4j {
	enableTwitter4jController = false  // To avoid intruders to use controller all together.
	'default' {
		debugEnabled           = false
		OAuthConsumerKey       = ''
		OAuthConsumerSecret    = ''
		OAuthAccessToken       = ''
		OAuthAccessTokenSecret = ''
	}
}

grails.config.locations = [ "file:/data/balsa/balsa-config.groovy", "file:E:/data/balsa/balsa-config.groovy" ]