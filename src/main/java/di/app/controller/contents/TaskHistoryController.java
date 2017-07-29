package di.app.controller.contents;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.form.TaskHistoryDto;
import di.domain.service.TaskHistoryService;
import domain.entity.T_Scheduledtask_History;

/**
 * "Scheduled task history" screen controller
 */
@Controller
public class TaskHistoryController {
	
	@Autowired
	private TaskHistoryService svc;
	
	/**
	 * GET request
	 * @param model Model
	 * @return URL
	 * @throws Exception Exception
	 */
	@RequestMapping(value = "/task/history", method = RequestMethod.GET)
	public String taskHistoryGet(Model model) throws Exception {
		// Get task history
		List<TaskHistoryDto> list = new LinkedList<TaskHistoryDto>();
		for (T_Scheduledtask_History task : svc.txGetTaskHistoryOrderByIdDesc()) {
			list.add(svc.convertEntityToForm(task));
		}
		model.addAttribute("taskHistoryList", list);
		return "contents/TaskHistory";
	}
	
}
