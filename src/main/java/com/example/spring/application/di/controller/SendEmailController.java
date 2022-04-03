package com.example.spring.application.di.controller;

import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.application.form.MailForm;
import com.example.spring.domain.di.service.SendEmailThreadControlService;

import lombok.RequiredArgsConstructor;

/**
 * Send mail (asynchronous processing) screen controller.
 */
@Controller
@RequiredArgsConstructor
public class SendEmailController {

	private final MessageSource msg;

	private final SendEmailThreadControlService service;

	@Value("${javax.mail.fromAddr}")
	private String fromAddr;

	/**
	 * GET request.
	 * @param model   Model object.
	 * @param success mail send result (optional).
	 * @param locale  Locale object.
	 * @return HTML template path.
	 */
	@GetMapping("/async/mail")
	public String get(Model model, @RequestParam Optional<Boolean> success, Locale locale) {
		model.addAttribute("mailForm", new MailForm(fromAddr, fromAddr, null, null));
		if (success.isPresent()) {
			String message = null;
			if (success.get().booleanValue()) {
				message = msg.getMessage("SendEmail.msg.success", null, locale);
			} else {
				message = msg.getMessage("SendEmail.msg.failed", null, locale);
			}
			model.addAttribute("message", message);
		}
		return "content/SendEmail";
	}

	/**
	 * POST request.
	 * @param form   MailForm.
	 * @param result BindingResult object.
	 * @param model  Model object.
	 * @return CompletableFuture object.
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@PostMapping("/async/mail")
	public CompletableFuture<String> post(
			@Validated @ModelAttribute MailForm form, BindingResult result, Model model)
			throws InterruptedException, ExecutionException {
		if (result.hasErrors()) {
			model.addAttribute("mailForm", form);
			return CompletableFuture.completedFuture("content/SendEmail");
		} else {
			return service.sendMails(
					form.getTo().split(","), form.getSubject(), form.getBody(),
					"redirect:/async/mail?success=true",
					"redirect:/async/mail?success=false");
		}
	}

}
