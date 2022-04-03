package com.example.springmvc.application.di.controller;

import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.springmvc.application.di.helper.TaskListHelper;
import com.example.springmvc.application.form.PageInfoForm;
import com.example.springmvc.application.form.TaskListConditionForm;
import com.example.springmvc.application.form.TaskListForm;
import com.example.springmvc.config.security.UserDetailsImpl;
import com.example.springmvc.domain.di.service.TaskManageService;
import com.example.springmvc.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Task list screen controller.
 */
@Controller
// @SessionAttributes(types = { TaskListConditionForm.class, PageInfoForm.class })
@SessionAttributes(names = { "taskListConditionForm", "pageInfoForm" })
@RequiredArgsConstructor
public class TaskListController {

	private final TaskManageService service;

	private final TaskListHelper helper;

	/**
	 * GET request to initially display the screen.
	 * @param model         Model object.
	 * @param locale        Locale object.
	 * @param sessionStatus SessionStatus object.
	 * @return HTML template path.
	 */
	@GetMapping("/task/list")
	public String getForInit(Model model, Locale locale, SessionStatus sessionStatus) {
		// sessionStatus.setComplete(); // Delete the object saved in the session.
		model.addAttribute("taskListConditionForm", new TaskListConditionForm());
		model.addAttribute("pageInfoForm", null);
		model.addAttribute("completionMap", helper.getCompletionComboBoxMap(locale));
		return "content/TaskList";
	}

	/**
	 * POST request for displaying search results.
	 * @param form        search conditions entered on the screen.
	 * @param result      BindingResult object.
	 * @param model       Model object.
	 * @param locale      Locale object.
	 * @param userDetails UserDetailsImpl object.
	 * @return HTML template path.
	 */
	@PostMapping("/task/list")
	public String postForSearch(@Validated @ModelAttribute TaskListConditionForm form,
			BindingResult result, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (result.hasErrors()) {
			model.addAttribute("taskListConditionForm", form);
			model.addAttribute("completionMap", helper.getCompletionComboBoxMap(locale));
			return "content/TaskList";
		} else {
			model.addAttribute("taskListConditionForm", form);
			return "redirect:/task/list/1";
		}
	}

	/**
	 * GET request for paging.
	 * @param form        search conditions stored in the session.
	 * @param pageNo      page number.
	 * @param model       Model object.
	 * @param locale      Locale object.
	 * @param userDetails UserDetailsImpl object.
	 * @return HTML template path.
	 */
	@GetMapping(path = "/task/list/{pageNo}")
	public String getForPaging(
			// @SessionAttribute("taskListConditionForm") TaskListConditionForm form,
			@ModelAttribute(name = "taskListConditionForm", binding = false) TaskListConditionForm form,
			@PathVariable("pageNo") String pageNo, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		// Store page number.
		form.setPage(Integer.parseInt(pageNo));

		// Search for tasks.
		Page<TTask> page = service.txGetTaskPagingList(
				form.getPage(),
				form.getTitle(),
				helper.convertScheduledDate(form.getMinDate(), locale),
				helper.convertScheduledDate(form.getMaxDate(), locale),
				form.getCompletion(),
				form.getDescription(),
				userDetails.getUsername());
		TaskListForm taskList = new TaskListForm();
		taskList.setTaskList(helper.convertEntityToForm(page.getContent(), locale));

		// Store page information.
		PageInfoForm pageInfo = new PageInfoForm();
		pageInfo.setPage(form.getPage());
		pageInfo.setTotalElements(page.getTotalElements());
		pageInfo.setTotalPages(page.getTotalPages());
		pageInfo.setHasPreviousPage(page.hasPrevious());
		pageInfo.setHasNextPage(page.hasNext());

		// Display the screen.
		model.addAttribute("taskListForm", taskList);
		model.addAttribute("pageInfoForm", pageInfo);
		model.addAttribute("completionMap", helper.getCompletionComboBoxMap(locale));
		return "content/TaskList";
	}

}
