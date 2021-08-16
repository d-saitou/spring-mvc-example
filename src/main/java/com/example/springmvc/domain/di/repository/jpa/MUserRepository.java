package com.example.springmvc.domain.di.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springmvc.domain.entity.jpa.MUser;

/**
 * Spring Data JPA repository (table: m_user).
 */
@Repository
//@Scope("prototype")
public interface MUserRepository extends JpaRepository<MUser, String> {

	/**
	 * Select all order by user id (implementation pattern by method name).
	 * @return user entities.
	 */
	public List<MUser> findAllByOrderByUserIdAsc();

	/**
	 * Select by user id. (implementation pattern by method name)
	 * @param userId user id.
	 * @return user entity.
	 */
	public MUser findByUserIdEquals(String userId);

	/**
	 * Update by task id (implementation pattern by named JPQL query).
	 * @param userId  user id.
	 * @param enabled true if user is valid.
	 * @return Number of updates.
	 */
	@Query(name = "MUserRepository.setEnable",
			value = "update MUser t set t.enabled = :enabled where t.userId = :userId")
	@Modifying // Automatic call of EntityManager#clear
	public int setEnable(@Param("userId") String userId, @Param("enabled") boolean enabled);

	/**
	 * Delete by user id (implementation pattern by method name).
	 * @param userId user id.
	 * @return Number of deletes.
	 */
	public int deleteByUserIdEquals(String userId);

}
