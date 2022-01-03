package com.example.springmvc.config.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmvc.domain.di.repository.jpa.MUserRepository;
import com.example.springmvc.domain.di.repository.jpa.VUserAuthorityRepository;
import com.example.springmvc.domain.entity.jpa.MUser;
import com.example.springmvc.domain.entity.jpa.VUserAuthority;
import com.example.springmvc.utility.StringUtility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom UserDetailsService.<br>
 * Note: See {@link com.example.springmvc.config here (Chapter 2)} for details.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MUserRepository userRepo;

	private final VUserAuthorityRepository userAuthRepo;

	/**
	 * Loads user-specific data.
	 * @param username user ID
	 * @return UserDetailsImpl UserDetailsImpl
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = -1)
	public UserDetails loadUserByUsername(String username) {
		MUser user = null;
		List<VUserAuthority> userAuthList = null;

		// Get user information
		try {
			user = userRepo.findByUserIdEquals(username);
		} catch (Exception e) {
			error("Can not get user data of login id: " + username);
		}
		if (user == null) {
			error("User not found for login id: " + username);
		}
		if (!user.getEnabled()) {
			error("Invalid user: " + username);
		}

		// Get authority information
		try {
			userAuthList = userAuthRepo.findByUserIdEquals(username);
		} catch (Exception e) {
			error("Can not get user authority data for login id: " + username);
		}
		if (userAuthList.size() == 0) {
			error("User authority data not found for login id: " + username);
		}

		// Create authority list
		List<String> roles = new LinkedList<String>();
		for (VUserAuthority v : userAuthList) {
			roles.add(v.getAuthorityName());
		}
		String[] rolesArray = StringUtility.convertListToStringArray(roles);

		// Set UserDetails
		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUsername(user.getUserId());
		userDetails.setDisplayname(user.getUserName());
		userDetails.setPassword(user.getPassword());
		userDetails.setEnabled(user.getEnabled());
		userDetails.setAuthorities(AuthorityUtils.createAuthorityList(rolesArray));
		userDetails.setSessiontimeout(user.getSessionTimeout());
		return userDetails;
	}

	/**
	 * Error handling.
	 * @param msg the detail massage.
	 */
	private void error(String msg) {
		log.warn(msg);
		throw new UsernameNotFoundException(msg);
	}

}
