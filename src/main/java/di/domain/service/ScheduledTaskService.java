package di.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.domain.repository.T_Scheduledtask_HistoryRepository;
import domain.entity.T_Scheduledtask_History;
import lombok.extern.slf4j.Slf4j;

/**
 * Scheduled task execution service<br>
 * <p>
 *  This class implements a task called by ScheduledTasks class.
 * <p>
 *  * For transaction configulation, see "Transaction" comment part in 
 *    application-config.xml.
 */
@Service @Slf4j
public class ScheduledTaskService {
	
	@Autowired
	T_Scheduledtask_HistoryRepository repo;
	
	/**
	 * Register task history to DB
	 * @param methodName scheduled task method name
	 */
	public void txSaveTaskHistory(String methodName) {
		T_Scheduledtask_History history = new T_Scheduledtask_History();
		history.setFunction(methodName);
		history.setMessage("");
		history.setUpdatedate(new Date());
		log.info("Scheduled task execute. [method : {}()]", methodName);
		try {
			this.repo.save(history);
		} catch (Exception e) {
			log.error("Scheduled task failed. [method : {}()]", methodName);
			throw e;
		}
		return;
	}
	
}
