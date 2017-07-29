package di.app.controller.contents;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * "Get request parameters" screen controller
 */
@Controller
public class QueryParameterController {
	
	/**
	 * GET request
	 * @param param1  request parameter (param1)
	 * @param param3  path variable (param3)
	 * @param param4  path variable (param4)
	 * @param param5  request parameter (param5) [option]
	 * @param model   Model
	 * @param request HttpServletRequest
	 * @return URL
	 */
	@RequestMapping(value = "/queryparam/{param3}/{param4}", method = RequestMethod.GET)
	public String queryParameterGet(
			@RequestParam("param1") String param1,	// get by @RequestParam
			@PathVariable String param3,			// get by @PathVariable (String)
			@PathVariable int param4,				// get by @PathVariable (int)
			@RequestParam Optional<String> param5,	// get by @RequestParam [option]
			Model model, HttpServletRequest request) {
		
		// Set form
		model.addAttribute("param1", param1);
		model.addAttribute("param2", request.getParameter("param2"));	// get by getParameter()
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		if (param5.isPresent()) {
			model.addAttribute("param5", param5.get());		// unused
		}
		return "contents/QueryParameter";
	}
	
}
