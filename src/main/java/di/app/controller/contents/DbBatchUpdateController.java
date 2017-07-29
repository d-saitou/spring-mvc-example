package di.app.controller.contents;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.form.TaskForm;
import app.form.TaskListForm;
import di.domain.service.DbCrudService;
import domain.entity.T_Task;
import lombok.extern.slf4j.Slf4j;

/**
 * "DB-CRUD (batch-update)" screen screen controller
 */
@Controller @Slf4j
public class DbBatchUpdateController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param model  Model
	 * @param locale Locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/batchupdate", method = RequestMethod.GET)
	public String dbBatchUpdateGet(Model model, Locale locale) {
		TaskListForm form = new TaskListForm();
		List<TaskForm> tasklist = new LinkedList<TaskForm>();
		// Set form
		try {
			for (T_Task entity : svc.txGetTaskAll()) {
				tasklist.add(svc.convertEntityToForm(entity));
			}
			form.setTasklist(tasklist);
			model.addAttribute("taskListForm", form);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		return "contents/DbBatchUpdate";
	}
	
	/**
	 * POST request
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @param locale Locale
	 * @return URL
	 */
	@RequestMapping(value = "/db/batchupdate", method = RequestMethod.POST)
	public String dbBatchUpdatePost(
			@Validated @ModelAttribute TaskListForm form,
			BindingResult result, Model model, Locale locale) {
		
		String message = null;
		if (result.hasErrors()) {
			model.addAttribute("taskListForm", form);
			message = msg.getMessage("DbBatchUpdate.msg.error", null, locale);
		} else {
			List<T_Task> entities = new LinkedList<T_Task>();
			List<TaskForm> forms = new LinkedList<TaskForm>();
			try {
				// Batch update
				for (TaskForm f : form.getTasklist()) {
					entities.add(svc.convertFormToEntity(f));
				}
				svc.txBatchUpdateTaskById(entities);
				message = msg.getMessage("DbBatchUpdate.msg.succeeded", null, locale);
				// Set form
				for (T_Task e : svc.txGetTaskAll()) {
					forms.add(svc.convertEntityToForm(e));
				}
				model.addAttribute("taskListForm", new TaskListForm().setTasklist(forms));
			} catch (Exception e) {
				log.error("An exception occurred during DB access.");
				throw e;	// Exceptions are handled by GlobalExceptionHandler class.
			}
		}
		model.addAttribute("message", message);
		return "contents/DbBatchUpdate";
	}
	
}
