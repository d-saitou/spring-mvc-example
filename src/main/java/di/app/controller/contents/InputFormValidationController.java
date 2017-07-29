package di.app.controller.contents;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.form.EmployeeForm;

/**
 * "Input form validation" screen controller<br>
 * <p>
 *  This class implements input validation of form objects using JSR-303 
 *  Bean Validation and Hibernate Validator.
 */
@Controller
public class InputFormValidationController {
	
	/**
	 * GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/form/validation", method = RequestMethod.GET)
	public String formValidationGet(Model model) {
		// Set form
		EmployeeForm form = new EmployeeForm();
		model.addAttribute("employeeForm", form);
		return "contents/InputFormValidation";
	}
	
	/**
	 * POST request
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @return URL
	 */
	@RequestMapping(value = "/form/validation", method = RequestMethod.POST)
	public String formValidationPost(
			@Valid @ModelAttribute EmployeeForm form,	// Apply validation with Valid annotation
			BindingResult result, Model model) {
		
		// Set form
		if (result.hasErrors()) {
			model.addAttribute("employeeForm", form);
		} else {
			model.addAttribute("employeeForm", new EmployeeForm());
			model.addAttribute("resultForm", form);
		}
		return "contents/InputFormValidation";
	}
	
}
