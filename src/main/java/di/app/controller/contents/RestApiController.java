package di.app.controller.contents;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.form.TaskForm;
import di.domain.service.DbCrudService;
import domain.entity.T_Task;
import lombok.extern.slf4j.Slf4j;

/**
 * REST-API controller<br>
 * <p>
 *  This class implements REST-API of plain-text and XML and JSON data.
 * <p>
 *  When returning JSON data, Spring MVC uses the JSON library (Jackson) to 
 *  convert the method return value to JSON data and return the response.
 *  In this application, the setting for automatic indentation of JSON data 
 *  is set to Spring setting.
 * <p>
 *  * For JSON data automatic indent setting, see the "REST API" comment 
 *    part in application-config.xml.
 */
@RestController @Slf4j
public class RestApiController {
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * REST-API (text)
	 * @return plain text
	 */
	@RequestMapping(value = "/rest/text", produces = "text/plain")
	public String plaintextApi() {
		return "SampleText";
	}
	
	/**
	 * REST-API (XML)
	 * @return xml text
	 */
	@RequestMapping(value = "/rest/xml", produces = "application/xml")
	public String xmlApi() {
		return "<tag1><tag2>SampleParamater</tag2></tag1>";
	}
	
	/**
	 * REST-API (JSON)
	 * @param response HttpServletResponse
	 * @return json data
	 */
	@RequestMapping(value = "/rest/json", produces = "application/json")
	public List<TaskForm> jsonApi(HttpServletResponse response) {
		List<TaskForm> tasklist = new LinkedList<TaskForm>();
		try {
			// Get task data
			for (T_Task e : svc.txGetTaskAll()) {
				tasklist.add(svc.convertEntityToForm(e));
			}
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		return tasklist;
	}
	
}
