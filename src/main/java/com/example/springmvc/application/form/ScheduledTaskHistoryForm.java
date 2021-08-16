package com.example.springmvc.application.form;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;

/**
 * Scheduled task history screen form.
 */
@Data
public class ScheduledTaskHistoryForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String method;

	private String message;

	private LocalDateTime updatedate;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
