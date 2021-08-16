package com.example.springmvc.config.application;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.springmvc.config.AppConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Configure Spring Data JPA.
 */
@Configuration
@EnableJpaRepositories(basePackages = AppConstants.PKG_JPA_REPOSITORY)
@EnableTransactionManagement
@RequiredArgsConstructor
public class JpaConfig {

	private final Environment env;

	private final DataSource dataSource;

	/**
	 * Configure EntityManagerFactory and register as bean.
	 * @return LocalContainerEntityManagerFactoryBean object.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(this.dataSource);
		emf.setPackagesToScan(AppConstants.PKG_JPA_ENTITY);
		emf.setJpaVendorAdapter(this.jpaVendorAdapter());
		emf.setJpaProperties(this.hibernateProperties());
		return emf;
	}

	/**
	 * Configure JpaVendorAdapter and register as bean.
	 * @return JpaVendorAdapter object.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(false);
		return vendorAdapter;
	}

	/**
	 * Return Hibernate properties.
	 * Get parameters from application.properties.
	 * @return Properties object.
	 */
	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		props.setProperty("hibernate.connection.handling_mode", env.getProperty("hibernate.connection.handling_mode"));
		props.setProperty("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
		props.setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
		props.setProperty("hibernate.order_inserts", env.getProperty("hibernate.order_inserts"));
		props.setProperty("hibernate.order_updates", env.getProperty("hibernate.order_updates"));
		return props;
	}

	/**
	 * Configure TransactionManager and register as bean.
	 * @param emf EntityManagerFactory object.
	 * @return PlatformTransactionManager object.
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
