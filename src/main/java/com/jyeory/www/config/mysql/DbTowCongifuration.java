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
public class DbTowCongifuration {
	@Value("${spring.datasource.hikari.ds-two.url}")
	private String jdbcUrl;
	
	@Value("${spring.datasource.hikari.ds-two.username}")
	private String username;
	
	@Value("${spring.datasource.hikari.ds-two.password}")
	private String password;
	
	@Value("${spring.datasource.hikari.ds-two.driver-class-name}")
	private String driverClassName;
	
	@Bean(name="dsTwo")
	@Qualifier("dsTwo")
    public DataSource dsTwo() {
		HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
	
	private static final String PERSISTANCE_UNIT_NAME = "dsEntityManager";
	
	@Bean(name = "dsTwoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dsTwoEntityManagerFactory () {
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
        emf.setDataSource(dsTwo());
        emf.setPackagesToScan("com.jyeory.www.data.entity");
        emf.setPersistenceUnitName(PERSISTANCE_UNIT_NAME);
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaPropertyMap(properties);

        return emf;
    }
	
	@Bean(name = "dsTwoTxManager")
	PlatformTransactionManager transactionManager(@Qualifier(value = "dsTwoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
	}

	@Configuration
	@EnableJpaRepositories(
			basePackages="com.jyeory.www.data.repository",
			entityManagerFactoryRef = "dsTwoEntityManagerFactory",
			transactionManagerRef = "dsTwoTxManager")
	static class dsTwoJpaRepositoriesConfig {
	}
}
