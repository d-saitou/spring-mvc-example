package app.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import util.common.StringUtility;

/**
 * Form class ("Array input form validation check" screen)<br>
 * <p>
 *  The getter, setter, equals, and hashCode methods are automatically 
 *  generated by lombok at compile.
 */
@Accessors(chain = true) @Getter @Setter @EqualsAndHashCode
public class EmployeeListForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Valid
	private List<EmployeeForm> employeelist;
	
	public String toString() {
		return StringUtility.toStringForBeans(this);
	}
	
}
