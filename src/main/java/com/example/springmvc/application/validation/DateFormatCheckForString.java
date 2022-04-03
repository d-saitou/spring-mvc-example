package com.example.springmvc.application.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * DateFormatCheckForString annotation.
 * This annotation is a proprietary extended implementation of Bean Validation, which
 * provides validation of the date format for fields of String type. The implementation
 * class is {@link DateFormatCheckForStringImpl}.
 *
 * <p>usage:<br>
 * {@literal @}DateFormatCheckForString(format = { "uuuu/MM/dd", "MM/dd/uuuu" }, message="...")<br>
 * private String date;
 */
@Documented
@Constraint(validatedBy = DateFormatCheckForStringImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatCheckForString {

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String message() default "{com.example.springmvc.application.validation.DateFormatCheckForString.message}";

	String[] format();

	boolean empty() default false;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		DateFormatCheckForString[] value();
	}

}
