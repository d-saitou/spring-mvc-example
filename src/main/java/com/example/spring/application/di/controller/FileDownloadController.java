package com.example.spring.application.di.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.config.security.UserDetailsImpl;
import com.example.spring.domain.di.service.TaskExcelManageService;
import com.example.spring.utility.WebUtility;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * File download controller.
 */
@Controller
@RequestMapping("/file/download")
@RequiredArgsConstructor
public class FileDownloadController {

	@Value("${application.charset}")
	private String charset;

	private final MessageSource msg;

	private final TaskExcelManageService service;

	/**
	 * GET request.
	 * @param response    HttpServletResponse.
	 * @param locale      locale.
	 * @param userDetails UserDetailsImpl object.
	 * @throws IOException
	 */
	@GetMapping
	@SuppressFBWarnings(value = "REC_CATCH_EXCEPTION")
	public void get(
			HttpServletResponse response, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
		String path = service.generateExcelFileAbsolutePath();
		try {
			service.txCreateExcelFile(path, userDetails.getUsername(), locale);
			WebUtility.setFileDownloadResponse(response, path, service.getFileName(), charset);
		} catch (Exception e) {
			String message = msg.getMessage("FileDownload.msg.failed", null, locale);
			WebUtility.setAlertWindowResponse(response, message, charset);
		} finally {
			service.deleteExcelFile(path);
		}
	}

}
