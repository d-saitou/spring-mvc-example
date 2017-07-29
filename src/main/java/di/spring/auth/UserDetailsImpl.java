package di.spring.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Proprietary authentication (UserDetails)<br>
 * <p>
 *  This class implements authentication for Spring Security.
 *  Implement the interface UserDetails and pass this class to Spring 
 *  Security as authentication information.
 * <p>
 *  By setting the fields of this class by the proprietary authentication 
 *  service class (UserDetailsServiceImpl), let Spring Security perform 
 *  authentication.
 * <p>
 *  By setting the AuthenticationPrincipalArgumentResolver class in the 
 *  Spring configulation, it is possible to refer to the fields of this 
 *  class from the controller class.<br>
 *  * See "mvc:argument-resolvers" tag in application-config.xml.
 * <p>
 *  In addition to the accessors of the interface definition, 
 *  "showname" is implemented as an accessor (properties) for reference 
 *  from the views.
 */
public class UserDetailsImpl implements UserDetails {
	
	private String userid;
	private String showname;
	private String password;
	private boolean isEnabled;
	private Collection<GrantedAuthority> authorities;
	
	/* getter */
	public String getUsername() {
		return this.userid;
	}
	public String getPassword() {
		return this.password;
	}
	public boolean isEnabled() {
		return this.isEnabled;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	public boolean isAccountNonExpired() {
		return true;	// return fixed value
	}
	public boolean isAccountNonLocked() {
		return true;	// return fixed value
	}
	public boolean isCredentialsNonExpired() {
		return true;	// return fixed value
	}
	public String getUserid() {
		return this.userid;
	}
	public String getShowname() {
		return this.showname;
	}
	
	/* setter */
	public UserDetailsImpl setUserid(String userid) {
		this.userid = userid;
		return this;
	}
	public UserDetailsImpl setShowname(String showname) {
		this.showname = showname;
		return this;
	}
	public UserDetailsImpl setPassword(String password) {
		this.password = password;
		return this;
	}
	public UserDetailsImpl setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}
	public UserDetailsImpl setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
		return this;
	}
	
}
