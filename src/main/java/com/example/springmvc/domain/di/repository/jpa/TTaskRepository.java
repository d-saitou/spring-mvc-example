package com.example.springmvc.domain.di.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface TTaskRepository extends JpaRepository<TTask, Integer> {

	/**
	 * Select by task id (implementation pattern by named native query).
	 * @param id task id.
	 * @return task entities.
	 */
	@Query(nativeQuery = true, value = "select * from t_task where id = :id limit 1")
	public TTask findById(@Param("id") String id);

	/**
	 * Select by user id (implementation pattern by method name).
	 * @param userId user id.
	 * @return task entities.
	 */
	public List<TTask> findByUserIdEquals(String userId);

	/**
	 * Select by user id and pagination (implementation pattern by method name).
	 * @param userId   user id.
	 * @param pageable Pageable object.
	 * @return task entities. (Page object)
	 */
	public Page<TTask> findByUserIdEquals(String userId, Pageable pageable);

	/**
	 * Update by task id (implementation pattern by named JPQL query).
	 * @param id           task id.
	 * @param title        title.
	 * @param scheduleDate scheduled date.
	 * @param status       status.
	 * @param description  description.
	 * @param userId       user id.
	 * @return Number of updates.
	 */
	@Query("update TTask t "
			+ "set t.title = :title, t.scheduleDate = :scheduleDate, t.status = :status, "
			+ "t.description = :description, t.userId = :userId where t.id = :id")
	@Modifying // Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int setEntity(
			@Param("id") Integer id, @Param("title") String title,
			@Param("scheduleDate") Date scheduleDate, @Param("status") boolean status,
			@Param("description") String description, @Param("userId") String userId);

	/**
	 * Delete by task id (implementation pattern by method name).
	 * @param id task id.
	 * @return Number of deletes.
	 */
	@Modifying // Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int removeById(int id);

}
