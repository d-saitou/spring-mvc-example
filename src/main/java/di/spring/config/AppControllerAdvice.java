package di.spring.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller advice<br>
 * <p>
 *  This class implements controller advice using the ControllerAdvice 
 *  annotation.
 * <p>
 *  WebDataBinder#setValidator():<br>
 *    In this application, set Validator Factory in WebDataBinder so 
 *    that validator refers to spring's message resource.
 *    For the bean definition of Validator Factory, refer to 
 *    "Validator Factory" comment part in application-config.xml.
 * <p>
 *  WebDataBinder#setAutoGrowCollectionLimit():<br>
 *    In Spring MVC, the maximum number of list items (form object) 
 *    received by controller is 256 by default.
 *    Therefore, use the InitBinder annotation and WebDataBinder class 
 *    to change the maximum number of list items.
 *    Set the maximum number in List to application.properties and get 
 *    it by Value annotation.
 * <p>
 *  ExceptionHandler annotation:<br>
 *    Error handling method executed when an exception occurs in the 
 *    controller classes.
 *    Implement it by adding ExceptionHandler annotation to the method.
 */
@ControllerAdvice @Slf4j
public class AppControllerAdvice {
	
	@Value("${appControllerAdvice.autoGrowCollectionLimit}")
	private int autoGrowCollectionLimit;
	
	@Autowired
	private Validator validator;
	
	/**
	 * Change the maximum number of list items (form object)
	 * @param binder WebDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		// Set validator for referencing spring's message resources
		binder.setValidator(validator);
		
		// Set the maximum number of list items (form object) received by controller
		binder.setAutoGrowCollectionLimit(this.autoGrowCollectionLimit);
	}
	
	/**
	 * Handle NullPointerException by ExceptionHandler annotation
	 * @param attr   RedirectAttributes
	 * @param e      Exception
	 * @param locale locale
	 * @return URL
	 */
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(
			RedirectAttributes attr, Exception e, Locale locale) {
		log.error("Exception occurred.\r\n", e);
		return "redirect:/views/common/error.jsp";
	}
	
}
