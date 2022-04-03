package com.example.spring.domain.di.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.domain.entity.jpa.TScheduledTaskHistory;

/**
 * Spring Data JPA repository (table: t_scheduledtask_history).
 */
@Repository
//@Scope("prototype")
public interface TScheduledTaskHistoryRepository extends JpaRepository<TScheduledTaskHistory, Integer> {
}
