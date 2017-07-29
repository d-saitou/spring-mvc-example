package di.app.controller.contents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.form.EmployeeForm;
import app.form.EmployeeListForm;
import util.common.DateUtility;

/**
 * "Array input form validation" screen controller<br>
 * <p>
 *  This class implements input validation of form objects using JSR-303 
 *  Bean Validation and Hibernate Validator.
 * <p>
 *  In Spring MVC, the maximum number of list items (form object) 
 *  received by controller is 256 by default.
 *  Therefore, use the InitBinder annotation and WebDataBinder class to 
 *  change the maximum number of list items.<br>
 *  * See AppControllerAdvice class.
 */
@Controller
public class ArrayInputFormValidationController {
	
	// Default maximum number of list items (form object) + 1
	private static final int MAX_ARRAY_LENGTH = 257;
	
	@Autowired
	private MessageSource msg;
	
	/**
	 * GET request
	 * @param model Model
	 * @param locale locale
	 * @return URL
	 */
	@RequestMapping(value = { "/form/array" }, method = RequestMethod.GET)
	public String arrayInputFormValidationGet(Model model, Locale locale) {
		// Set form
		EmployeeListForm form = new EmployeeListForm();
		form.setEmployeelist(getDefaultParamsList(locale));
		model.addAttribute("employeeListForm", form);
		return "contents/ArrayInputFormValidation";
	}
	
	/**
	 * POST request
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @param locale locale
	 * @return URL
	 */
	@RequestMapping(value = { "/form/array" }, method = RequestMethod.POST)
	public String arrayInputFormValidationPost(
			@Validated @ModelAttribute EmployeeListForm form,	// Apply validation with Validated annotation
			BindingResult result, Model model, Locale locale) {
		
		// Set message
		if (result.hasErrors()) {
			model.addAttribute(
					"message",
					msg.getMessage("ArrayInputFormValidation.msg.failed", null, locale));
		}
		// Set form
		model.addAttribute("employeeListForm", form);
		return "contents/ArrayInputFormValidation";
	}
	
	/**
	 * Generate form objects and initial value setting
	 * @param locale locale
	 * @return form list
	 */
	private List<EmployeeForm> getDefaultParamsList(Locale locale) {
		List<EmployeeForm> list = new ArrayList<EmployeeForm>();
		String format = msg.getMessage("contents.format.date", null, locale);
		Date now = new Date();
		for (int i = 1; i <= MAX_ARRAY_LENGTH; i++) {
			EmployeeForm form = new EmployeeForm();
			form.setName("sample" + i);
			form.setGender("MALE");
			form.setBirthday(now);
			form.setAge(Integer.valueOf(18));
			form.setPhone("08099990000");
			form.setEmail("test" + i + "@sample.com");
			form.setCredit(DateUtility.parseString(new Date(), format));
			list.add(form);
		}
		return list;
	}
	
}
