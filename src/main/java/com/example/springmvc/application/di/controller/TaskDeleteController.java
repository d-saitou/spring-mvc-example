package com.example.springmvc.application.di.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.domain.di.service.TaskManageService;

import lombok.RequiredArgsConstructor;

/**
 * Task delete controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskDeleteController {

	private final TaskManageService service;

	/**
	 * GET request.
	 * @param page request page no.
	 * @param id   task id.
	 * @return redirect uri.
	 * @throws ParseException
	 */
	@GetMapping("/task/delete/{id}")
	public String delete(
			@RequestParam(name = "page", required = false) int page,
			@PathVariable(name = "id", required = false) int id) throws ParseException {
		service.txDeleteTask(id);
		return "redirect:/task/list/" + page;
	}

}
