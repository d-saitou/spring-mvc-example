package di.app.controller.contents;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.form.TaskForm;
import di.domain.service.DbCrudService;
import domain.entity.T_Task;
import lombok.extern.slf4j.Slf4j;
import util.common.StringUtility;

/**
 * "DB-CRUD (select/update/delete)" screen controller
 */
@Controller @Slf4j
public class DbSelectAllController {
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param message request parameter (message)
	 * @param model   Model
	 * @param locale  locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/select", method = RequestMethod.GET)
	public String dbSelectAllGet(
			@ModelAttribute("message") String message, Model model, Locale locale) {
		// Set message
		if (StringUtility.isNotEmpty(message)) {
			model.addAttribute("message", message);
		}
		// Set form
		List<TaskForm> tasklist = new LinkedList<TaskForm>();
		try {
			for (T_Task e : svc.txGetTaskAll()) {
				tasklist.add(svc.convertEntityToForm(e));
			}
			model.addAttribute("tasklist", tasklist);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		
		return "contents/DbSelectAll";
	}
	
}
