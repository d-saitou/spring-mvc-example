package di.app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import di.domain.service.ScheduledTaskService;

/**
 * Scheduled task execution<br>
 * <p>
 *  This class implements Spring's task scheduling execution.
 *  Register a task schedule in Spring by implementing Scheduled annotation 
 *  to the method to be executed as a task.
 * <p>
 *  The configulation related to task are stored in application.properties 
 *  and referenced by Value annotation.
 * <p>
 *  In order to use task schedule, it is necessary to set Spring config.<br>
 *  * See "Task Scheduler" comment part in application-config.xml.
 */
@Component
public class ScheduledTasks {
	
	@Value("${scheduledTasks.execute}")
	private boolean execute;
	
	@Autowired
	ScheduledTaskService svc;
	
	/**
	 * fixedRate task
	 */
	@Scheduled(fixedRateString = "${scheduledTasks.fixedRateTask.schedule.fixedRate}")
	public void fixedRateTask() {
		if (this.execute) {
			svc.txSaveTaskHistory("fixedRateTask");
		}
		return;
	}
	
	/**
	 * initialDelay and fixedDelay task
	 */
	@Scheduled(
			initialDelayString = "${scheduledTasks.initialAndFixedDelayTask.schedule.initialDelay}",
			fixedDelayString = "${scheduledTasks.initialAndFixedDelayTask.schedule.fixedDelay}")
	public void initialAndFixedDelayTask() {
		if (this.execute) {
			svc.txSaveTaskHistory("initialAndFixedDelayTask");
		}
		return;
	}
	
	/**
	 * cron task
	 */
	@Scheduled(cron = "${scheduledTasks.cronTask.schedule.cron}")
	public void cronTask() {
		if (this.execute) {
			svc.txSaveTaskHistory("cronTask");
		}
		return;
	}
	
}
