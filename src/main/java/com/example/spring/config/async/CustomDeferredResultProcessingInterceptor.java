package com.example.spring.config.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * Common methods of asynchronous processing using DeferredResult.
 * Implements common methods in async processing executed by Spring MVC unmanaged thread.
 */
@Slf4j
public class CustomDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor {

	/**
	 * Before execution ServletRequest#startAsync.
	 * @param req    NativeWebRequest object.
	 * @param result DeferredResult object.
	 */
	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest req, DeferredResult<T> result) {
	}

	/**
	 * After executionServletRequest#startAsync.
	 * @param req    NativeWebRequest object.
	 * @param result DeferredResult object.
	 */
	@Override
	public <T> void preProcess(NativeWebRequest req, DeferredResult<T> result) {
		log.info("Start async processing.");
	}

	/**
	 * At asynchronous processing end, before execution AsyncContext#dispatch.
	 * @param req              NativeWebRequest object.
	 * @param result           DeferredResult object.
	 * @param concurrentResult Object.
	 */
	@Override
	public <T> void postProcess(
			NativeWebRequest req, DeferredResult<T> result, Object concurrentResult) {
		log.info("End async processing.");
	}

	/**
	 * At servlet container timeout detection (AsyncListener#onTimeout).
	 * @param req    NativeWebRequest object.
	 * @param result DeferredResult object.
	 */
	@Override
	public <T> boolean handleTimeout(NativeWebRequest req, DeferredResult<T> result) {
		log.info("Async processing timed out.");
		// deferredResult.setResult(CommonUtility.genericsCast("common/timeout"));
		return true;
	}

	/**
	 * At servlet container detects an exception (AsyncListener#onError).
	 * @param req    NativeWebRequest object.
	 * @param result DeferredResult object.
	 * @param t      Throwable object.
	 */
	@Override
	public <T> boolean handleError(NativeWebRequest req, DeferredResult<T> result, Throwable t) {
		log.error("Error occurs in async processing.", t);
		return true;
	}

	/**
	 * At servlet standard asynchronous processing ends (AsyncListener#onComplete).
	 * @param req    NativeWebRequest object.
	 * @param result DeferredResult object.
	 */
	@Override
	public <T> void afterCompletion(NativeWebRequest req, DeferredResult<T> result) {
		log.info("Complete async processing.");
	}

}
