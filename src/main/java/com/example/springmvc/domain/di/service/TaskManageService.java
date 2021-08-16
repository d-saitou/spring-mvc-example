package com.example.springmvc.domain.di.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springmvc.domain.di.repository.jpa.TTaskRepository;
import com.example.springmvc.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Service that manages tasks as an example of DB-CRUD.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
public class TaskManageService {

	private final TTaskRepository repo;

	/**
	 * Get task by task id.
	 * @param id task id.
	 * @return task entity.
	 */
	public TTask txGetTaskById(String id) {
		return repo.findById(id);
	}

	/**
	 * Get tasks by user id.
	 * @param userid user id.
	 * @return task entities.
	 */
	public List<TTask> txGetTaskByUserid(String userid) {
		return repo.findByUserIdEquals(userid);
	}

	/**
	 * Get pagination tasks by user ID.
	 * @param userid   user id.
	 * @param pageable Pageable object.
	 * @return Page object.
	 */
	public Page<TTask> txGetTaskPagingListByUserid(String userid, Pageable pageable) {
		return repo.findByUserIdEquals(userid, pageable);
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
	 * Delete task by task id.
	 * @param id task id.
	 * @return Number of deletes.
	 */
	public int txDeleteTask(int id) {
		return repo.removeById(id);
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
