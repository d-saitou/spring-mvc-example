package di.spring.async;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Common functions of asynchronous processing (for Callable)<br>
 * <p>
 *  This class extends CallableProcessingInterceptorAdapter class and 
 *  implements a common method in asynchronous processing executed by 
 *  Spring MVC managed thread.
 * <p>
 *  In order to use this class, it is necessary to set bean definition 
 *  in Spring setting.<br>
 *  * See "mvc:async-support" tag in application-config.xml.
 */
@Slf4j
public class CustomCallableProcessingInterceptor extends CallableProcessingInterceptorAdapter {
	
	// interface definition
	
	// Before execution ServletRequest#startAsync
	//<T> void beforeConcurrentHandling(
	//		NativeWebRequest request, Callable<T> task) throws Exception;
	
	// After executionServletRequest#startAsync
	//<T> void preProcess(
	//		NativeWebRequest request, Callable<T> task) throws Exception;
	
	// At asynchronous processing end, before execution AsyncContext#dispatch
	//<T> void postProcess(
	//		NativeWebRequest request, Callable<T> task, Object concurrentResult) throws Exception;
	
	// At servlet container timeout detection (trigger: AsyncListener#onTimeout)
	//<T> Object handleTimeout(
	//		NativeWebRequest request, Callable<T> task) throws Exception;
	
	// At servlet standard asynchronous processing ends (trigger: AsyncListener#onComplete and onError)
	//<T> void afterCompletion(
	//		NativeWebRequest request, Callable<T> task) throws Exception;
	
	/**
	 * Log output at execution of asynchronous processing
	 * @param request NativeWebRequest
	 * @param task    Callable
	 */
	public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
		log.info("Async function start.(use Callable)");
	}
	
	/**
	 * Log output at the end of asynchronous processing
	 * @param request          NativeWebRequest
	 * @param task             Callable
	 * @param concurrentResult Object
	 */
	public <T> void postProcess(
			NativeWebRequest request, Callable<T> task, Object concurrentResult) throws Exception {
		log.info("Async function finished.(use Callable)");
	}
	
	/**
	 * Log output at asynchronous processing timeout
	 * @param request NativeWebRequest
	 * @param task    DeferredResult
	 */
	public <T> Object handleTimeout(
			NativeWebRequest request, Callable<T> task) throws Exception {
		log.info("Async function timeout.(use Callable)");
		return "common/timeout";
	}
	
}
