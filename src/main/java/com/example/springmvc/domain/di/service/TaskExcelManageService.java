package com.example.springmvc.domain.di.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.springmvc.domain.di.repository.jpa.TTaskRepository;
import com.example.springmvc.domain.entity.jpa.TTask;
import com.example.springmvc.utility.FileUtility;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service to manage Excel files for download.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@Slf4j
public class TaskExcelManageService {

	@Getter
	@Value("${content.fileDownload.excelFileName}")
	private String fileName;

	private final MessageSource msg;

	private final TTaskRepository repo;

	/**
	 * Generate temporary excel file path.
	 * @return excel file path.
	 */
	public String generateExcelFileAbsolutePath() {
		StringBuilder path = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		return path
				.append(FileUtility.getSystemTempDirectoryPath() + File.separator)
				.append(LocalDateTime.now().format(dtf) + ".")
				.append(this.fileName).toString();
	}

	/**
	 * Create temporary excel file.
	 * @param filePath excel file path.
	 * @param userid   user id.
	 * @param locale   Locale.
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public void txCreateExcelFile(String filePath, String userid, Locale locale)
			throws DataAccessException, IOException {
		Workbook wb = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
			wb = new XSSFWorkbook();
			Row row = null;
			// Create sheet
			String safeName = WorkbookUtil.createSafeSheetName("Taskdata");
			Sheet sheet = wb.createSheet(safeName);
			// Set header to sheet
			row = sheet.createRow(0);
			row.createCell(0).setCellValue(msg.getMessage("FileDownload.label.excel.taskId", null, locale));
			row.createCell(1).setCellValue(msg.getMessage("FileDownload.label.excel.title", null, locale));
			row.createCell(2).setCellValue(msg.getMessage("FileDownload.label.excel.scheduledDate", null, locale));
			row.createCell(3).setCellValue(msg.getMessage("FileDownload.label.excel.completion", null, locale));
			row.createCell(4).setCellValue(msg.getMessage("FileDownload.label.excel.description", null, locale));
			row.createCell(5).setCellValue(msg.getMessage("FileDownload.label.excel.userId", null, locale));
			// Set task items to sheet
			List<TTask> taskList = this.repo.findByCreatedByEquals(userid);
			String dateFormat = msg.getMessage("common.format.date", null, locale);
			String completion = msg.getMessage("FileDownload.label.excel.completion", null, locale);
			int cnt = 1;
			for (TTask task : taskList) {
				final String date = task.getScheduledDate().format(DateTimeFormatter.ofPattern(dateFormat));
				row = sheet.createRow(cnt);
				row.createCell(0).setCellValue(task.getTaskId().intValue());
				row.createCell(1).setCellValue(task.getTitle());
				row.createCell(2).setCellValue(date);
				row.createCell(3).setCellValue(task.getCompletion() ? completion : "");
				row.createCell(4).setCellValue(task.getDescription());
				row.createCell(5).setCellValue(task.getCreatedBy());
				cnt++;
			}
			// Write to file
			wb.write(fileOut);
		} catch (DataAccessException e) {
			log.error("Failed to get task data.", e);
			throw e;
		} catch (IOException e) {
			log.error("Failed to create excel file. [file:" + filePath + "]", e);
			throw e;
		} finally {
			try {
				fileOut.close();
			} catch (Exception expected) {
			}
			try {
				wb.close();
			} catch (Exception expected) {
			}
		}
	}

	/**
	 * Delete temporary excel file.
	 * @param filePath excel file path.
	 * @return success:true, failure:false
	 */
	public boolean deleteExcelFile(String filePath) {
		boolean isSuccess = true;
		File file = new File(filePath);
		if (file.exists()) {
			isSuccess = file.delete();
			if (!isSuccess) {
				log.error("Failed to delete excel file. [file:" + filePath + "]");
			}
		}
		return isSuccess;
	}

}
