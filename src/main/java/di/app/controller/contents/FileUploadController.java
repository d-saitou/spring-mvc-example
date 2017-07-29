package di.app.controller.contents;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import app.form.MultiFileUploadForm;
import app.form.SingleFileUploadForm;
import di.domain.dao.MultipartFileDao;
import lombok.extern.slf4j.Slf4j;

/**
 * "File upload" screen controller
 */
@Controller @Slf4j
public class FileUploadController {
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private MultipartFileDao fileDao;
	
	/**
	 * GET request
	 * @return URL
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.GET )
	public String fileUploadGet() {
		return "contents/FileUpload";
	}
	
	/**
	 * POST request (single file)
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @param locale locale
	 * @return URL
	 * @throws IllegalStateException IllegalStateException
	 * @throws IOException           IOException
	 */
	@RequestMapping(value = "/file/upload/single", method = RequestMethod.POST )
	public String fileUploadSingleFilePost(
			@ModelAttribute("singleFileUploadForm") SingleFileUploadForm form,
			BindingResult result, Model model, Locale locale) 
			throws IllegalStateException, IOException {
		
		MultipartFile file = form.getFile();
		String message = null;
		if (result.hasErrors()) {
			message = msg.getMessage("FileUpload.msg.failed", null, locale);
		} else if (null != file) {
			// Save upload file
			try {
				this.fileDao.save(file);
				message = msg.getMessage("FileUpload.msg.success", null, locale);
			} catch (IllegalStateException e) {
				log.error("An exception occurred when saving file.");
				throw e;	// Exceptions are handled by GlobalExceptionHandler class.
			} catch (IOException e) {
				log.error("An exception occurred when saving file.");
				throw e;	// Exceptions are handled by GlobalExceptionHandler class.
			}
		} else {
			message = msg.getMessage("FileUpload.msg.notselect", null, locale);
		}
		model.addAttribute("message", message);
		return "contents/FileUpload";
	}
	
	/**
	 * POST request (multiple file)
	 * @param form   form
	 * @param result BindingResult
	 * @param model  Model
	 * @param locale locale
	 * @return URL
	 * @throws IllegalStateException IllegalStateException
	 * @throws IOException           IOException
	 */
	@RequestMapping(value = "/file/upload/multi", method = RequestMethod.POST)
	public String fileUploadMultiFilesPost(
			@ModelAttribute("multiFileUploadForm") MultiFileUploadForm form,
			BindingResult result, Model model, Locale locale) 
			throws IllegalStateException, IOException {
		
		List<MultipartFile> files = form.getFiles();
		String message = null;
		if (result.hasErrors()) {
			message = msg.getMessage("FileUpload.msg.failed", null, locale);
		} else if ((null != files) && (files.size() > 0)) {
			// Save upload file
			for (MultipartFile file : files) {
				try {
					this.fileDao.save(file);
				} catch (IllegalStateException e) {
					log.error("An exception occurred when saving file.");
					throw e;	// Exceptions are handled by GlobalExceptionHandler class.
				} catch (IOException e) {
					log.error("An exception occurred when saving file.");
					throw e;	// Exceptions are handled by GlobalExceptionHandler class.
				}
			}
			message = msg.getMessage("FileUpload.msg.success", null, locale);
		} else {
			message = msg.getMessage("FileUpload.msg.notselect", null, locale);
		}
		model.addAttribute("message", message);
		return "contents/FileUpload";
	}
	
}
