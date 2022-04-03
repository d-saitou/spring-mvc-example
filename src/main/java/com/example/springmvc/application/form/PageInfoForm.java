package com.example.springmvc.application.form;

import java.io.Serializable;

import com.example.springmvc.utility.StringUtility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A form that stores information for pagination.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private int page;

	private int totalPages;

	private Long totalElements;

	private boolean hasPreviousPage;

	private boolean hasNextPage;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
