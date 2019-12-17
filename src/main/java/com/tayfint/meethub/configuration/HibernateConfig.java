package com.tayfint.meethub.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.tayfint.meethub.configuration")
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories("com.tayfint.meethub.dao")
public class HibernateConfig {
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.tayfint.meethub.model"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return entityManagerFactoryBean;
	}
	
	@Bean
	public DataSource dataSource(){
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSourceConfig.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
		dataSourceConfig.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSourceConfig.setPassword(environment.getRequiredProperty("jdbc.password"));
		dataSourceConfig.setConnectionTimeout(Long.parseLong(environment.getRequiredProperty("jdbc.connection-timeout")));
		dataSourceConfig.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("jdbc.max-pool-size")));
		return new HikariDataSource(dataSourceConfig);		
	}
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.connection.SetBigStringTryClob", environment.getRequiredProperty("hibernate.connection.SetBigStringTryClob"));
		properties.put("javax.persistence.validation.mode", environment.getRequiredProperty("javax.persistence.validation.mode"));
		return properties;
	}
	
	@Bean
	 public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
