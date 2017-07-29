package di.app.controller.contents;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import di.domain.service.DbCrudService;
import lombok.extern.slf4j.Slf4j;

/**
 * "DB-CRUD (delete)" controller
 */
@Controller @Slf4j
public class DbDeleteController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param id     task ID
	 * @param attr   RedirectAttributes
	 * @param locale Locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/delete/{id}", method = RequestMethod.GET )
	public String dbDeleteGet(
			@PathVariable int id, RedirectAttributes attr, Locale locale) {
		String message = null;
		try {
			// Delete task
			svc.txDeleteTaskById(id);
			message = msg.getMessage("DbDelete.msg.succeeded", null, locale);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		attr.addFlashAttribute("message", message);
		return "redirect:/db/select";
	}
	
}
