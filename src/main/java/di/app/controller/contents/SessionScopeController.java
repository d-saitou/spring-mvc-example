package di.app.controller.contents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import di.domain.component.SessionScopeComponent;

/**
 * "Session scope bean" screen controller<br>
 * <p>
 *  This class implements a controller of the screen which passes value 
 *  between web screens using session scope bean.
 */
@Controller
public class SessionScopeController {
	
	@Autowired
	private SessionScopeComponent component;
	
	/**
	 * "Session scope bean (set)" screen GET request
	 * @return URL
	 */
	@RequestMapping(value = "/scope/session/set", method = RequestMethod.GET)
	public String sessionScopeSetGet() {
		return "contents/SessionScopeSet";
	}
	
	/**
	 * "Session scope bean (set)" screen POST request
	 * @param message request parameter (message)
	 * @return URL
	 */
	@RequestMapping(value = "/scope/session/set", method = RequestMethod.POST)
	public String sessionScopeSetPost(@RequestParam("message") String message) {
		this.component.setMessage(message);
		return "redirect:/scope/session/ref";
	}
	
	/**
	 * "Session scope bean (refer)" screen GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/scope/session/ref", method = RequestMethod.GET)
	public String sessionScopeRefGet(Model model) {
		model.addAttribute("message", this.component.getMessage());
		return "contents/SessionScopeRef";
	}
	
}
