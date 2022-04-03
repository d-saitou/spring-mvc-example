package com.example.spring.domain.di.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.spring.domain.di.service.ScheduledTaskService;

import lombok.RequiredArgsConstructor;

/**
 * Component that registers scheduled tasks.
 */
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

	@Value("${schedule.execute}")
	private boolean execute;

	private final ScheduledTaskService service;

	/**
	 * fixedRate task.
	 */
	@Scheduled(fixedRateString = "${schedule.fixedRateTask.schedule.fixedRate}")
	public void fixedRateTask() {
		if (this.execute) {
			service.txSaveTaskHistory("fixedRateTask");
		}
	}

	/**
	 * initialDelay and fixedDelay task.
	 */
	@Scheduled(
			initialDelayString = "${schedule.initialAndFixedDelayTask.schedule.initialDelay}",
			fixedDelayString = "${schedule.initialAndFixedDelayTask.schedule.fixedDelay}")
	public void initialAndFixedDelayTask() {
		if (this.execute) {
			service.txSaveTaskHistory("initialAndFixedDelayTask");
		}
	}

	/**
	 * cron task.
	 */
	@Scheduled(cron = "${schedule.cronTask.schedule.cron}")
	public void cronTask() {
		if (this.execute) {
			service.txSaveTaskHistory("cronTask");
		}
	}

}
