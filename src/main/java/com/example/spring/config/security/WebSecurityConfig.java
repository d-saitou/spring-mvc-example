package com.example.spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;

import com.example.spring.config.AppConstants;
import com.example.spring.config.WebAppInitializer;
import com.example.spring.domain.di.repository.jpa.MUserRepository;
import com.example.spring.domain.di.repository.jpa.VUserAuthorityRepository;

import lombok.RequiredArgsConstructor;

/**
 * Configure authentication and authorization.<br>
 * Note: See {@link com.example.spring.config here (Chapter 2)} for details.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final MUserRepository userRepo;

	private final VUserAuthorityRepository userAuthRepo;

	/**
	 * Configure WEB security.
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**");
	}

	// @formatter:off
	/**
	 * Configure user authentication and authorization.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] uriPermitAll = AppConstants.URI_PERMIT_ALL.toArray(new String[0]);
		String[] uriUsersPage = AppConstants.URI_USERS_PAGE.toArray(new String[0]);
		String[] uriAdminPage = AppConstants.URI_ADMIN_PAGE.toArray(new String[0]);
		http.authorizeRequests()
				.antMatchers(uriPermitAll).permitAll()
				.antMatchers(uriUsersPage).access("hasAuthority('USERS')")
				.antMatchers(uriAdminPage).access("hasAuthority('ADMIN')")
//				.anyRequest().authenticated()
				.anyRequest().denyAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/login")
				// Use AuthenticationSuccessHandler instead of defaultSuccessUrl().
//				.defaultSuccessUrl("/main", true)
				.successHandler(new AuthenticationSuccessHandlerImpl())
				.failureUrl("/login?error=true")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
			.sessionManagement()
				.sessionFixation().newSession()
				.invalidSessionUrl("/login")
			.and()
			.csrf()
			.and()
			.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
				.accessDeniedPage("/denied")
			.and()
//			.headers()
//				.defaultsDisabled() // Disable default security headers.
//				.cacheControl().disable()
//				.frameOptions().sameOrigin()
//				.contentTypeOptions().disable()
//				.xssProtection().disable()
//				.httpStrictTransportSecurity().disable()
//			.and()
			// The first filter in security chain that reads a parameter is CsrfFilter,
			// so add CharacterEncodingFilter before CsrfFilter.
			.addFilterBefore(WebAppInitializer.getCharacterEncodingFilter(), CsrfFilter.class);
	}
	// @formatter:on

	/**
	 * Register UserDetailsService as a bean.
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userRepo, userAuthRepo);
	}

	/**
	 * Register PasswordEncoder as a bean.
	 * @return PasswordEncoder object.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
