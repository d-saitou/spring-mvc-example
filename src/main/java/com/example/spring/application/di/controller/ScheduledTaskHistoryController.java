package com.example.spring.application.di.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.application.form.ScheduledTaskHistoryForm;
import com.example.spring.domain.di.service.ScheduledTaskService;
import com.example.spring.domain.entity.jpa.TScheduledTaskHistory;

import lombok.RequiredArgsConstructor;

/**
 * Scheduled task history (inaccessible) screen controller.
 */
@Controller
@RequestMapping("/schedule/history")
@RequiredArgsConstructor
public class ScheduledTaskHistoryController {

	private final ScheduledTaskService service;

	/**
	 * GET request.
	 * @param model Model object.
	 * @return HTML template path.
	 */
	@GetMapping
	public String get(Model model) {
		List<ScheduledTaskHistoryForm> formList = new LinkedList<ScheduledTaskHistoryForm>();
		for (TScheduledTaskHistory entity : service.txGetAllTaskHistory()) {
			ScheduledTaskHistoryForm form = new ScheduledTaskHistoryForm();
			form.setId(entity.getId());
			form.setMethod(entity.getMethod());
			form.setMessage(entity.getMessage());
			form.setCreatedDate(entity.getCreatedDate());
			formList.add(form);
		}
		model.addAttribute("formList", formList);
		return "content/ScheduledTaskHistory";
	}

}
