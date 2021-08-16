package com.example.springmvc.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configure asynchronous processing.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	/**
	 * Configure AsyncTaskExecutor and register as bean.
	 * @return AsyncTaskExecutor object.
	 */
	@Bean
	public AsyncTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.setKeepAliveSeconds(300);
		executor.setThreadNamePrefix("ThreadPoolTaskExecutor");
		return executor;
	}

}
