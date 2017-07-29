package di.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import di.domain.repository.T_TaskRepository;
import domain.entity.T_Task;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import util.common.DateUtility;

/**
 * "File download" service<br>
 * <p>
 *  This class implements temporary file creation and deletion to be 
 *  downloaded from "file download".
 *  This class uses Apache POI to create an excel file as a temporary 
 *  file.
 * <p>
 *  Destination directory path and file name when creating temporary 
 *  files are stored in application.properties and referenced by Value 
 *  annotation.
 * <p>
 *  * For transaction configulation, see "Transaction" comment part in 
 *    application-config.xml.
 */
@Service @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class FileDownloadService {
	
	@Value("${fileDownloadService.workDirectory}")
	private String workDir;
	
	@Getter
	@Value("${fileDownloadService.excelFileName}")
	private String excelFileName;
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
	private T_TaskRepository repo;
	
	/**
	 * Generate temporary excel file full-path
	 * @return full-path
	 */
	public String generateExcelFullPath() {
		StringBuilder path = new StringBuilder();
		return path.append(this.workDir + "/")
					.append(DateUtility.parseString(new Date(), "yyyyMMddHHmmssSSS") + ".")
					.append(this.excelFileName).toString();
	}
	
	/**
	 * Get task items and Create temporary excel file
	 * @param excelFullPath excel file full-path
	 * @param locale        Locale
	 * @return success:true, failure:false
	 * @throws IOException         IOException
	 * @throws DataAccessException DataAccessException
	 */
	public boolean txCreateTaskExcel(
			String excelFullPath, Locale locale) throws IOException {
		File dir = new File(this.workDir);
		List<T_Task> taskList = null;
		// Create excel file
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				log.error("Failed to create working directory. [directory:" + this.workDir + "]");
				return false;
			}
		}
		// Create excel file
		try {
			taskList = this.repo.findAll();
		} catch (DataAccessException e) {
			log.error("Failed to get task data.");
			throw e;
		}
		// Create excel file
		createExcel(excelFullPath, taskList, locale);
		return true;
	}
	
	/**
	 * Create temporary excel file
	 * @param excelFullPath excel file full-path
	 * @param list          task items list
	 * @param locale        Locale
	 * @throws IOException IOException
	 */
	private void createExcel(
			String excelFullPath, List<T_Task> list, Locale locale) throws IOException {
		Workbook wb = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(excelFullPath);
			wb = new XSSFWorkbook();
			Row row = null;
			// Create sheet
			String safeName = WorkbookUtil.createSafeSheetName("Taskdata");
			Sheet sheet = wb.createSheet(safeName);
			// Set header to sheet
			row = sheet.createRow(0);
			row.createCell(0).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.id", null, locale));
			row.createCell(1).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.title", null, locale));
			row.createCell(2).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.scheduledate", null, locale));
			row.createCell(3).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.status", null, locale));
			row.createCell(4).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.description", null, locale));
			row.createCell(5).setCellValue(
					msg.getMessage("FileDownloadService.text.excel.header.userid", null, locale));
			// Set task items to sheet
			int cnt = 1;
			for (T_Task item : list) {
				row = sheet.createRow(cnt);
				row.createCell(0).setCellValue(item.getId().intValue());
				row.createCell(1).setCellValue(item.getTitle());
				row.createCell(2).setCellValue(
						DateUtility.parseString(item.getScheduledate(), "yyyy/MM/dd"));
				row.createCell(3).setCellValue(item.isStatus());
				row.createCell(4).setCellValue(item.getDescription());
				row.createCell(5).setCellValue(item.getUserid());
				cnt++;
			}
			// Write to file
			wb.write(fileOut);
		} catch (IOException e) {
			log.error("Failed to create excel file. [file:" + excelFullPath + "]");
			throw e;
		} finally {
			try {
				fileOut.close();
			} catch (Exception e) {}
			try {
				wb.close();
			} catch (Exception e) {}
		}
		return;
	}
	
	/**
	 * Delete temporary excel file full-path
	 * @param excelFullPath excel file full-path
	 * @return success:true, failure:false
	 */
	public boolean deleteExcel(String excelFullPath) {
		boolean isSuccess = new File(excelFullPath).delete();
		if (!isSuccess) {
			log.error("Failed to delete excel file. [file:" + excelFullPath + "]");
		}
		return isSuccess;
	}
	
}
