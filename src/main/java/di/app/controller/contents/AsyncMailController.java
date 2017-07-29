package di.app.controller.contents;

import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.form.MailForm;
import di.domain.service.AsyncMailService;

/**
 * "Asynchronous (send mail)" screen controller
 */
@Controller
public class AsyncMailController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private AsyncMailService svc;
	
	/**
	 * GET request
	 * @param model   Model
	 * @param success mail send result [option]
	 * @param locale  Locale
	 * @return request URL
	 */
	@RequestMapping(value = "/async/mail", method = RequestMethod.GET)
	public String asyncMailGet(
			Model model, @RequestParam Optional<Boolean> success, Locale locale) {
		// Set form
		model.addAttribute(
				"mailForm",
				new MailForm().setFrom(svc.getFromAddr()).setTo(svc.getToAddr()));
		// Set message
		String message = null;
		if (success.isPresent()) {
			if (success.get().booleanValue()) {
				message = msg.getMessage("AsyncMail.msg.success", null, locale);
			} else {
				message = msg.getMessage("AsyncMail.msg.failed", null, locale);
			}
			model.addAttribute("message", message);
		}
		return "contents/AsyncMail";
	}
	
	/**
	 * POST request
	 * @param form   MailForm
	 * @param model  Model
	 * @param locale Locale
	 * @return request URL
	 */
	@RequestMapping(value = "/async/mail", method = RequestMethod.POST)
	public CompletableFuture<String> asyncMailPost(
			@ModelAttribute MailForm form, Model model, Locale locale) {
		// Set form
		model.addAttribute(
				"mailForm",
				new MailForm().setFrom(svc.getFromAddr()).setTo(svc.getToAddr()));
		// Send email
		svc.setSubject(form.getSubject());
		svc.setMessage(form.getMessage());
		return svc.sendMail(
				"redirect:/async/mail?success=true", "redirect:/async/mail?success=false");
	}
	
}
