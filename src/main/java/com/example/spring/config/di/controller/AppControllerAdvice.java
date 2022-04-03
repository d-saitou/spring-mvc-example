package com.example.spring.config.di.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.spring.utility.CommonUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller advice.
 *
 * <p>{@link WebDataBinder#setValidator()}:<br>
 * Set Validator in WebDataBinder so that validator refers to Spring's message resource.
 *
 * <p>{@link WebDataBinder#setAutoGrowCollectionLimit()}:<br>
 * In Spring MVC, the maximum number of list items (form object) received by controller
 * is 256 by default. Therefore, use the InitBinder annotation and WebDataBinder class
 * to change the maximum number of list items.
 * Set the max number in List to application.properties and get it by Value annotation.
 *
 * <p>{@link ExceptionHandler} annotation:<br>
 * Error handling method executed when an exception occurs in the controller classes.
 * Implement it by adding ExceptionHandler annotation to the method.
 */
@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class AppControllerAdvice {

	@Value("${application.form.MaxListSize}")
	private int formMaxListSize;

//	@Autowired
//	private Validator validator;

	/**
	 * Change the maximum number of list items. (form object)
	 * @param binder WebDataBinder object.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Set validator for referencing spring's message resources.
//		binder.setValidator(validator);
		// Set maximum number of form object lists received by the controller.
		binder.setAutoGrowCollectionLimit(formMaxListSize);
	}

	/**
	 * Handle NullPointerException by ExceptionHandler annotation.
	 * @param model Model object.
	 * @param e     Exception object.
	 * @return request url.
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String nullPointerExceptionHandler(Model model, Exception e) {
		log.error("Exception occurred.", e);
		model.addAttribute("stacktrace", CommonUtility.getStackTraceString(e));
		return "error";
	}

}
