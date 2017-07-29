package util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Date type utility<br>
 * <p>
 * Implement utility methods on date type.
 */
public class DateUtility {
	
	/**
	 * Private constructor
	 */
	private DateUtility() {};
	
	/**
	 * Convert date string to Date type value
	 * @param date   date string
	 * @param format date format after conversion
	 * @return Date
	 * @throws ParseException ParseException
	 */
	public static Date parseDate(String date, String... format)
			throws ParseException {
		return DateUtils.parseDate(date, format);
	}
	
	/**
	 * Convert Date type value to date string (use SimpleDateFormat)
	 * @param date   Date
	 * @param format date format before conversion
	 * @return date string
	 */
	public static String parseString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
}
