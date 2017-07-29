package di.domain.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.entity.V_User_Authority;

/**
 * JPA repository (view: v_user_authority)<br>
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
 * <p>
 *  * Since JPA needs to specify a unique key in Id annotation, this 
 *    application adds a field definition that makes the unique value to 
 *    the view, and uses it as a substitute for the unique key.
 *    It is necessary to investigate separately whether such usage is 
 *    acceptable or not.
 */
@Repository @Scope("prototype")
public interface V_User_AuthorityRepository extends JpaRepository<V_User_Authority, String> {
	
	/**
	 * Select by user ID (implementation pattern by named JPQL query)
	 * @param userid user ID
	 * @return select result
	 */
	@Query( name = "V_User_AuthorityRepository.findByUserid",
			value = "select v from V_User_Authority v where v.userid = :userid")
	public List<V_User_Authority> findByUserid(@Param("userid") String userid);
	
}
