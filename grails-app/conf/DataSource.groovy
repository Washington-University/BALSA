dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
	dialect = "net.kaleidos.hibernate.PostgresqlExtensionsDialect"
	postgresql.extensions.sequence_per_table = false
	dbCreate = "update"
	//logSql = true
	
	properties {
		// See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
		jmxEnabled = true
		initialSize = 5
		maxActive = 50
		minIdle = 5
		maxIdle = 25
		maxWait = 10000
		maxAge = 10 * 60000
		timeBetweenEvictionRunsMillis = 5000
		minEvictableIdleTimeMillis = 60000
		validationQuery = "SELECT 1"
		validationQueryTimeout = 3
		validationInterval = 15000
		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false
		jdbcInterceptors = "ConnectionState"
		defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
	 }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}