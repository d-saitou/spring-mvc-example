package com.example.springmvc.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * Configure and initialize application security.
 */
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * Invoked before the springSecurityFilterChain is added.
	 */
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		servletContext
				.addFilter("multipartFilter", new MultipartFilter())
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
	}

}
