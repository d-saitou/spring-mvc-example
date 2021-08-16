package com.example.springmvc.utility;

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
		LocalDate ldt = null;
		for (String fmtStr : format) {
			if (StringUtility.isEmpty(fmtStr)) continue;
			try {
				ldt = LocalDate.parse(date,
						DateTimeFormatter.ofPattern(fmtStr).withResolverStyle(ResolverStyle.STRICT));
			} catch (Exception expected) {
			}
		}
		if (ldt == null) {
			throw new DateTimeParseException("Failed to parse to LocalDate.", date, 0);
		}
		return ldt;
	}

}
