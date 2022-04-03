package com.example.springmvc.domain.di.repository.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springmvc.domain.entity.jpa.TTask;

/**
 * Spring Data JPA repository (table: t_task).
 */
@Repository
//@Scope("prototype")
public interface TTaskRepository
		extends JpaRepository<TTask, Integer>, JpaSpecificationExecutor<TTask> {

	/**
	 * Select by task id (implementation pattern by named native query).
	 * @param taskId task id.
	 * @return task entities.
	 */
	@Query(nativeQuery = true, value = "select * from t_task where task_id = :taskId limit 1")
	public TTask findByTaskId(@Param("taskId") String taskId);

	/**
	 * Select by user id (implementation pattern by method name).
	 * @param createdBy user id.
	 * @return task entities.
	 */
	public List<TTask> findByCreatedByEquals(String createdBy);

	/**
	 * Select by user id and pagination (implementation pattern by method name).
	 * @param createdBy user id.
	 * @param pageable  Pageable object.
	 * @return task entities. (Page object)
	 */
	public Page<TTask> findByCreatedByEquals(String createdBy, Pageable pageable);

	/**
	 * Update by task id (implementation pattern by named JPQL query).
	 * @param taskId        task id.
	 * @param title         title.
	 * @param scheduledDate scheduled date.
	 * @param completion    completion status.
	 * @param description   description.
	 * @param modifiedBy    modified user id.
	 * @param modifiedDate  modified date.
	 * @return Number of updates.
	 */
	@Query("update TTask t "
			+ "set"
			+ " t.title = :title,"
			+ " t.scheduledDate = :scheduledDate,"
			+ " t.completion = :completion,"
			+ " t.description = :description,"
			+ " t.modifiedBy = :modifiedBy,"
			+ " t.modifiedDate = :modifiedDate "
			+ "where"
			+ " t.taskId = :taskId")
	@Modifying // Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int setEntity(
			@Param("taskId") Integer taskId,
			@Param("title") String title,
			@Param("scheduledDate") LocalDate scheduledDate,
			@Param("completion") Boolean completion,
			@Param("description") String description,
			@Param("modifiedBy") String modifiedBy,
			@Param("modifiedDate") LocalDateTime modifiedDate);

	/**
	 * Delete by task id (implementation pattern by method name).
	 * @param taskId task id.
	 * @return Number of deletes.
	 */
	@Modifying // Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int removeByTaskId(int taskId);

}
