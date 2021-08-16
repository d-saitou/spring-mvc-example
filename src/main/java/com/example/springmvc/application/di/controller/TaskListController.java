package com.example.springmvc.application.di.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.application.di.helper.TaskListHelper;
import com.example.springmvc.application.form.TaskPageForm;
import com.example.springmvc.config.security.UserDetailsImpl;
import com.example.springmvc.domain.di.service.TaskManageService;
import com.example.springmvc.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Task list screen controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskListController {

	@Value("${web.screen.TaskList.pageSize}")
	protected int pageSize;

	protected final TaskManageService service;

	protected final TaskListHelper helper;

	/**
	 * GET request.
	 * @param page        request page no.
	 * @param userDetails UserDetailsImpl object.
	 * @param model       Model object.
	 * @param locale      Locale object..
	 * @return HTML template path.
	 */
	@GetMapping("/task/list")
	public String get(
			@RequestParam("page") int page,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			Model model, Locale locale) {
		String userid = userDetails.getUsername();
		model.addAttribute("taskPageForm", getTaskPage(page, locale, userid));
		return "content/TaskList";
	}

	/**
	 * Get the form of the paged task.
	 * @param page   request page no.
	 * @param locale Locale object.
	 * @param userid user id.
	 * @return task page form.
	 */
	protected TaskPageForm getTaskPage(int page, Locale locale, String userid) {
		// get task page
		String[] order = new String[] { "id" };
		PageRequest pageable = PageRequest.of(page - 1, pageSize, Direction.ASC, order);
		Page<TTask> taskPage = service.txGetTaskPagingListByUserid(userid, pageable);
		// create form
		TaskPageForm form = new TaskPageForm();
		form.setTasklist(helper.convertEntityToForm(taskPage.getContent(), locale));
		form.setCurrentPage(page);
		form.setTotalPages(taskPage.getTotalPages());
		form.setHasPreviousPage(taskPage.hasPrevious());
		form.setHasNextPage(taskPage.hasNext());
		return form;
	}

}
