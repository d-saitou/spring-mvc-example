package com.example.springmvc.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Common utility.
 */
public class CommonUtility {

	/**
	 * Private constructor.
	 */
	private CommonUtility() {
	}

	/**
	 * Get the stack trace string.
	 * @param e Exception
	 * @return stack trace string
	 */
	public static String getStackTraceString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

}
