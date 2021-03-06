package app.form;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Form class ("File upload" screen [multiple files])<br>
 * <p>
 *  The getter, setter, equals, and hashCode methods are automatically 
 *  generated by lombok at compile.
 */
@Accessors(chain = true) @Getter @Setter @EqualsAndHashCode
public class MultiFileUploadForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<MultipartFile> files;
	
//	public String toString() {
//		return StringUtility.toString(this);
//	}
	
}
