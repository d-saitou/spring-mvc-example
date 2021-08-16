package com.example.springmvc.application.di.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.application.di.helper.TaskListHelper;
import com.example.springmvc.application.form.TaskForm;
import com.example.springmvc.config.security.UserDetailsImpl;
import com.example.springmvc.domain.di.service.TaskManageService;
import com.example.springmvc.domain.entity.jpa.TTask;

import lombok.RequiredArgsConstructor;

/**
 * Task update screen controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskUpdateController {

	@Value("${web.screen.TaskList.pageSize}")
	private int pageSize;

	private final TaskManageService service;

	private final TaskListHelper helper;

	/**
	 * POST request.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @param page   request page no.
	 * @param id     task id.
	 * @return HTML template path.
	 */
	@GetMapping("/task/update/{id}")
	public String get(Model model, Locale locale,
			@RequestParam(name = "page", required = false) String page,
			@PathVariable(name = "id", required = false) String id) {
		TTask task = service.txGetTaskById(id);
		model.addAttribute("taskForm", helper.convertEntityToForm(task, locale));
		model.addAttribute("page", page);
		return "content/TaskUpdate";
	}

	/**
	 * POST request.
	 * @param page        request page no.
	 * @param form        TaskForm.
	 * @param result      BindingResult object.
	 * @param model       Model object.
	 * @param locale      Locale object.
	 * @param userDetails UserDetailsImpl object.
	 * @return redirect uri.
	 */
	@PostMapping("/task/update")
	public String post(
			@RequestParam(name = "page", required = false) String page,
			@Validated @ModelAttribute TaskForm form,
			BindingResult result, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (result.hasErrors()) {
			model.addAttribute("taskForm", form);
			model.addAttribute("page", page);
			return "content/TaskUpdate";
		} else {
			form.setUserId(userDetails.getUsername());
			List<TTask> entities = new ArrayList<TTask>();
			entities.add(helper.convertFormToEntity(form, locale));
			service.txSaveTasks(entities);
			return "redirect:/task/list?page=" + page;
		}
	}

}
