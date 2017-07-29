package di.app.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * login screen controller
 */
@Controller
public class LoginController {
	
	@Autowired
	private MessageSource msg;
	
	/**
	 * GET request
	 * @param error  request parameter (error) [option]
	 * @param model  Model
	 * @param locale Locale
	 * @return URL
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam Optional<String> error, Model model, Locale locale) {
		
		if (error.isPresent()) {
			switch (error.get()) {		// Switch statements with String cases (since Java 7)
			case "auth-failure":
				model.addAttribute("error", msg.getMessage("login.msg.auth-failure", null, locale));
				break;
			case "without-auth":
				model.addAttribute("error", msg.getMessage("login.msg.without-auth", null, locale));
				break;
			}
		}
		return "login";
	}
	
}
