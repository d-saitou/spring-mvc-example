package com.example.spring.config.application;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Configure the data source.
 */
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

	private final Environment env;

	/**
	 * Configure DataSource and register as bean.
	 * Get parameters from application.properties.
	 * @return DataSource object.
	 */
	@Bean(destroyMethod = "close")
	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		ds.setJdbcUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		ds.setMaximumPoolSize(Integer.parseInt(env.getProperty("hikariCP.maximumPoolSize")));
		ds.setMinimumIdle(Integer.parseInt(env.getProperty("hikariCP.minimumIdle")));
		ds.setIdleTimeout(Long.parseLong(env.getProperty("hikariCP.idleTimeout")));
		ds.setLeakDetectionThreshold(Long.parseLong(env.getProperty("hikariCP.leakDetectionThreshold")));
		ds.setConnectionTestQuery(env.getProperty("hikariCP.validationQuery"));
		ds.setAutoCommit(false);
		return ds;
	}

}
