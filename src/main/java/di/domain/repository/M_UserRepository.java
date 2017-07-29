package di.domain.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.entity.M_User;

/**
 * JPA repository (table: m_user)<br>
 * <p>
 *  This interface implements Spring Data JPA repository.
 *  If you need proprietary methods other than Spring Data JPA standard 
 *  implementation methods (findAll(), save() etc...), define additional 
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
public interface M_UserRepository extends JpaRepository<M_User, String> {
	
	/**
	 * Select by user ID (implementation pattern by named JPQL query)
	 * @param userid user ID
	 * @return select result
	 */
	@Query( name = "M_UserRepository.findByUserid",
			value = "select m from M_User m where m.userid = :userid")
	public List<M_User> findByUserid(@Param("userid") String userid);
	
}
