package com.example.spring.domain.di.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.spring.domain.di.repository.jpa.TTaskRepository;
import com.example.spring.domain.di.repository.jpa.specification.TTaskSpecs;
import com.example.spring.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Service that manages tasks as an example of DB-CRUD.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
public class TaskManageService {


	@Value("${web.screen.TaskList.pageSize}")
	protected int pageSize;

	private final TTaskRepository repo;

	/**
	 * Get task by task id.
	 * @param taskId task id.
	 * @return task entity.
	 */
	public TTask txGetTaskByTaskId(String taskId) {
		return repo.findByTaskId(taskId);
	}

	/**
	 * Get tasks by user id.
	 * @param userid user id.
	 * @return task entities.
	 */
	public List<TTask> txGetTaskByUserid(String userid) {
		return repo.findByCreatedByEquals(userid);
	}

	/**
	 * Get paginated tasks.
	 * @param page        page number.
	 * @param title       title.
	 * @param minDate     start date of search period.
	 * @param maxDate     end date of search period.
	 * @param completion  completion status.
	 * @param description description.
	 * @param userId      user id.
	 * @return Page object.
	 */
	public Page<TTask> txGetTaskPagingList(
			int page, String title, LocalDate minDate, LocalDate maxDate,
			Boolean completion, String description, String userId) {
		Specification<TTask> spec = Specification
				.where(TTaskSpecs.titleContains(title))
				.and(TTaskSpecs.scheduledDateGreaterThanOrEquals(minDate))
				.and(TTaskSpecs.scheduledDateLessThanOrEquals(maxDate))
				.and(TTaskSpecs.completionEquals(completion))
				.and(TTaskSpecs.descriptionContains(description))
				.and(TTaskSpecs.createdByEquals(userId));
		String[] order = new String[] { "taskId" };
		PageRequest pageable = PageRequest.of(page - 1, pageSize, Direction.ASC, order);
		return repo.findAll(spec, pageable);
	}

	/**
	 * Update tasks.
	 * @param tasks task entities.
	 * @return Number of updates.
	 */
	public int txSaveTasks(List<TTask> tasks) {
		repo.saveAll(tasks);
		return tasks.size();
	}

	/**
	 * Update task.
	 * @param task task entity.
	 * @return Number of updates.
	 */
	public int txUpdateTask(TTask task) {
		return repo.setEntity(
				task.getTaskId(), task.getTitle(), task.getScheduledDate(),
				task.getCompletion(), task.getDescription(),
				task.getModifiedBy(), task.getModifiedDate());
	}

	/**
	 * Delete task by task id.
	 * @param taskId task id.
	 * @return Number of deletes.
	 */
	public int txDeleteTask(int taskId) {
		return repo.removeByTaskId(taskId);
	}

	/**
	 * Delete tasks.
	 * @param tasks task entities.
	 * @return Number of deletes.
	 */
	public int txDeleteTasks(List<TTask> tasks) {
		repo.deleteAll(tasks);
		return tasks.size();
	}

}
