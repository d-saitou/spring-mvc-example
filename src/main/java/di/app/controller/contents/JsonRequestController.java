package di.app.controller.contents;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.form.TaskForm;
import util.common.WebUtility;

/**
 * "REST-API (JSON) request" screen controller
 */
@Controller
public class JsonRequestController {
	
	/**
	 * GET request
	 * @param model   Model
	 * @param request HttpServletRequest
	 * @return URL
	 * @throws JsonParseException   JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException          IOException
	 */
	@RequestMapping(value = "/rest/jsonrequest", method = RequestMethod.GET)
	public String jsonResultGet(Model model, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		// Generate URL
		String apiUrl = request.getRequestURL().toString().replaceFirst("jsonrequest$", "json");
		// Get request result
		String response = WebUtility.getHttpGetRequestResult(apiUrl, StandardCharsets.UTF_8);
		// Convert json string to list
		List<TaskForm> tasklist = convertTaskList(response);
		model.addAttribute("tasklist", tasklist);
		return "contents/JsonRequest";
	}
	
	/**
	 * Convert json string to list
	 * @param json json string
	 * @return list data
	 * @throws JsonParseException   JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException          IOException
	 */
	private List<TaskForm> convertTaskList(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<TaskForm> list = mapper.readValue(json, new TypeReference<List<TaskForm>>() {});
		return list;
	}
	
}
