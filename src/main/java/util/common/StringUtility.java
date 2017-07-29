package util.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * String type utility<br>
 * <p>
 * Implement utility methods on String type.
 */
public class StringUtility {
	
	/**
	 * Private constructor
	 */
	private StringUtility() {};
	
	/**
	 * Check null and blank("")
	 * @param str String
	 * @return True if not null or blank
	 */
	public static Boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}
	
	/**
	 * Convert String type List to String array
	 * @param list String type List
	 * @return String array
	 */
	public static String[] convertListToStringArray(List<String> list) {
		return (String[]) list.toArray(new String[0]);
	}
	
	/**
	 * Generate JavaBeans toString method result string
	 * @param obj object
	 * @return toString() result string
	 */
	public static String toStringForBeans(Object obj) {
		return ToStringBuilder.reflectionToString(obj, ToStringStyle.SIMPLE_STYLE);
	}
	
}
