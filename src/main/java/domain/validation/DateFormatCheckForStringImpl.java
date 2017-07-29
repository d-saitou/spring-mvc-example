package domain.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * DateFormatCheckForString annotation implementation<br>
 * <p>
 *  See DateFormatCheckForString interface for details.
 */
public class DateFormatCheckForStringImpl
		implements ConstraintValidator<DateFormatCheckForString, String> {
	
	private String[] format;
	
	@SuppressWarnings("unused")
	private String message;
	
	/**
	 * Initialize
	 * @param constraintAnnotation Annotation interface
	 */
	@Override
	public void initialize(DateFormatCheckForString constraintAnnotation) {
		format = constraintAnnotation.format();
		message = constraintAnnotation.message();
	}
	
	/**
	 * Validate
	 * @param value Validation parameter
	 * @param context ConstraintValidatorContext
	 * @return Validation result
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid = false;
		String wk = null;
		// Empty check
		if(value == null || "".equals(value)) { return isValid; }
		// Validate input values for each element of the format array
		for (String fmtstr : format) {
			try {
				// Date and Time API(Java 8) has different format from SimpleDateFormat etc.
				wk = fmtstr.replace("y", "u");
				// Convert to LocalDate type (* exceptions will occur if date does not exist)
				DateTimeFormatter dtf = 
						DateTimeFormatter.ofPattern(wk).withResolverStyle(ResolverStyle.STRICT);
				LocalDate.parse(value, dtf);
				// Set to true if conversion succeeded
				isValid = true;
			} catch (DateTimeParseException e) {
				//e.printStackTrace();
			}
			// Exit the loop if conversion succeeded
			if (isValid) { break; }
		}
		return isValid;
	}
	
}
