package org.x2framework.kotlin.config

import net.sf.log4jdbc.Log4jdbcProxyDataSource
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter
import net.sf.log4jdbc.tools.LoggingType
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.TransactionManagementConfigurer
import javax.sql.DataSource

@Configuration
open class PersistenceConfig(): TransactionManagementConfigurer {
	val tempDir:String = System.getProperty("java.io.tmpdir")
	
	override fun annotationDrivenTransactionManager(): PlatformTransactionManager? {
		return transactionManager()
	}
	
	@Bean
    open fun transactionManager():PlatformTransactionManager {
        return DataSourceTransactionManager(proxyDatasource());
    }

	/**
	 * Creates Log4JjdbcProxyDataSource Bean.
	 * This bean is set with @Primay for Mybatis's SqlSessionFactory.
	 * In case when you need multiple 'real' datasources, you need to create SqlSessionFactoryBean manually,
	 * and invoke the SqlSessionFactoryBean's setDataSource method with passing datasource bean argument.
	 * 
	 * @see SqlSessionFactoryBean.setDataSource
	 */
	@Bean
	@Primary
	open fun proxyDatasource(): DataSource{
		val formatter = Log4JdbcCustomFormatter()
		formatter.loggingType = LoggingType.MULTI_LINE
		formatter.sqlPrefix = "--------------- SQL Statement --------------- \n"
		
		val jdbcDataSource = Log4jdbcProxyDataSource(h2Datasource())
		jdbcDataSource.logFormatter = formatter
		
		return jdbcDataSource
	}
	
	/**
	 * Creates H2 In Memory Database
	 * Load schema and data files manually
	 * 
	 * set @Bean(destroyMethod) to empty to prevent InstanceNotFoundException on shutdown
	 * @see https://stackoverflow.com/questions/24947717/failed-to-unregister-datasource-jmx-mbean-while-shutting-down-a-spring-boot-appl
	 */
	@Bean(destroyMethod = "")
	open fun h2Datasource(): DataSource {
		val h2DatabasePath = "classpath:/h2database";
		val schemaFilePath = "$h2DatabasePath/schema-h2.sql";
		var dataFilePath = "$h2DatabasePath/data-h2.sql"
		
		val datasource = BasicDataSource()
		datasource.url = "jdbc:h2:mem:test;INIT=runscript from '$schemaFilePath'\\;runscript from '$dataFilePath'"
		datasource.username = "sa"
		datasource.password = ""
		datasource.driverClassName = "org.h2.Driver"
		
		return datasource 
	}
}