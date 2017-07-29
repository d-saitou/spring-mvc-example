package di.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import app.form.TaskHistoryDto;
import di.domain.repository.T_Scheduledtask_HistoryRepository;
import domain.entity.T_Scheduledtask_History;

/**
 * "Scheduled task history" screen service<br>
 * <p>
 *  This class implements access of the DB used by "Scheduled task history" 
 *  screen.
 * <p>
 *  * For transaction configulation, see "Transaction" comment part in 
 *    application-config.xml.
 */
@Service @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskHistoryService {
	
	@Autowired
	private T_Scheduledtask_HistoryRepository repo;
	
	/**
	 * Get entity by ID descending sorting
	 * @return entity list
	 */
	public List<T_Scheduledtask_History> txGetTaskHistoryOrderByIdDesc() {
		return this.repo.findAllByOrderByIdDesc();
	}
	
	/**
	 * Convert entity to form
	 * @param e entity
	 * @return form
	 */
	public TaskHistoryDto convertEntityToForm(T_Scheduledtask_History e) {
		TaskHistoryDto f = new TaskHistoryDto();
		BeanUtils.copyProperties(e, f);
		return f;
	}
	
}
