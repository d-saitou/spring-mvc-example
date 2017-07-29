package di.spring.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;
import util.common.CommonUtility;

/**
 * Common functions of asynchronous processing (for DeferredResult)<br>
 * <p>
 *  This class extends DeferredResultProcessingInterceptorAdapter class 
 *  and implements a common method in asynchronous processing executed 
 *  by Spring MVC unmanaged thread.
 * <p>
 *  In order to use this class, it is necessary to set bean definition 
 *  in Spring setting.<br>
 *  * See "mvc:async-support" tag in application-config.xml.
 */
@Slf4j
public class CustomDeferredResultProcessingInterceptor
		extends DeferredResultProcessingInterceptorAdapter {
	
	// interface definition
	
	// Before execution ServletRequest#startAsync
	//<T> void beforeConcurrentHandling(
	//		NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception;
	
	// After executionServletRequest#startAsync
	//<T> void preProcess(
	//		NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception;
	
	// At asynchronous processing end, before execution AsyncContext#dispatch
	//<T> void postProcess(
	//		NativeWebRequest request, DeferredResult<T> deferredResult,
	//		Object concurrentResult) throws Exception;
	
	// At servlet container timeout detection (trigger: AsyncListener#onTimeout)
	//<T> boolean handleTimeout(
	//		NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception;
	
	// At servlet standard asynchronous processing ends (trigger: AsyncListener#onComplete and onError)
	//<T> void afterCompletion(
	//		NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception;
	
	/**
	 * Log output at execution of asynchronous processing
	 * @param request        NativeWebRequest
	 * @param deferredResult DeferredResult
	 */
	public <T> void preProcess(
			NativeWebRequest request, DeferredResult<T> deferredResult)	throws Exception {
		log.info("Async function start.(use DeferredResult)");
	}
	
	/**
	 * Log output at the end of asynchronous processing
	 * @param request          NativeWebRequest
	 * @param deferredResult   DeferredResult
	 * @param concurrentResult Object
	 */
	public <T> void postProcess(
			NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult)
			throws Exception {
		log.info("Async function finished.(use DeferredResult)");
	}
	
	/**
	 * Log output at asynchronous processing timeout
	 * @param request        NativeWebRequest
	 * @param deferredResult DeferredResult
	 */
	public <T> boolean handleTimeout(
			NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
		log.info("Async function timeout.(use DeferredResult)");
		deferredResult.setResult(CommonUtility.genericsCast("common/timeout"));
		return false;
	}
	
}
