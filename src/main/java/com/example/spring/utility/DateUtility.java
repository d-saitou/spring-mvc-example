package com.example.spring.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Date utility.
 */
public class DateUtility {

	/**
	 * Private constructor.
	 */
	private DateUtility() {
	}

	/**
	 * Convert String to Date.
	 * @param date   date string.
	 * @param format date format after conversion.
	 * @return LocalDate object.
	 */
	public static LocalDate parseLocalDate(String date, String... format) {
		if (StringUtility.isEmpty(date)) {
			return null;
		}
		LocalDate ldt = null;
		for (String fmtStr : format) {
			if (StringUtility.isEmpty(fmtStr)) {
				continue;
			}
			try {
				ldt = LocalDate.parse(date,
						DateTimeFormatter.ofPattern(fmtStr).withResolverStyle(ResolverStyle.STRICT));
			} catch (Exception e) {
				throw new DateTimeParseException("Failed to parse to LocalDate.", date, 0, e);
			}
		}
		if (ldt == null) {
			throw new DateTimeParseException("Failed to parse to LocalDate.", date, 0);
		}
		return ldt;
	}

}
