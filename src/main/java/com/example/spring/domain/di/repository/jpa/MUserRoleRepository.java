package com.example.spring.domain.di.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.spring.domain.entity.jpa.MUserRole;
import com.example.spring.domain.entity.jpa.MUserRolePK;

/**
 * Spring Data JPA repository (table: m_user_role).
 */
@Repository
//@Scope("prototype")
public interface MUserRoleRepository extends JpaRepository<MUserRole, MUserRolePK> {

	/**
	 * Delete by user id (implementation pattern by method name).
	 * @param userId user id.
	 * @return Number of deletes.
	 */
	@Modifying // Automatic call of EntityManager#clear
	//@Modifying(clearAutomatically = false)
	public int deleteByUserIdEquals(String userId);

}
