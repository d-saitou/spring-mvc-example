package di.app.controller.contents;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import di.domain.service.FileDownloadService;
import util.common.WebUtility;

/**
 * "File download" controller<br>
 * <p>
 *  Character set of response when downloading files are stored in 
 *  application.properties and referenced by Value annotation.
 */
@Controller
public class FileDownloadController {
	
	@Value("${fileDownloadController.charset}")
	private String charset;
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private FileDownloadService svc;
	
	/**
	 * GET request
	 * @param response HttpServletResponse
	 * @param locale   locale
	 * @throws IOException         IOException
	 * @throws FileNotFoundException FileNotFoundException
	 * @throws IOException           IOException
	 */
	@RequestMapping(value = "/file/download", method = RequestMethod.GET)
	public void downloadGet(HttpServletResponse response, Locale locale)
			throws FileNotFoundException, IOException {
		
		// Create download file
		String excelPath = svc.generateExcelFullPath();
		String message = null;
		if (!svc.txCreateTaskExcel(excelPath, locale)) {
			message = msg.getMessage("FileDownloadController.msg.failed", null, locale);
			WebUtility.setAlertWindowResponse(response, message, this.charset);
			return;
		}
		// Set Download response
		WebUtility.setFileDownloadResponse(response, excelPath, svc.getExcelFileName(), this.charset);
		// Delete download file
		svc.deleteExcel(excelPath);
		return;
	}
	
}
