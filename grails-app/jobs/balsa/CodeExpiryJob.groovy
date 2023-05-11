package balsa

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.ui.RegistrationCode

class CodeExpiryJob {

    static triggers = {
        cron cronExpression: '0 0 0 * * ?'
    }

    @Transactional
    def execute() {
        RegistrationCode.executeUpdate('delete RegistrationCode r where r.dateCreated < current_date - 7')
    }
}
