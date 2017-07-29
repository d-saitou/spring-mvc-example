package di.domain.dao;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import util.common.DateUtility;

/**
 * Control upload files<br>
 * <p>
 *  This class implements the control of the uploaded file from the web 
 *  screen.
 *  Save directory of the file are stored in application.properties and 
 *  referenced by Value annotation.
 */
@Component @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MultipartFileDao {
	
	@Value("${multipartFileDao.fileUploadDirectory}")
	private String saveDir;
	
	/**
	 * Save file
	 * @param file MultipartFile, if empty returns ""
	 * @return file path
	 * @throws IllegalStateException IllegalStateException
	 * @throws IOException IOException
	 */
	public String save(MultipartFile file) throws IllegalStateException, IOException {
		String date = null;
		String filename = null;
		File dir = null;
		if (file == null)
			return "";
		dir = new File(this.saveDir);
		if (!dir.exists()) {
			if(!dir.mkdirs())
				return "";
		}
		date = DateUtility.parseString(new Date(), "yyyyMMddHHmmssSSS");
		filename = date + "." + file.getOriginalFilename();
		file.transferTo(new File(this.saveDir, filename));
		return this.saveDir + "/" + filename;
	}
	
}
