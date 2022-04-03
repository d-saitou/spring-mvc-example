package com.example.springmvc.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Application constants.
 */
public class AppConstants {

	/** configuration : character encoding. */
	public static final String CHARACTER_ENCODING = "UTF-8";

	/** configuration : session time out. */
	public static final int DEFAULT_SESSION_TIMEOUT = 300;

	/** format : date format. */
	public static final List<String> DATE_FORMAT =
			Collections.unmodifiableList(Arrays.asList("uuuu/MM/dd", "MM/dd/uuuu"));

	/** java package : application base. */
	private static final String PKG_BASE = "com.example.springmvc";

	/** java package : component scan package for configuration. */
	public static final String PKG_CONFIG_DI = PKG_BASE + ".config.di";

	/** java package : component scan package for application layer. */
	public static final String PKG_APPLICATION_DI = PKG_BASE + ".application.di";

	/** java package : component scan package for domain layer. */
	public static final String PKG_DOMAIN_DI = PKG_BASE + ".domain.di";

	/** java package : application service. */
	public static final String PKG_SERVICE = PKG_DOMAIN_DI + ".service";

	/** java package : Spring Data JPA repository. */
	public static final String PKG_JPA_REPOSITORY = PKG_DOMAIN_DI + ".repository.jpa";

	/** java package : Spring Data JPA entity. */
	public static final String PKG_JPA_ENTITY = PKG_BASE + ".domain.entity.jpa";

	// @formatter:off
	/**
	 * configuration : pointcut expression for transaction advice.
	 * <ul>
	 * <li>Package class of the hierarchy under "com.example.springmvc.domain.di.service".</li>
	 * <li>Classes and methods for which Transactional annotation is not set.</li>
	 * <li>The access modifier is public.</li>
	 * <li>The method name prefix is "tx".</li>
	 * </ul>
	 */
	public static final String TRANSACTION_POINTCUT_EXPRESSION =
			"execution(public * " + PKG_SERVICE + "..*.tx*(..)) and "
			//+ "@target(org.springframework.stereotype.Service) and "
			+ "not @annotation(org.springframework.transaction.annotation.Transactional) and "
			+ "not @target(org.springframework.transaction.annotation.Transactional)";
	// @formatter:on

	/** security : role id - administrator role. */
	public static final String ROLE_ID_FOR_ADMIN = "0001";

	/** security : role id - public role. */
	public static final String ROLE_ID_FOR_USERS = "0002";

	/** security : URI to permit for all users. */
	public static final List<String> URI_PERMIT_ALL;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/");
		list.add("/login");
		list.add("/logout");
		list.add("/user/regist");
		URI_PERMIT_ALL = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for USERS authority. */
	public static final List<String> URI_USERS_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/main");
		list.add("/file/upload"); // GET request
		list.add("/file/upload/*"); // POST request
		list.add("/file/download");
		list.add("/task/create");
		list.add("/task/list");
		list.add("/task/list/*"); // paging
		list.add("/task/delete/*");
		list.add("/task/update"); // POST request
		list.add("/task/update/*"); // GET request
		list.add("/task/bulk/update");
		list.add("/task/bulk/update/*"); // paging
		list.add("/task/api");
		list.add("/task/api/page");
		list.add("/async/mail");
		list.add("/exception/null");
		list.add("/exception/illegal");
		URI_USERS_PAGE = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for ADMIN authority. */
	public static final List<String> URI_ADMIN_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/schedule/history");
		list.add("/user/list");
		list.add("/user/delete/*");
		URI_ADMIN_PAGE = Collections.unmodifiableList(list);
	};

	/**
	 * Private constructor.
	 */
	private AppConstants() {
	}

}
