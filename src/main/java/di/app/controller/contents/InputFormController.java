package di.app.controller.contents;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.form.InputForm;
import util.common.DateUtility;

/**
 * "Input form" screen controller<br>
 * <p>
 *  This class gets the input form value from the screen with each pattern 
 *  using / not using the form class.
 *  It is common to get values using form classes.
 */
@Controller
public class InputFormController {
	
	@Autowired
	private MessageSource msg;
	
	/**
	 * "Input form (not use form class)" screen GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/form/input/nonformobj", method = RequestMethod.GET)
	public String formInputNonFormObjGet(Model model) {
		model.addAttribute("hidden", "sample");
		model.addAttribute("sampleItems", getSampleItems());
		return "contents/InputFormNotUseFormObj";
	}
	
	/**
	 * "Input form (not use form class)" screen POST request
	 * @param testParam request parameter (inputstr)
	 * @param textarea  request parameter (textarea)
	 * @param request   HttpServletRequest
	 * @param model     Model
	 * @param locale    locale
	 * @return URL
	 * @throws ParseException ParseException
	 */
	@RequestMapping(value = "/form/input/nonformobj", method = RequestMethod.POST)
	public String formInputNonFormObjPost(
			@RequestParam("inputstr") String testParam,	// get by @RequestParam (specify a name)
			@RequestParam String textarea,				// get by @RequestParam (do not specify a name)
			HttpServletRequest request, 				// get by getParameter()
			Model model, Locale locale) throws ParseException {
		
		// Set form
		Date inputdate = null;
		try {
			inputdate = DateUtility.parseDate(
					request.getParameter("inputdate"),
					msg.getMessage("contents.format.date", null, locale));
		} catch (ParseException e) {
//			throw e;
		}
		model.addAttribute("inputstr", testParam);
		model.addAttribute("inputint", request.getParameter("inputint"));
		model.addAttribute("inputdate", inputdate);
		model.addAttribute("password", request.getParameter("password"));
		model.addAttribute("textarea", textarea);
		model.addAttribute("radiobutton", request.getParameter("radiobutton"));
		model.addAttribute("radiobuttons", request.getParameter("radiobuttons"));
		model.addAttribute("checkbox", request.getParameter("checkbox"));
		model.addAttribute("checkboxbool", request.getParameter("checkboxbool"));
		model.addAttribute("checkboxes", request.getParameterValues("checkboxes"));
		model.addAttribute("select", request.getParameterValues("select"));
		model.addAttribute("selectoption", request.getParameter("selectoption"));
		model.addAttribute("hidden", request.getParameter("hidden"));
		model.addAttribute("postFlg", Boolean.valueOf(true));
		return "contents/InputFormNotUseFormObj";
	}
	
	/**
	 * "Input form (use form class)" screen GET request
	 * @param model Model
	 * @return URL
	 */
	@RequestMapping(value = "/form/input/useformobj", method = RequestMethod.GET)
	public String formInputUseFormObjGet(Model model) {
		InputForm form = new InputForm();
		form.setHidden("sample");
		model.addAttribute("inputForm", form);
		model.addAttribute("sampleItems", getSampleItems());
		return "contents/InputFormUseFormObj";
	}
	
	/**
	 * "Input form (use form class)" screen POST request
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @return URL
	 */
	@RequestMapping(value = "/form/input/useformobj", method = RequestMethod.POST)
	public String formInputUseFormObjPost(
			@Valid @ModelAttribute InputForm form,
			BindingResult result, Model model) {
		
		// Set form
		InputForm newForm = new InputForm();
		newForm.setHidden("sample");
		model.addAttribute("inputForm", newForm);
		model.addAttribute("resultForm", form);
		model.addAttribute("sampleItems", getSampleItems());
		return "contents/InputFormUseFormObj";
	}
	
	/**
	 * Generate selection list of select box
	 * @return selection list
	 */
	private List<String> getSampleItems() {
		List<String> list = new LinkedList<String>();
		list.add("sample1");
		list.add("sample2");
		return list;
	}
	
}
