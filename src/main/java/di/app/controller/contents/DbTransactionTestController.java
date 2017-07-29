package di.app.controller.contents;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import di.domain.service.DbCrudService;
import lombok.extern.slf4j.Slf4j;

/**
 * "DB transaction test" controller
 */
@Controller @Slf4j
public class DbTransactionTestController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param attr   RedirectAttributes
	 * @param locale locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/transaction", method = RequestMethod.GET)
	public String dbTransactionTestGet(RedirectAttributes attr, Locale locale) {
		String message = null;
		try {
			// Intentionally cause an Exception in txTransactionTest() method
			svc.txTransactionTest();
			message = msg.getMessage("DbTransactionTest.msg.succeeded", null, locale);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.", e);
			message = msg.getMessage("DbTransactionTest.msg.failed", null, locale);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:/db/select";
	}
	
}
