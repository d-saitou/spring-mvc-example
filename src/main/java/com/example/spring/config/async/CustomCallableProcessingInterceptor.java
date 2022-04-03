package com.example.spring.config.async;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * Common methods of asynchronous processing using Callable.
 * Implements common methods in async processing executed by Spring MVC managed thread.
 */
@Slf4j
public class CustomCallableProcessingInterceptor implements CallableProcessingInterceptor {

	/**
	 * Before execution ServletRequest#startAsync.
	 * @param req  NativeWebRequest object.
	 * @param task Callable object.
	 */
	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest req, Callable<T> task) {
	}

	/**
	 * After executionServletRequest#startAsync.
	 * @param req  NativeWebRequest object.
	 * @param task Callable object.
	 */
	@Override
	public <T> void preProcess(NativeWebRequest req, Callable<T> task) {
		log.info("Start async processing.");
	}

	/**
	 * At asynchronous processing end, before execution AsyncContext#dispatch.
	 * @param req              NativeWebRequest object.
	 * @param task             Callable object.
	 * @param concurrentResult Object object.
	 */
	@Override
	public <T> void postProcess(
			NativeWebRequest req, Callable<T> task, Object concurrentResult) {
		log.info("End async processing.");
	}

	/**
	 * At servlet container timeout detection. (AsyncListener#onTimeout)
	 * @param req  NativeWebRequest object.
	 * @param task Callable object.
	 */
	@Override
	public <T> Object handleTimeout(NativeWebRequest req, Callable<T> task) {
		log.info("Async processing timed out.");
		return RESULT_NONE;
	}

	/**
	 * At servlet container detects an exception. (AsyncListener#onError)
	 * @param req  NativeWebRequest object.
	 * @param task Callable object.
	 * @param t    Throwable object.
	 */
	@Override
	public <T> Object handleError(NativeWebRequest req, Callable<T> task, Throwable t) {
		log.error("Error occurs in async processing.", t);
		return RESULT_NONE;
	}

	/**
	 * At servlet standard asynchronous processing ends. (AsyncListener#onComplete)
	 * @param req  NativeWebRequest object.
	 * @param task Callable object.
	 */
	@Override
	public <T> void afterCompletion(NativeWebRequest req, Callable<T> task) {
		log.info("Complete async processing.");
	}

}