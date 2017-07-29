package di.spring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * Aapplication exception handler<br>
 * <p>
 *  This class implements handling in the entire application when an 
 *  exception occurs. 
 *  Implement interface HandlerExceptionResolver.
 * <p>
 *  In order to use this class, it is necessary to set bean definition 
 *  in the Spring configuration.<br>
 *  * See "applicationExceptionHandler" in application-config.xml.
 */
@Slf4j
public class GlobalExceptionHandler implements HandlerExceptionResolver {
	
	/**
	 * Exception handler
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @param handler  object
	 * @param e        Exception
	 * @return ModelAndView
	 */
	public ModelAndView resolveException(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		log.error("Exception occurred.\r\n", e);
		// Set response status
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		// Set ModelAndView
		ModelAndView model = new ModelAndView();
		model.setViewName("/common/error");
		return model;
	}
	
}
