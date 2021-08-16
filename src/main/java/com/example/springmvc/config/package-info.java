/**
 * <h3>Application Configuration Details.</h3>
 *
 * <h4>Chapter 1 : Web MVC application</h4>
 *
 * <p>This application configures Spring Web MVC with the following classes:
 *  <ul><li>
 *   {@link com.example.springmvc.config.WebAppInitializer WebAppInitializer}
 *   : Register DispatcherServlet and Java-based Spring configuration.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.AppConfig AppConfig}
 *   : Configure the basic configuration of the application.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.WebMvcConfig WebMvcConfig}
 *   : Configure Spring Web MVC.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 2 : Authentication and authorization</h4>
 *
 * <p>This application configures Spring Security with the following classes:
 *  <ul><li>
 *   {@link com.example.springmvc.config.SecurityWebAppInitializer SecurityWebAppInitializer}
 *   : Configure and initialize application security.
 *  </li><li>
 *   {@link com.example.springmvc.config.security.WebSecurityConfig WebSecurityConfig}
 *   : Configure authentication and authorization.
 *  </li><li>
 *   {@link com.example.springmvc.config.security.UserDetailsImpl UserDetailsImpl}
 *   : User-specific data.
 *  </li><li>
 *   {@link com.example.springmvc.config.security.UserDetailsServiceImpl UserDetailsServiceImpl}
 *   : Loads user-specific data.
 *  </li><li>
 *   {@link com.example.springmvc.config.security.AuthenticationSuccessHandlerImpl AuthenticationSuccessHandlerImpl}
 *   : Handler on successful authentication.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 3 : Character encoding</h4>
 *
 * <p>When Spring Security filter is processed before CharacterEncodingFilter,
 *  CharacterEncodingFilter does not work correctly.
 *  CharacterEncodingFilter should be processed before Spring Security filter.
 *  Therefore, instead of using WebAppInitializer.getServletFilters(), configure
 *  CharacterEncodingFilter with the following methods:
 *  <ul><li>
 *   {@link com.example.springmvc.config.WebAppInitializer#getCharacterEncodingFilter() WebAppInitializer#getCharacterEncodingFilter()}
 *   : Configure and get CharacterEncodingFilter.
 *  </li><li>
 *   {@link com.example.springmvc.config.security.WebSecurityConfig#configure(HttpSecurity) WebSecurityConfig#configure(HttpSecurity)}
 *   : Register CharacterEncodingFilter.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 4 : Multipart request</h4>
 *
 * <p>Spring Web MVC framework supports Servlet 3.0 and Commons FileUpload file uploads
 *  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-multipart">
 *  (Spring Framework Documentation)</a>.
 *  This application uses Servlet 3.0 specifications to implement file upload.<br>
 *  In the case of Multipart request, the parameter sent from form cannot be obtained in
 *  Filter.
 *  Therefore, when CsrfFilter in springSecurityFilterChain gets token from request
 *  parameter, getParameter() is executed for Multipart request and an error occurs.
 *  By running MultipartFilter before springSecurityFilterChain, it is necessary to be
 *  able to get the parameters sent from form in Filter even in the case of Multipart
 *  request.
 *  This application configures Multipart request with the following methods:
 *  <ul><li>
 *   {@link com.example.springmvc.config.WebAppInitializer#customizeRegistration() WebAppInitializer#customizeRegistration()}
 *   : Enable Multipart request for Servlet 3.0.
 *  </li><li>
 *   {@link com.example.springmvc.config.SecurityWebAppInitializer#beforeSpringSecurityFilterChain() SecurityWebAppInitializer#beforeSpringSecurityFilterChain()}
 *   : Add MultipartFilter before springSecurityFilterChain.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.AppConfig#multipartResolver() AppConfig#multipartResolver()}
 *   : Register MultipartResolver with Bean.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 5 : Database support</h4>
 *
 * <p>Support database CRUD using Spring Data JPA and Hibernate.
 *  Some parameters are defined in /src/main/resources/application.properties.
 *  This application configures database support with the following classes:
 *  <ul><li>
 *   {@link com.example.springmvc.config.application.DataSourceConfig DataSourceConfig}
 *   : Configure the data source.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.JpaConfig JpaConfig}
 *   : Configure the basic configuration of the application.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.TransactionAdviceConfig TransactionAdviceConfig}
 *   : Configure transaction advice using AOP. Also, AOP must be enabled in Spring Web MVC.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 6 : Validation</h4>
 *
 * <p>Validate form inputs using Bean validation and Hibernate validator.
 *  The message when an error occurs as a result of validation is defined in
 *  /src/main/resources/i18n/ValidationMessages.properties.
 *  Also, if there is
 *  a constraint that cannot be validated in Bean validation, validate it with
 *  custom annotations ({@link com.example.springmvc.application.validation validation package}) and
 *  custom validators ({@link com.example.springmvc.application.di.validator validator package}).
 *  This application configures validation with the following methods:
 *  <ul><li>
 *   {@link com.example.springmvc.config.application.WebMvcConfig#getValidator() WebMvcConfig#getValidator()}
 *   : Configure validator and register as bean.
 *  </li></ul>
 * </p>
 *
 * <h4>Chapter 7 : Asynchronous support</h4>
 *
 * <p>Spring framework supports asynchronous processing using two types of threads, either
 *  under Spring management or not under Spring management. This application configures
 *  asynchronous support with the following classes and methods.
 *  <ul>
 *  <li>
 *   {@link com.example.springmvc.config.WebAppInitializer#customizeRegistration() WebAppInitializer#customizeRegistration()}
 *   : Enable asynchronous support for DispatcherServlet.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.AsyncConfig AsyncConfig}
 *   : Enable and configure asynchronous requests.
 *  </li><li>
 *   {@link com.example.springmvc.config.application.WebMvcConfig#configureAsyncSupport() WebMvcConfig#configureAsyncSupport()}
 *   : Configure asynchronous request handling options.
 *  </li><li>
 *   {@link com.example.springmvc.config.async.CustomCallableProcessingInterceptor CustomCallableProcessingInterceptor}
 *   : Common methods of asynchronous processing using Callable.
 *  </li><li>
 *   {@link com.example.springmvc.config.async.CustomDeferredResultProcessingInterceptor CustomDeferredResultProcessingInterceptor}
 *   : Common methods of asynchronous processing using DeferredResult.
 *  </li>
 *  </ul>
 * </p>
 *
 * <h4>Chapter 8 : Task scheduler</h4>
 *
 * <p>This application configures Spring's Task Scheduler with the following class:
 *  <ul><li>
 *   {@link com.example.springmvc.config.application.TaskSchedulerConfig TaskSchedulerConfig}
 *   : Configure task scheduler and register as bean.
 *  </li></ul>
 * </p>
 */
package com.example.springmvc.config;
