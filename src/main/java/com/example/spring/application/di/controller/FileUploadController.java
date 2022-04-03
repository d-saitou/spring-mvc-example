package com.example.spring.application.di.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.application.di.validator.FileUploadFormValidator;
import com.example.spring.application.form.FileUploadForm;
import com.example.spring.domain.di.service.MultipartFileManageService;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * File upload screen controller.
 */
@Controller
@RequiredArgsConstructor
public class FileUploadController {

	private final FileUploadFormValidator validator;

	private final MultipartFileManageService service;

	/**
	 * Add validator for FileUploadForm.
	 * @param binder WebDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	/**
	 * GET request.
	 * @param model Model.
	 * @return HTML template path.
	 */
	@GetMapping("/file/upload")
	public String get(Model model) {
		model.addAttribute("singleFileUploadForm", new FileUploadForm());
		model.addAttribute("multipleFileUploadForm", new FileUploadForm());
		return "content/FileUpload";
	}

	/**
	 * POST request for single file (*Get a file from request parameters).
	 * @param file   MultipartFile object.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @return HTML template path.
	 * @throws IOException
	 */
	@PostMapping("/file/upload/single")
	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
	public String postSingleFile(
			@RequestParam("file") MultipartFile file, Model model, Locale locale)
			throws IOException {
		if (file == null || file.getOriginalFilename().isEmpty()) {
			model.addAttribute("isSingleFileUploadError", true);
		} else {
			MultipartFile[] files = { file };
			List<String> fileList = service.saveMultipartFiles(files);
			model.addAttribute("filePath", fileList.get(0));
		}
		model.addAttribute("singleFileUploadForm", new FileUploadForm());
		model.addAttribute("multipleFileUploadForm", new FileUploadForm());
		return "content/FileUpload";
	}

	/**
	 * POST request for multiple files (*Get the files using a form class).
	 * @param form   FileUploadForm.
	 * @param result BindingResult object.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @return HTML template path.
	 * @throws IOException
	 */
	@PostMapping("/file/upload/multiple")
	public String postMultipleFiles(
			@Validated @ModelAttribute("multipleFileUploadForm") FileUploadForm form,
			BindingResult result, Model model, Locale locale) throws IOException {
		if (!result.hasErrors()) {
			List<String> fileList = service.saveMultipartFiles(form.getFile());
			model.addAttribute("fileList", fileList);
			model.addAttribute("multipleFileUploadForm", new FileUploadForm());
		}
		model.addAttribute("singleFileUploadForm", new FileUploadForm());
		return "content/FileUpload";
	}

}
