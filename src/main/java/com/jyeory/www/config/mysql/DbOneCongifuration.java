package com.jyeory.www.config.mysql;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
public class DbOneCongifuration {
	
	@Value("${spring.datasource.hikari.ds-one.url}")
	private String jdbcUrl;
	
	@Value("${spring.datasource.hikari.ds-one.username}")
	private String username;
	
	@Value("${spring.datasource.hikari.ds-one.password}")
	private String password;
	
	@Value("${spring.datasource.hikari.ds-one.driver-class-name}")
	private String driverClassName;
	
	@Bean(name="dsOne")
	@Qualifier("dsOne")
    public DataSource dsOne() {
		HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
	
	private static final String PERSISTANCE_UNIT_NAME = "dsEntityManager";
	
	@Bean(name = "dsOneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dsOneEntityManagerFactory () {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        vendorAdapter.setDatabase(Database.MYSQL);

        JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setGenerateDdl(false);
        jpaProperties.setShowSql(true);
        
        HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto("none");
        
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties()
                , new HibernateSettings());

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dsOne());
        emf.setPackagesToScan("com.jyeory.www.user.entity");
        emf.setPersistenceUnitName(PERSISTANCE_UNIT_NAME);
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaPropertyMap(properties);

        return emf;
    }
	
	@Bean(name = "dsOneTxManager")
	PlatformTransactionManager transactionManager(@Qualifier(value = "dsOneEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
	}

	@Configuration
	@EnableJpaRepositories(
			basePackages="com.jyeory.www.user.repository",
			entityManagerFactoryRef = "dsOneEntityManagerFactory",
			transactionManagerRef = "dsOneTxManager")
	static class dsOneJpaRepositoriesConfig {
	}
}
