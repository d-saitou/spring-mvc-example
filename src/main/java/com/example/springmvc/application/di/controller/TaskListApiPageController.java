package com.example.springmvc.application.di.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Task list (using API) screen controller.
 */
@Controller
@RequestMapping("/task/api/page")
public class TaskListApiPageController {

	/**
	 * GET request.
	 * @return HTML template path.
	 */
	@GetMapping
	public String get() {
		return "content/TaskListApiPage";
	}

}
