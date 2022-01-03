package com.example.springmvc.domain.di.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.springmvc.config.AppConstants;
import com.example.springmvc.domain.di.repository.jpa.MUserRepository;
import com.example.springmvc.domain.di.repository.jpa.MUserRoleRepository;
import com.example.springmvc.domain.entity.jpa.MUser;
import com.example.springmvc.domain.entity.jpa.MUserRole;

import lombok.RequiredArgsConstructor;

/**
 * Service that manages users of this application.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
public class UserManageService {

	private final MUserRepository userRepo;

	private final MUserRoleRepository userRoleRepo;

	/**
	 * Get all users.
	 * @return user entities.
	 */
	public List<MUser> txGetAll() {
		return userRepo.findAllByOrderByUserIdAsc();
	}

	/**
	 * Register user and default role assignments.
	 * @param user user entity.
	 */
	public void txRegistUser(MUser user) {
		userRepo.save(user);

		// Register the role assigned to the user.
		MUserRole userRole = new MUserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(AppConstants.ROLE_ID_FOR_USERS);
		userRoleRepo.save(userRole);
	}

	/**
	 * Update user enable / disable.
	 * @param userMap Map(user id / enable or disable) object.
	 * @param userId  user id.
	 */
	public void txChangeEnableUser(Map<String, Boolean> userMap, String userId) {
		LocalDateTime now = LocalDateTime.now();
		for (Map.Entry<String, Boolean> entry : userMap.entrySet()) {
			userRepo.setEnable(entry.getKey(), entry.getValue(), userId, now);
		}
	}

	/**
	 * Delete user.
	 * @param userid user id.
	 */
	public void txDeleteUser(String userid) {
		userRepo.deleteByUserIdEquals(userid);
	}

}
