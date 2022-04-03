package com.example.spring.domain.di.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.domain.entity.jpa.VUserAuthority;
import com.example.spring.domain.entity.jpa.VUserAuthorityPK;

/**
 * Spring Data JPA repository (view: v_user_authority).
 */
@Repository
//@Scope("prototype")
public interface VUserAuthorityRepository extends JpaRepository<VUserAuthority, VUserAuthorityPK> {

	/**
	 * Select by user id (implementation pattern by method name).
	 * @param userId user id.
	 * @return user and authority entities.
	 */
	public List<VUserAuthority> findByUserIdEquals(String userId);

}
