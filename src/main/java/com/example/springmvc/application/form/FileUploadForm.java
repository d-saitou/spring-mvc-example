package com.example.springmvc.application.form;

import org.springframework.web.multipart.MultipartFile;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

/**
 * File upload screen form.
 */
@Data
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set array values of form by Spring.")
public class FileUploadForm {

	private MultipartFile[] file;

}
