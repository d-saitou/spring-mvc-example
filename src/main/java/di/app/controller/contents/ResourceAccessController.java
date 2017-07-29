package di.app.controller.contents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * "Resource import (JavaScript)" screen controller<br>
 * <p>
 *  When using static resources (images, JavaScript, CSS, Webjars, etc) 
 *  in Spring MVC, set access target mapping and security exemption 
 *  configulation to Spring configulation.
 * <p>
 *  Webjars is a mechanism to jar file client side libraries such as 
 *  CSS and JavaScript and manage the dependency of each library by 
 *  Maven (Gradle).
 *  Spring MVC directly accesses the files in each Webjars by mapping 
 *  specific paths in the jar file to Spring MVC.
 * <p>
 *  * For the mapping configulation to static resources, see 
 *   "Static resource reference" comment part in mvc-config.xml.
 * <p>
 *  * For security exclusion configulation, see "Non-security config" 
 *    comment part in security-config.xml.
 */
@Controller
public class ResourceAccessController {
	
	/**
	 * "Resource import (JavaScript)" screen GET request
	 * @return URL
	 */
	@RequestMapping(value = "/resource/js", method = RequestMethod.GET)
	public String resourceJsAccessGet() {
		return "contents/ResourceJsAccess";
	}
	
	/**
	 * "Resource import (Webjars)" screen GET request
	 * @return URL
	 */
	@RequestMapping(value = "/resource/webjars", method = RequestMethod.GET)
	public String resourceWebjarsAccessGet() {
		return "contents/ResourceWebjarsAccess";
	}
	
}
