package di.domain.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.entity.T_Scheduledtask_History;

/**
 * JPA repository (table: t_scheduledtask_history)<br>
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
public interface T_Scheduledtask_HistoryRepository
		extends JpaRepository<T_Scheduledtask_History,Integer> {
	
	/**
	 * Select all by ID descending sorting (implementation pattern by method name)
	 * @return select result
	 */
	public List<T_Scheduledtask_History> findAllByOrderByIdDesc();
	
}
