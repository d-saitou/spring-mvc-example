package com.example.springmvc.config.application;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.example.springmvc.config.AppConstants;

/**
 * Configure the application.
 */
@Configuration
@ComponentScan(basePackages = AppConstants.PKG_DOMAIN_DI)
@EnableAspectJAutoProxy
@PropertySources({
		@PropertySource(value = "classpath:application.properties"),
		@PropertySource(value = "file:${springMvcExample.configurationFile}", ignoreResourceNotFound = true)
})
public class AppConfig {

	/**
	 * Configure MessageSource and register as bean.
	 * @return MessageSource object.
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames("classpath:i18n/messages", "classpath:i18n/ValidationMessages");
		ms.setFallbackToSystemLocale(false);
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(30);
		return ms;
	}

	/**
	 * Configure LocaleResolver and register as bean.
	 * @return LocaleResolver object.
	 */
	@Bean
	public LocaleResolver localeResolver() {
//		SessionLocaleResolver lr = new SessionLocaleResolver();
//		CookieLocaleResolver lr = new CookieLocaleResolver();
		AcceptHeaderLocaleResolver lr = new AcceptHeaderLocaleResolver();
		lr.setDefaultLocale(Locale.US);
		return lr;
	}

	/**
	 * Configure MultipartResolver and register as bean.
	 * @return MultipartResolver object.
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
