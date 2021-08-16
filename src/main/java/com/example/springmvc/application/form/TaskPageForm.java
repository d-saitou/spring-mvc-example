package com.example.springmvc.application.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;

/**
 * Task paging data form.
 */
@Data
public class TaskPageForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<TaskForm> tasklist;

	private int currentPage;

	private int totalPages;

	private boolean hasPreviousPage;

	private boolean hasNextPage;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
