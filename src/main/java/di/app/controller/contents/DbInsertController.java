package di.app.controller.contents;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.form.TaskForm;
import di.domain.service.DbCrudService;
import di.spring.auth.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * "DB-CRUD (insert)" screen controller
 */
@Controller @Slf4j
public class DbInsertController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/db/insert", method = RequestMethod.GET)
	public String dbInsertGet(Model model) {
		// Set form
		TaskForm form = new TaskForm();
		model.addAttribute("taskForm", form);
		return "contents/DbInsert";
	}
	
	/**
	 * POST request
	 * @param form        form
	 * @param result      BindingResult
	 * @param userDetails UserDetailsImpl
	 * @param model       Model
	 * @param attr        RedirectAttributes
	 * @param locale      locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/insert", method = RequestMethod.POST)
	public String dbInsertPost(
			@Valid @ModelAttribute TaskForm form, BindingResult result,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Model model,
			RedirectAttributes attr, Locale locale) {
		
		String message = null;
		if (result.hasErrors()) {
			model.addAttribute("taskForm", form);
			message = msg.getMessage("DbInsert.msg.error", null, locale);
			model.addAttribute("message", message);
			return "contents/DbInsert";
		}
		try {
			// Insert task
			svc.txInsertTask(svc.convertFormToEntity(form), userDetails.getUserid());
			message = msg.getMessage("DbInsert.msg.succeeded", null, locale);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		attr.addFlashAttribute("message", message);
		return "redirect:/db/select";
	}
	
}
