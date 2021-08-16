package com.example.springmvc.application.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * DateFormatCheckForString annotation implementation.
 * See {@link DateFormatCheckForString} interface for details.
 */
public class DateFormatCheckForStringImpl
		implements ConstraintValidator<DateFormatCheckForString, String> {

	private String[] format;

	// @SuppressWarnings("unused")
	@SuppressFBWarnings(value = "URF_UNREAD_FIELD")
	private String message;

	/**
	 * Initialize.
	 * @param constraintAnnotation Annotation interface.
	 */
	@Override
	public void initialize(DateFormatCheckForString constraintAnnotation) {
		format = constraintAnnotation.format();
		message = constraintAnnotation.message();
	}

	/**
	 * Validate.
	 * @param value   Validation parameter.
	 * @param context ConstraintValidatorContext.
	 * @return Validation result.
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid = false;
		// Empty check
		if (value == null || "".equals(value)) {
			return isValid;
		}
		// Validate input values for each element of the format array
		for (String fmtstr : format) {
			try {
				// Convert to LocalDate type(exceptions will occur if date does not exist)
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmtstr).withResolverStyle(ResolverStyle.STRICT);
				LocalDate.parse(value, dtf);
				// Set to true if conversion succeeded
				isValid = true;
			} catch (DateTimeParseException e) {
				// e.printStackTrace();
			}
			// Exit the loop if conversion succeeded
			if (isValid) {
				break;
			}
		}
		return isValid;
	}

}
