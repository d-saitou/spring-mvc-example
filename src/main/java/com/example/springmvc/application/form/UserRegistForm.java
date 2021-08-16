package com.example.springmvc.application.form;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.springmvc.application.validation.DateFormatCheckForString;
import com.example.springmvc.utility.StringUtility;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

/**
 * User registration screen form.
 */
@Data
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set array values of form by Spring.")
public class UserRegistForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(min = 4, max = 10)
	private String userId;

	@NotBlank
	@Size(min = 0, max = 40)
	private String userName;

	@Size(min = 4, max = 16)
	private String password;

	@Size(min = 4, max = 16)
	private String retypePassword;

	@NotBlank
	@Size(min = 0, max = 50)
	private String emailAddress1;

	@Size(min = 0, max = 50)
	private String emailAddress2;

	@NotBlank
	private String gender;

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Past
	private LocalDate dateOfBirth;

	@NotEmpty
	private String[] nationality;

	@NotBlank
	@Size(min = 0, max = 50)
	private String address;

	@NotBlank
	@Size(min = 0, max = 16)
	private String creditCardNo;

	@DateFormatCheckForString(format = { "uuuu/MM/dd", "MM-dd-uuuu" })
	private String creditExpirationDate;

	@NotBlank
	private String passwordHint;

	@NotBlank
	@Size(min = 0, max = 50)
	private String passwordHintAnswer;

	@Min(60)
	@Max(3600)
	private Integer sessionTimeout;

	private String[] emailNewsletter;

	private Boolean agreeTeams;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
