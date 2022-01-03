package com.example.springmvc.application.di.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springmvc.application.form.UserForm;
import com.example.springmvc.application.form.UserListForm;
import com.example.springmvc.config.security.UserDetailsImpl;
import com.example.springmvc.domain.di.service.UserManageService;
import com.example.springmvc.domain.entity.jpa.MUser;

import lombok.RequiredArgsConstructor;

/**
 * User list screen controller.
 */
@Controller
@RequiredArgsConstructor
public class UserListController {

	private final UserManageService service;

	/**
	 * GET request.
	 * @param model Model object.
	 * @return HTML template path.
	 */
	@GetMapping("/user/list")
	public String get(Model model) {
		model.addAttribute("userListForm", getUserListForm());
		return "content/UserList";
	}

	/**
	 * POST request for user list.
	 * @param form        UserListForm.
	 * @param result      BindingResult object.
	 * @param model       Model object.
	 * @param locale      Locate object.
	 * @param userDetails UserDetailsImpl object.
	 * @return HTML template path.
	 */
	@PostMapping("/user/list")
	public String post(
			@Validated @ModelAttribute UserListForm form,
			BindingResult result, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (result.hasErrors()) {
			model.addAttribute("userListForm", form);
		} else {
			Map<String, Boolean> userMap = new HashMap<String, Boolean>();
			for (UserForm user : form.getUserList()) {
				if (user.isUpdateFlg()) {
					userMap.put(user.getUserId(), user.isEnable());
				}
			}
			service.txChangeEnableUser(userMap, userDetails.getUsername());
			model.addAttribute("userListForm", getUserListForm());
		}
		return "content/UserList";
	}

	/**
	 * Get the user's form list.
	 * @return form list.
	 */
	private UserListForm getUserListForm() {
		List<UserForm> userList = new LinkedList<UserForm>();
		for (MUser user : service.txGetAll()) {
			userList.add(new UserForm()
					.setUserId(user.getUserId())
					.setUserName(user.getUserName())
					.setEmailAddress1(user.getEmailAddress1())
					.setEmailAddress2(user.getEmailAddress2())
					.setReadonly(user.getReadonly())
					.setEnable(user.getEnabled()));
		}
		return new UserListForm().setUserList(userList);
	}

	/**
	 * POST request for user deletion.
	 * @param userid user id.
	 * @return redirect uri.
	 */
	@GetMapping("/user/delete/{userid}")
	public String delete(@PathVariable("userid") String userid) {
		service.txDeleteUser(userid);
		return "redirect:/user/list";
	}

}
