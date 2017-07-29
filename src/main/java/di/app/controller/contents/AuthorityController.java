package di.app.controller.contents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * "Authority control" screens controller<br>
 * <p>
 *  According to the authority got by the UserDetailsServiceImpl class, the 
 *  following processing is performed on the screen opened by this class.
 * <p>
 *  "Reference authority (admin user only)" screen: <br>
 *    According to Spring Security configulation, only admin user can see 
 *    the screen. 
 *    Refer to security-config.xml for configulation.
 * <p>
 *  "Contents by authority" screen: <br>
 *    JSP switches contents of screen according to authority.
 */
@Controller
public class AuthorityController {
	
	/**
	 * "Reference authority (admin user only)" screen GET request
	 * @return URL
	 */
	@RequestMapping(value = "/authority/reference", method = RequestMethod.GET)
	public String referenceAuthorityGet() {
		return "contents/ReferenceAuthority";
	}
	
	/**
	 * "Contents by authority" screen GET request
	 * @return URL
	 */
	@RequestMapping(value = "/authority/contentsbyauth", method = RequestMethod.GET)
	public String contentsByAuthorityGet() {
		return "contents/ContentsByAuthority";
	}
	
}
