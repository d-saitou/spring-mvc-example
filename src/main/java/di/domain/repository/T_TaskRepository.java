package di.domain.repository;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.entity.T_Task;

/**
 * JPA repository (table: t_task)<br>
 * <p>
 *  This interface implements Spring Data JPA repository.
 *  If you need proprietary methods other than Spring Data JPA standard 
 *  implementation methods (findAll(), save() etc), define additional 
 *  methods for this interface.
 *  Examine proprietary method implementation method as necessary.
 * <p>
 *  The repository is created by extending the following classes.<br>
 *  JpaRepository {@literal <}entity class, primary key type{@literal >}
 * <p>
 *  In addition, it is possible to implement the implementation class 
 *  definitions of this interface and proprietary processing by creating 
 *  extended classes, so investigate as necessary.
 * <p>
 *  * For the configulation, refer to "Spring Data JPA/Hibernate" comment 
 *    part in application-config.xml.
 */
@Repository @Scope("prototype")
public interface T_TaskRepository extends JpaRepository<T_Task, Integer> {
	
	/**
	 * Select by ID (implementation pattern by named native query)
	 * @param id ID
	 * @return select result
	 */
	@Query( name = "T_TaskRepository.findById", nativeQuery = true,
			value = "select * from t_task where id = :id limit 1")
	public T_Task findById(@Param("id") Integer id);
	
	/**
	 * Select first (implementation pattern by named native query)
	 * @return select result
	 */
	@Query( name = "T_TaskRepository.findTopOne", nativeQuery = true,
			value = "select * from t_task limit 1")
	public T_Task findTopOne();
	
	/**
	 * Select all by pagination (implementation pattern by named JPQL query)
	 * @param userid   user ID
	 * @param pageable Pageable
	 * @return select result (Page object)
	 */
	@Query( name = "T_TaskRepository.findPageingByUserid",
			value = "select t from T_Task t where t.userid = :userid")
	public Page<T_Task> findPaginationByUserid(
			@Param("userid") String userid, Pageable pageable);
	
	/**
	 * Update by ID (implementation pattern by named JPQL query)
	 * @param id           ID
	 * @param title        title
	 * @param scheduledate scheduled date
	 * @param status       status
	 * @param description  description
	 * @param userid       user ID
	 * @return update count
	 */
	@Query( name = "T_TaskRepository.setEntity",
			value = "update T_Task t" +
			        " set t.title = :title, t.scheduledate = :scheduledate, t.status = :status," +
			        " t.description = :description, t.userid = :userid where t.id = :id")
	@Modifying	// Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int setEntity(
			@Param("id") Integer id, @Param("title") String title,
			@Param("scheduledate") Date scheduledate, @Param("status") boolean status,
			@Param("description") String description, @Param("userid") String userid);
	
	/**
	 * Delete by ID (implementation pattern by method name)
	 * @param id ID
	 * @return delete count
	 */
	@Modifying	// Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int removeById(int id);
	
}
