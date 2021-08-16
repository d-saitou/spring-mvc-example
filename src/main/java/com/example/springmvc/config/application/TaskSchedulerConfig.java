package com.example.springmvc.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Configure task scheduler.
 */
@Configuration
@EnableScheduling
public class TaskSchedulerConfig {

	/**
	 * Configure ThreadPoolTaskScheduler and register as bean.
	 * @return ThreadPoolTaskScheduler object.
	 */
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler ts = new ThreadPoolTaskScheduler();
		ts.setPoolSize(5);
		ts.setThreadNamePrefix("ThreadPoolTaskScheduler");
		return ts;
	}

}
