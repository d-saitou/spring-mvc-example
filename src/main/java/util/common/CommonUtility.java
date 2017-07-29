package util.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Common utility<br>
 * <p>
 * Implement generic utility methods.
 */
public class CommonUtility {
	
	/**
	 * Private constructor
	 */
	private CommonUtility() {};
	
	/**
	 * Ignore warning of unchecked cast
	 * @param obj object
	 * @return casted object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T genericsCast(Object obj) {
		T castObj = (T) obj;
		return castObj;
	}
	
	/**
	 * Get exception string
	 * @param e Exception
	 * @return exception string
	 */
	public static String getExceptionMsg(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}
	
}
