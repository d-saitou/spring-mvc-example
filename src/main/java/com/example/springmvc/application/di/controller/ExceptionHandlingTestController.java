package com.example.springmvc.application.di.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springmvc.config.di.controller.AppControllerAdvice;
import com.example.springmvc.config.di.handler.AppHandlerExceptionResolver;
import com.example.springmvc.domain.di.aop.MethodLoggingInterceptor;

/**
 * Exception handle test controller.
 *
 * <p>Spring MVC can implement exception handles in three ways.
 * This class implements exception handles using methods 2 and 3 below.
 *
 * <p>1. AfterThrowing annotation<br>
 *  Catch an exception on a method basis.
 *  Implemented by adding an AfterThrowing annotation to the method.
 *  See {@link MethodLoggingInterceptor} class for the implementation.
 *  (Currently invalidated)
 *
 * <p>2. ExceptionHandler annotation<br>
 *  Catch an exception within the controller class.
 *  Implemented by adding method to ExceptionHandler annotation.
 *  See {@link AppControllerAdvice} class for implementation.
 *
 * <p>3. HandlerExceptionResolver class<br>
 *  Catch an exception throughout the application.
 *  Used by implementing the HandlerExceptionResolver class.
 *  See {@link AppHandlerExceptionResolver} class for implementation.
 */
@Controller
public class ExceptionHandlingTestController {

	/**
	 * Cause NullPointerException (exception handle test by ExceptionHandler annotation).
	 * @throws NullPointerException NullPointerException
	 */
	@GetMapping("/exception/null")
	public void nullPointerExTest() throws NullPointerException {
		throw new NullPointerException("NullPointerException");
	}

	/**
	 * Cause IllegalArgumentException (exception handle test by HandlerExceptionResolver).
	 * @throws IllegalArgumentException IllegalArgumentException
	 */
	@GetMapping("/exception/illegal")
	public void illegalArgumentExTest() throws IllegalArgumentException {
		throw new IllegalArgumentException("IllegalArgumentException");
	}

}
