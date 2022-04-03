package com.example.spring.application.di.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring.application.di.helper.TaskListHelper;
import com.example.spring.application.form.TaskForm;
import com.example.spring.application.form.TaskListForm;
import com.example.spring.config.security.UserDetailsImpl;
import com.example.spring.domain.di.service.TaskManageService;
import com.example.spring.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Task create screen controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskCreateController {

	@Value("${web.screen.TaskList.pageSize}")
	private int pageSize;

	private final TaskManageService service;

	private final TaskListHelper helper;

	/**
	 * GET request.
	 * @param model Model object.
	 * @return HTML template path.
	 */
	@GetMapping("/task/create")
	public String get(Model model) {
		model.addAttribute("taskListForm", getInitialForm());
		return "content/TaskCreate";
	}

	/**
	 * POST request.
	 * @param form        TaskListForm.
	 * @param result      BindingResult object.
	 * @param model       Model object.
	 * @param locale      Locale object.
	 * @param userDetails UserDetailsImpl object.
	 * @return HTML template path.
	 */
	@PostMapping("/task/create")
	public String post(
			@Validated @ModelAttribute TaskListForm form,
			BindingResult result, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (result.hasErrors()) {
			model.addAttribute("taskListForm", form);
		} else {
			String userid = userDetails.getUsername();
			// create tasks
			List<TTask> entities = new ArrayList<TTask>();
			for (TaskForm task : form.getTaskList()) {
				task.setUserId(userid);
				entities.add(helper.convertFormToEntity(task, locale));
			}
			service.txSaveTasks(entities);
			// get tasks
			model.addAttribute("taskListForm", getInitialForm());
		}
		return "content/TaskCreate";
	}

	/**
	 * Get the initialized form.
	 * @return TaskListForm.
	 */
	private TaskListForm getInitialForm() {
		List<TaskForm> tasks = new ArrayList<TaskForm>();
		tasks.add(new TaskForm());
		return new TaskListForm().setTaskList(tasks);
	}

}
