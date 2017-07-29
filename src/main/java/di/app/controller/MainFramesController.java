package di.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * main and menu frames controller
 */
@Controller
public class MainFramesController {
	
	@Value("${dbPagingSelect.size}")
	int pageSize;
	
	/**
	 * Main frame GET request
	 * @return URL
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainFrameGet() {
		return "main";
	}
	
	/**
	 * Menu frame GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menuFrameGet(Model model) {
		model.addAttribute("pageSize", Integer.valueOf(this.pageSize));
		return "menu";
	}
	
}
