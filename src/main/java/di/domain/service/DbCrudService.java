package di.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import app.form.TaskForm;
import di.domain.repository.T_TaskRepository;
import domain.entity.T_Task;

/**
 * "DB-CRUD control" screens service<br>
 * <p>
 *  This class implements access of the DB used by "DB-CRUD control" 
 *  screens.
 *  This class performs a CRUD control on the task schema in the DB.
 * <p>
 *  Batch size used by "DB-CRUD (batch-update)" screen are stored in 
 *  application.properties and referenced by Value annotation.
 * <p>
 *  * For transaction configulation, see "Transaction" comment part in 
 *    application-config.xml.
 */
@Service @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DbCrudService {
	
	@Value("${hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private T_TaskRepository repo;
	
	/**
	 * Select all tasks
	 * @return select result
	 */
	public List<T_Task> txGetTaskAll() {
		return this.repo.findAll();
	}
	
	/**
	 * Select task by task ID
	 * @param id task ID
	 * @return select result
	 */
	public T_Task txGetTaskById(int id) {
		return this.repo.findById(Integer.valueOf(id));
	}
	
	/**
	 * Select pagination task by user ID
	 * @param userid   user ID
	 * @param pageable Pageable
	 * @return select result (Page object)
	 */
	public Page<T_Task> txGetPaginationByUserid(
			String userid, Pageable pageable) {
		return this.repo.findPaginationByUserid(userid, pageable);
	}
	
	/**
	 * Insert task
	 * @param task   task
	 * @param userid user ID
	 * @return entity
	 */
	public T_Task txInsertTask(T_Task task, String userid) {
		task.setUserid(userid);
		return this.repo.save(task);
	}
	
	/**
	 * Update task by task ID
	 * @param task   task
	 * @param userid user ID
	 * @return update count
	 */
	public int txUpdateTaskById(T_Task task, String userid) {
		task.setUserid(userid);
		return this.repo.setEntity(
				task.getId(), task.getTitle(), task.getScheduledate(),
				task.isStatus(), task.getDescription(), task.getUserid());
	}
	
	/**
	 * Update multiple tasks
	 * @param list task list
	 * @return update count
	 */
	public int txBatchUpdateTaskById(List<T_Task> list) {
		int cnt = 0;
		for (T_Task task : list) {
			this.repo.save(task);
			cnt++;
			if (cnt % this.batchSize == 0) {
				this.repo.flush();
				// In the case of normal JPA, call EntityManager#flush() and EntityManager#clear() method.
				// In the case of Spring Data JPA repository, call <repository class>#flush () method only.
				//EntityManager#flush();
				//EntityManager#clear();
			}
		}
		return cnt;
	}
	
	/**
	 * Delete task by task ID
	 * @param id task ID
	 * @return delete count
	 */
	public int txDeleteTaskById(int id) {
		return this.repo.removeById(id);
	}
	
	/**
	 * Transaction rollback test
	 */
	public void txTransactionTest() {
		// Get first item of task
		T_Task task = this.repo.findTopOne();
		// Normal update
		task.setUserid("testuser");
		this.repo.setEntity(
				task.getId(), task.getTitle(), task.getScheduledate(),
				task.isStatus(), task.getDescription(), task.getUserid());
		// Cause exception by overflow of user ID
		task.setUserid("XXXXXXXXXXX");
		this.repo.setEntity(
				task.getId(), task.getTitle(), task.getScheduledate(),
				task.isStatus(), task.getDescription(), task.getUserid());
		return;
	}
	
	/**
	 * Convert entity to form
	 * @param e entity
	 * @return form
	 */
	public TaskForm convertEntityToForm(T_Task e) {
		TaskForm f = new TaskForm();
		BeanUtils.copyProperties(e, f);
		return f;
	}
	
	/**
	 * Convert form to entity
	 * @param f form
	 * @return entity
	 */
	public T_Task convertFormToEntity(TaskForm f) {
		T_Task e = new T_Task();
		BeanUtils.copyProperties(f, e);
		return e;
	}
	
}
