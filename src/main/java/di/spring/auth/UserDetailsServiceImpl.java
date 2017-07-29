package di.spring.auth;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import di.domain.repository.M_UserRepository;
import di.domain.repository.V_User_AuthorityRepository;
import domain.entity.M_User;
import domain.entity.V_User_Authority;
import util.common.StringUtility;

/**
 * Proprietary authentication (UserDetailsService)<br>
 * <p>
 *  This class implements authentication for Spring Security.
 *  Implement the interface UserDetailsService and perform proprietary 
 *  authentication.
 * <p>
 *  The DB schema that stores user authentication and authorization 
 *  information is as follows.
 *  This class refers to the schemas 1 and 6, gets the user's 
 *  authentication and authorization information, and passes it to 
 *  Spring Security.
 * <p>
 *  [schema]<br>
 *   1. m_user: User authentication information<br>
 *   2. m_role: Role information<br>
 *   3. m_authority: Authority information<br>
 *   4. m_user_role: Role assignment for users<br>
 *   5. m_role_authority: Authorization assignment for roles<br>
 *   6. v_user_authority: View referencing 1 to 5 information<br>
 * <p>
 *  In order to use Spring Security, it is necessary to set filter in 
 *  web.xml.<br>
 *  * See "Spring Security filter" comment part in web.xml.
 * <p>
 *  Also, in order to use this class, it is necessary to set bean 
 *  definition in the Spring Security configuration.<br>
 *  * See "sec:authentication-manager" tag in security-config.xml.<br>
 *  * See "userDetailsService" in security-config.xml.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private M_UserRepository userRepo;
	
	@Autowired
	private V_User_AuthorityRepository userAuthRepo;
	
	/**
	 * Get authentication and authorization information
	 * @param username user ID
	 * @return UserDetailsImpl UserDetailsImpl
	 */
	@Transactional(
			isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = -1)
	public UserDetails loadUserByUsername(String username) {
		List<M_User> userList = null;
		List<V_User_Authority> userAuthList = null;
		// Get user information
		try {
			userList = this.userRepo.findByUserid(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException(
					"Can not get user data of login id: " + username);
		}
		if (userList.size() == 0) {
			throw new UsernameNotFoundException(
					"User not found for login id: " + username);
		}
		// Get authority information
		try {
			userAuthList = this.userAuthRepo.findByUserid(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException(
					"Can not get user authority data for login id: " + username);
		}
		if (userAuthList.size() == 0) {
			throw new UsernameNotFoundException(
					"User authority data not found for login id: " + username);
		}
		// Create authority list
		List<String> roles = new LinkedList<String>();
		for (V_User_Authority v : userAuthList) {
			roles.add(v.getAuthorityname());
		}
		String[] rolesArray = StringUtility.convertListToStringArray(roles);
		// Set UserDetails
		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUserid(userList.get(0).getUserid())
				.setShowname(userList.get(0).getUsername())
				.setPassword(userList.get(0).getPassword())
				.setEnabled(userList.get(0).isEnabled())
				.setAuthorities(AuthorityUtils.createAuthorityList(rolesArray));
		return userDetails;
	}
	
}
