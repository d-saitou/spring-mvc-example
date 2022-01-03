package com.example.springmvc.domain.di.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.springmvc.domain.di.repository.jpa.TScheduledTaskHistoryRepository;
import com.example.springmvc.domain.entity.jpa.TScheduledTaskHistory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service that saves the execution history of scheduled tasks.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskService {

	private final TScheduledTaskHistoryRepository repo;

	/**
	 * Save task history.
	 * @param methodName method name of scheduled task.
	 */
	public void txSaveTaskHistory(String methodName) {
		TScheduledTaskHistory history = new TScheduledTaskHistory();
		history.setMethod(methodName);
		history.setMessage("");
		log.info("Scheduled task execute. [method : {}()]", methodName);
		try {
			this.repo.save(history);
		} catch (Exception e) {
			log.error("Scheduled task failed. [method : {}()]", methodName, e);
			throw e;
		}
	}

	/**
	 * Get all task history.
	 * @return entities.
	 */
	public List<TScheduledTaskHistory> txGetAllTaskHistory() {
		return repo.findAll(Sort.by(Direction.DESC, "id"));
	}

}
