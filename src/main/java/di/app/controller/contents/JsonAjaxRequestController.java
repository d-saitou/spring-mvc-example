package di.app.controller.contents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * "REST-API (JSON) Ajax request" screen controller
 */
@Controller
public class JsonAjaxRequestController {
	
	/**
	 * GET request
	 * @return URL
	 */
	@RequestMapping(value = "/rest/jsonajaxrequest", method = RequestMethod.GET)
	public String jsonAjaxResultGet() {
		return "contents/JsonAjaxRequest";
	}
	
}
