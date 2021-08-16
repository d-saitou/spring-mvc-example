package com.example.springmvc.config.application;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.example.springmvc.config.AppConstants;
import com.example.springmvc.config.async.CustomCallableProcessingInterceptor;
import com.example.springmvc.config.async.CustomDeferredResultProcessingInterceptor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Configure Spring Web MVC.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {
		AppConstants.PKG_CONFIG_DI,
		AppConstants.PKG_APPLICATION_DI
})
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final ApplicationContext applicationContext;

	private final MessageSource messageSource;

	private final Environment env;

	private final AsyncTaskExecutor taskExecutor;

//	/**
//	 * Configure JSP view resolver and register as bean.
//	 * @return JSP view resolver.
//	 */
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}

	/**
	 * Configure Thymeleaf view resolver and register as bean.
	 * @return Thymeleaf view resolver.
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		templateResolver.setCharacterEncoding(AppConstants.CHARACTER_ENCODING);

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.setMessageSource(messageSource);
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new Java8TimeDialect());

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCharacterEncoding(AppConstants.CHARACTER_ENCODING);
		return viewResolver;
	}

	/**
	 * Configure validator (JSR-303 Bean Validation) and register as bean.
	 * @return Validator object.
	 */
	@Override
	public Validator getValidator() {
		final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		return validator;
	}

	/**
	 * Configure view controllers.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/denied").setViewName("403");
	}

	/**
	 * Configuring ResourceHandlers to serve static resources.
	 * @see WebMvcConfigurer#addResourceHandlers(ResourceHandlerRegistry)
	 */
	@Override
	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Integer cachePeriod = Integer.valueOf(env.getProperty("web.resource.cachePeriod"));
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.setCachePeriod(cachePeriod);
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(cachePeriod)
				.resourceChain(false)
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}

	/**
	 * Configure the HttpMessageConverters to use for reading or writing to the body of
	 * the request or response.
	 * @see WebMvcConfigurer#configureMessageConverters(List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
//				.serializationInclusion(JsonInclude.Include.NON_NULL)
//				.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
//				.serializationInclusion(Include.NON_EMPTY)
//				.dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	/**
	 * Configure asynchronous request handling options.
	 * @see WebMvcConfigurer#configureAsyncSupport(AsyncSupportConfigurer)
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(taskExecutor);
		configurer.setDefaultTimeout(60000);
		configurer.registerCallableInterceptors(new CustomCallableProcessingInterceptor());
		configurer.registerDeferredResultInterceptors(new CustomDeferredResultProcessingInterceptor());
	}

	/**
	 * Add resolvers to support custom controller method argument types.
	 * @see WebMvcConfigurer#addArgumentResolvers(List)
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// used for automatic generation of Pageable objects from URL request parameters
//		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//		resolver.setMaxPageSize(10);
//		resolver.setOneIndexedParameters(true);
//		argumentResolvers.add(resolver);
	}

}
