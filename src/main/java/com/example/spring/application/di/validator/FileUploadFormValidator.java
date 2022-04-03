package com.example.spring.application.di.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.application.form.FileUploadForm;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Custom validator for file upload screen.
 */
@Component
public class FileUploadFormValidator implements Validator {

	/**
	 * Validate supported classes.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		// return FileUploadForm.class.equals(clazz);
		return FileUploadForm.class.isAssignableFrom(clazz);
	}

	/**
	 * Validate FileUploadForm instances.
	 */
	@Override
	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
	public void validate(Object obj, Errors e) {
		FileUploadForm form = (FileUploadForm) obj;
		if (form.getFile().length == 0) {
			e.rejectValue("file", "FileUpload.msg.notSelect");
		} else {
			for (MultipartFile file : form.getFile()) {
				if (file == null || file.getOriginalFilename().isEmpty()) {
					e.rejectValue("file", "FileUpload.msg.notSelect");
					break;
				}
			}
		}
	}

}
