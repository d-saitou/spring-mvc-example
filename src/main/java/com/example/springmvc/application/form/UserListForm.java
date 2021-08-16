package com.example.springmvc.application.form;

import java.io.Serializable;
import java.util.List;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User list screen form.
 */
@Data
@Accessors(chain = true)
public class UserListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UserForm> userList;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
