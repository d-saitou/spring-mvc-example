package com.example.springmvc.application.di.helper;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springmvc.application.form.UserRegistForm;
import com.example.springmvc.domain.entity.jpa.MUser;
import com.example.springmvc.utility.DateUtility;

import lombok.RequiredArgsConstructor;

/**
 * User registration screen helper.
 */
@Component
@RequiredArgsConstructor
public class UserRegistHelper {

	private static final String[] DATE_FORMAT = { "uuuu/MM/dd", "MM/dd/uuuu" };

	private final MessageSource msg;

	private final PasswordEncoder passwordEncoder;

	/**
	 * Convert form to entity.
	 * @param form UserRegistForm.
	 * @return MUser entity.
	 */
	public MUser convertFormToEntity(UserRegistForm form) {
		final LocalDate expirationDate = DateUtility.parseLocalDate(form.getCreditExpirationDate(), DATE_FORMAT);

		MUser entity = new MUser();
		entity.setUserId(form.getUserId());
		entity.setUserName(form.getUserName());
		entity.setPassword(passwordEncoder.encode(form.getPassword()));
		entity.setEmailAddress1(form.getEmailAddress1());
		entity.setEmailAddress2(form.getEmailAddress2());
		entity.setGender(form.getGender());
		entity.setDateOfBirth(form.getDateOfBirth());
		int i = 0;
		for (String nationality : form.getNationality()) {
			if (i == 0) entity.setNationality1(nationality);
			if (i == 1) entity.setNationality2(nationality);
			i++;
		}
		entity.setAddress(form.getAddress());
		entity.setCreditCardNo(form.getCreditCardNo());
		entity.setCreditExpirationDate(expirationDate);
		entity.setPasswordHint(form.getPasswordHint());
		entity.setPasswordHintAnswer(form.getPasswordHintAnswer());
		entity.setSessionTimeout(form.getSessionTimeout());
		for (String value : form.getEmailNewsletter()) {
			if (value.equals("1")) entity.setEmailNewsletter1(true);
			if (value.equals("2")) entity.setEmailNewsletter2(true);
		}
		entity.setReadonly(false);
		entity.setEnabled(true);

		return entity;
	}

	/**
	 * Generate radio button items for gender.
	 * @param locale Locale.
	 * @return Map of input items (labels and values).
	 */
	public Map<String, String> generateGenderItems(Locale locale) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put(msg.getMessage("UserRegist.label.gender.men", null, locale), "1");
		m.put(msg.getMessage("UserRegist.label.gender.women", null, locale), "2");
		return m;
	}

	/**
	 * Generate combo box item for nationality.
	 * @param locale Locale.
	 * @return Map of input items (labels and values).
	 */
	public Map<String, String> generateNationalityItems(Locale locale) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put(msg.getMessage("UserRegist.label.nationality.jp", null, locale), "jp");
		m.put(msg.getMessage("UserRegist.label.nationality.us", null, locale), "us");
		return m;
	}

	/**
	 * Generate combo box item for password answer.
	 * @param locale Locale.
	 * @return Map of input items (labels and values).
	 */
	public Map<String, String> generatePasswordHintItems(Locale locale) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put(msg.getMessage("UserRegist.label.passwordHint.born", null, locale), "1");
		m.put(msg.getMessage("UserRegist.label.passwordHint.pet", null, locale), "2");
		return m;
	}

	/**
	 * Generate checkbox items for mail magazine.
	 * @param locale Locale.
	 * @return Map of input items (labels and values).
	 */
	public Map<String, String> generateEmailNewsletterItems(Locale locale) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put(msg.getMessage("UserRegist.label.emailNewsletter.check1", null, locale), "1");
		m.put(msg.getMessage("UserRegist.label.emailNewsletter.check2", null, locale), "2");
		return m;
	}

}
