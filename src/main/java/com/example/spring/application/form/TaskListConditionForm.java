package com.example.spring.application.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.example.spring.application.validation.DateFormatCheckForString;
import com.example.spring.utility.StringUtility;

import lombok.Data;

/**
 * Search condition form on the task list screen.
 */
@Data
public class TaskListConditionForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	private String title;

	@DateFormatCheckForString(format = { "uuuu/MM/dd", "MM-dd-uuuu" }, empty = true)
	private String minDate;

	@DateFormatCheckForString(format = { "uuuu/MM/dd", "MM-dd-uuuu" }, empty = true)
	private String maxDate;

	private Boolean completion;

	@Size(max = 200)
	private String description;

	private int page;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
