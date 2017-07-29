package di.app.controller.contents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Exception handle test controller<br>
 * <p>
 *  Spring MVC can implement exception handles in three ways.
 *  This class implements exception handles using methods 2 and 3 below.
 * <p>
 *  1. AfterThrowing annotation<br>
 *    Catch an exception on a method basis.
 *    Implemented by adding an AfterThrowing annotation to the method.
 *    See MethodLoggingInterceptor class for the implementation.
 *    (Currently invalidated)
 * <p>
 *  2. ExceptionHandler annotation<br>
 *    Catch an exception within the controller class.
 *    Implemented by adding method to ExceptionHandler annotation.
 *    See AppControllerAdvice class for implementation.
 * <p>
 *  3. HandlerExceptionResolver class<br>
 *    Catch an exception throughout the application.
 *    Used by implementing the HandlerExceptionResolver class.
 *    See GlobalExceptionHandler class for implementation.
 */
@Controller
public class ExceptionHandleTestController {
	
	/**
	 * Cause NullPointerException (exception handle test by ExceptionHandler annotation)
	 * @throws NullPointerException NullPointerException
	 */
	@RequestMapping(value = "/exception/null", method = RequestMethod.GET)
	public void nullPointerExTest() throws NullPointerException {
		throw new NullPointerException("NullPointerException");
	}
	
	/**
	 * Cause IllegalArgumentException (exception handle test by HandlerExceptionResolver)
	 * @throws IllegalArgumentException IllegalArgumentException
	 */
	@RequestMapping(value = "/exception/illegal", method = RequestMethod.GET)
	public void illegalArgumentExTest() throws IllegalArgumentException {
		throw new IllegalArgumentException("IllegalArgumentException");
	}
	
}
