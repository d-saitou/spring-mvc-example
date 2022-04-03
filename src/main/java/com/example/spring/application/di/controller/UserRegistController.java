package com.example.spring.application.di.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.application.di.helper.UserRegistHelper;
import com.example.spring.application.di.validator.UserRegistFormValidator;
import com.example.spring.application.form.UserRegistForm;
import com.example.spring.domain.di.service.UserManageService;
import com.example.spring.utility.WebUtility;

import lombok.RequiredArgsConstructor;

/**
 * User registration screen controller.
 */
@Controller
@RequestMapping("/user/regist")
@RequiredArgsConstructor
public class UserRegistController {

	private final UserRegistFormValidator validator;

	private final UserManageService service;

	private final UserRegistHelper helper;

	/**
	 * Add validator for UserRegistForm.
	 * @param binder  WebDataBinder object.
	 * @param request HttpServletRequest object.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		if (WebUtility.isPostRequest(request)) {
			binder.addValidators(validator);
		}
	}

	/**
	 * GET request.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @return HTML template path.
	 */
	@GetMapping
	public String get(Model model, Locale locale) {
		UserRegistForm form = new UserRegistForm();
		setInputItemAttributes(form, model, locale);
		return "content/UserRegist";
	}

	/**
	 * POST request.
	 * @param form   UserRegistForm.
	 * @param result BindingResult object.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @return HTML template path or redirect uri.
	 */
	@PostMapping
	public String post(
			// @Valid @ModelAttribute UserRegistForm form,
			@Validated @ModelAttribute UserRegistForm form,
			BindingResult result, Model model, Locale locale) {
		if (result.hasErrors()) {
			setInputItemAttributes(form, model, locale);
			return "content/UserRegist";
		} else {
			service.txRegistUser(helper.convertFormToEntity(form));
			return "redirect:main";
		}
	}

	/**
	 * Set the form and selection items to be displayed on the screen.
	 * @param form   UserRegistForm.
	 * @param model  Model object.
	 * @param locale Locale object.
	 */
	private void setInputItemAttributes(UserRegistForm form, Model model, Locale locale) {
		model.addAttribute("userRegistForm", form);
		model.addAttribute("genderMap", helper.generateGenderItems(locale));
		model.addAttribute("nationalityMap", helper.generateNationalityItems(locale));
		model.addAttribute("passwordHintMap", helper.generatePasswordHintItems(locale));
		model.addAttribute("emailNewsletterMap", helper.generateEmailNewsletterItems(locale));
	}

}
