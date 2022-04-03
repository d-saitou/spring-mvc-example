package com.example.spring.domain.di.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service to manage multipart files.
 */
@Service
//@SessionScope
public class MultipartFileManageService {

	@Value("${application.datadir}")
	private String saveDir;

	/**
	 * Save files to localhost.
	 * @param files MultipartFile[] object.
	 * @return List of saved file paths.
	 * @throws IOException
	 */
	public List<String> saveMultipartFiles(MultipartFile[] files) throws IOException {
		List<String> list = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		String date = LocalDateTime.now().format(dtf);
		for (MultipartFile file : files) {
			String filename = date + "_" + file.getOriginalFilename();
			file.transferTo(new File(this.saveDir, filename));
			list.add(this.saveDir + File.separator + filename);
		}
		return list;
	}

}
