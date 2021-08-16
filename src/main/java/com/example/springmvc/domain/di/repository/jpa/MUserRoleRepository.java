package com.example.springmvc.domain.di.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springmvc.domain.entity.jpa.MUserRole;
import com.example.springmvc.domain.entity.jpa.MUserRolePK;

/**
 * Spring Data JPA repository (table: m_user_role).
 */
@Repository
//@Scope("prototype")
public interface MUserRoleRepository extends JpaRepository<MUserRole, MUserRolePK> {
}
