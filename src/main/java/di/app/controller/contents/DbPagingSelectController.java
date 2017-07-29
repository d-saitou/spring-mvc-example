package di.app.controller.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.form.TaskForm;
import app.form.TaskPageDto;
import di.domain.service.DbCrudService;
import di.spring.auth.UserDetailsImpl;
import domain.entity.T_Task;
import lombok.extern.slf4j.Slf4j;

/**
 * "DB paging select" screen controller<br>
 * <p>
 *  Batch size used by "DB-CRUD (batch-update)" screen are stored in 
 *  application.properties and referenced by Value annotation.
 * <p>
 *  By specifying a Pageable object as a controller method argument, 
 *  Pageable objects can be generated from request parameters.
 *  However, when using the above means, the page start number is "0" 
 *  instead of "1".
 *  For this reason, this class does not make use of getting Pageable 
 *  objects from method arguments.
 * <p>
 *  To get Pageable object from method argument, it is necessary to set 
 *  Spring config.<br>
 *  * See "Get paging information" part in application-config.xml.
 */
@Controller @Slf4j
public class DbPagingSelectController {
	
	@Value("${dbPagingSelect.size}")
	private int pageSize;
	
	@Autowired
	private DbCrudService svc;
	
	/**
	 * GET request
	 * @param requestPage request page number
	 * @param model       Model
	 * @param locale      locale
	 * @param userDetails UserDetailsImpl
	 * @return URL
	 */
	@RequestMapping(value = "/db/pagination", method = RequestMethod.GET )
	public String dbPagingSelectGet(
			@RequestParam("page") int requestPage, Model model, Locale locale,
			// Pageable pageable,	// Invalid in the current specification
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		
		// Generate paging request
		PageRequest pageable = null;
		pageable = new PageRequest(
				requestPage - 1, this.pageSize, Direction.ASC, new String[] { "id" });
		// Set form
		Page<T_Task> page = null;
		TaskPageDto taskpage = new TaskPageDto();
		List<TaskForm> formlist = new ArrayList<TaskForm>();
		try {
			// Get page data
			page = svc.txGetPaginationByUserid(userDetails.getUserid(), pageable);
			for (T_Task task : page.getContent()) {
				formlist.add(svc.convertEntityToForm(task));
			}
			// Set paging information to form
			taskpage.setTasklist(formlist);
			taskpage.setPageSize(this.pageSize);
			taskpage.setTotalPages(page.getTotalPages());
			taskpage.setCurrentPage(requestPage);
			taskpage.setHasNextPage(page.hasNext());
			taskpage.setHasPreviousPage(page.hasPrevious());
			model.addAttribute("taskpage", taskpage);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.");
			throw e;	// Exceptions are handled by GlobalExceptionHandler class.
		}
		return "contents/DbPagingSelect";
	}
	
}
