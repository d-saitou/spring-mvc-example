package com.example.spring.utility;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * String utility.
 */
public class StringUtility {

	/**
	 * Private constructor.
	 */
	private StringUtility() {
	}

	/**
	 * Checks if a CharSequence is empty ("") or null.
	 * @param str String
	 * @return true if the CharSequence is empty or null
	 */
	public static Boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * Check null and empty.
	 * @param str String
	 * @return true if not null and not empty string
	 */
	public static Boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}

	/**
	 * Convert a string list to a string array.
	 * @param list string list
	 * @return string array
	 */
	public static String[] convertListToStringArray(List<String> list) {
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * Convert object to JSON string.
	 * @param obj object
	 * @return JSON string
	 */
	public static String toJsonStyleString(Object obj) {
		return ToStringBuilder.reflectionToString(obj, ToStringStyle.JSON_STYLE);
	}

}
