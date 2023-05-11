package balsa

import balsa.file.FileMetadata
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.hibernate.SessionFactory
import java.sql.Connection

class FileInfoJob {
    GrailsApplication grailsApplication
    SessionFactory sessionFactory
    def stagingArea
    def bin

    static triggers = {
        cron cronExpression: '0 0 1 * * ? *'
    }

    def execute() {
        stagingArea = grailsApplication.config.staging.location
        bin = grailsApplication.config.staging.bin
        println('triggered\n')
        def testCommand = stagingArea + bin + 'wb_command'
        if (testCommand.execute().text.startsWith('Version')) {
            def initialTime = new Date().getTime()
            def c = FileMetadata.createCriteria()
            def unprocessedFiles = c.list {
                like('class', 'balsa.file._ifti.%')
                isNull('processed')
            }

            for (file in unprocessedFiles) {
                fileInfo(file)
                if (new Date().getTime() - initialTime > 1000 * 60 * 30) break
            }
        }
    }

    @Transactional
    def fileInfo(FileMetadata file) {
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
        file.processed = new Date()
        stagedFile.delete()
    }
}
