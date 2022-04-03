package com.example.springmvc.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.springmvc.config.application.AppConfig;
import com.example.springmvc.config.application.AsyncConfig;
import com.example.springmvc.config.application.DataSourceConfig;
import com.example.springmvc.config.application.JpaConfig;
import com.example.springmvc.config.application.TaskSchedulerConfig;
import com.example.springmvc.config.application.TransactionAdviceConfig;
import com.example.springmvc.config.application.WebMvcConfig;
import com.example.springmvc.config.security.WebSecurityConfig;
import com.example.springmvc.utility.FileUtility;

/**
 * Configure and initialize the application.
 */
public class WebAppInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Configure the root application context.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
				AppConfig.class,
				DataSourceConfig.class,
				JpaConfig.class,
				TransactionAdviceConfig.class,
				TaskSchedulerConfig.class,
				AsyncConfig.class,
				WebSecurityConfig.class
		};
	}

	/**
	 * Configure the servlet application context.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	/**
	 * Specify the servlet mappings for the DispatcherServlet.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * filters to add and map to the DispatcherServlet.
	 */
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {
//				getCharacterEncodingFilter(),
				new HiddenHttpMethodFilter() // Supports put and delete in form tag using hidden tag.
		};
	}

	/**
	 * Configure and return CharacterEncodingFilter.
	 * @return CharacterEncodingFilter object.
	 */
	public static Filter getCharacterEncodingFilter() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding(AppConstants.CHARACTER_ENCODING);
		cef.setForceEncoding(true);
		return cef;
	}

	/**
	 * Customize the DispatcherServlet.
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setLoadOnStartup(1);
		registration.setMultipartConfig(getMultipartConfigElement());
		registration.setAsyncSupported(true);
		registration.setInitParameter("defaultHtmlEscape", "true");
	}

	/**
	 * Configure and return MultipartConfig.
	 * @return MultipartConfigElement object.
	 */
	private MultipartConfigElement getMultipartConfigElement() {
		String dir = FileUtility.getSystemTempDirectoryPath();
		long size = 1024 * 1024 * 10; // 10MB
		int threshold = 1024 * 1024; // 1MB
		MultipartConfigElement mc = new MultipartConfigElement(dir, size, size, threshold);
		return mc;
	}

}
