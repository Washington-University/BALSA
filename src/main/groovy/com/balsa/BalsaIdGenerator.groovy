package balsa

import grails.util.Holders
import groovy.sql.Sql

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator

import com.apploi.Hashids

class BalsaIdGenerator implements IdentifierGenerator {
	static def dataSource = Holders.grailsApplication.mainContext.getBean 'dataSource'
	static def hashids = new Hashids('afSWJVh4fPdebzb6EWOzzC0BkXKG1W', 4, 'bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890')

	@Override
	public Serializable generate(SharedSessionContractImplementor ssci, Object object) throws HibernateException {
		def sql = new Sql(dataSource)
		def rows = sql.rows("select nextval('hibernate_sequence');")
		def nextVal = rows[0]['nextval']
		return hashids.encode(nextVal)
	}

}
