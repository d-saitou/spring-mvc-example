package com.example.springmvc.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.HtmlUtils;

/**
 * Web utility.
 */
public class WebUtility {

	/**
	 * Private constructor.
	 */
	private WebUtility() {
	}

	/**
	 * Escape HTML.
	 * @param str String before conversion
	 * @return String after conversion
	 */
	public static String escapeHtml(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	/**
	 * Judge whether the request method is GET.
	 * @param request HttpServletRequest
	 * @return True if it is a GET request
	 */
	public static boolean isGetRequest(HttpServletRequest request) {
		return HttpMethod.GET.matches(request.getMethod());
	}

	/**
	 * Judge whether the request method is POST.
	 * @param request HttpServletRequest
	 * @return True if it is a POST request
	 */
	public static boolean isPostRequest(HttpServletRequest request) {
		return HttpMethod.POST.matches(request.getMethod());
	}

	/**
	 * Set the response to download the file.
	 * @param response HttpServletResponse
	 * @param path     file path
	 * @param filename filename when downloading
	 * @param charset  charset string
	 * @throws IOException           IOException
	 * @throws FileNotFoundException FileNotFoundException
	 */
	public static void setFileDownloadResponse(
			HttpServletResponse response, String path, String filename, String charset)
			throws IOException, FileNotFoundException {
		response.setContentType("application/octet-stream;charset=" + charset);
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = null;
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			out = response.getOutputStream();
			byte[] buff = new byte['?'];
			int len = 0;
			while ((len = in.read(buff, 0, buff.length)) != -1) {
				out.write(buff, 0, len);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException expected) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException expected) {
				}
			}
		}
	}

	/**
	 * Set the response to display the alert dialog.
	 * @param response HttpServletResponse
	 * @param message  alert window message
	 * @param charset  charset string
	 * @throws IOException IOException
	 */
	public static void setAlertWindowResponse(
			HttpServletResponse response, String message, String charset)
			throws IOException {
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'charset='" + charset + "'>");
		out.println("window.alert('" + message + "');");
		out.println("</script>");
		out.close();
	}

}
