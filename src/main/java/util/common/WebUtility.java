package util.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.HtmlUtils;

/**
 * Web utility<br>
 * <p>
 * Implement generic utility methods for web.
 */
public class WebUtility {
	
	/**
	 * Private constructor
	 */
	private WebUtility() {};
	
	/**
	 * Escape HTML
	 * @param str String before conversion
	 * @return String after conversion
	 */
	public static String escapeHtml(String str) {
		return HtmlUtils.htmlEscape(str);
	}
	
	/**
	 * Get HTTP GET request result string
	 * @param urlStr  URL
	 * @param charset Charset
	 * @return request result string. If the response code is not 200, it is blank
	 * @throws IOException IOException
	 */
	public static String getHttpGetRequestResult(String urlStr, Charset charset)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		URL url = new URL(urlStr);
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			if (con.getResponseCode() == 200) {
				InputStreamReader isr = new InputStreamReader(con.getInputStream(), charset);
				BufferedReader reader = new BufferedReader(isr);
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return sb.toString();
	}
	
	/**
	 * Set response for file download
	 * @param response HttpServletResponse
	 * @param path     filepath
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
				} catch (IOException e) {}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Set response for alert window
	 * @param response HttpServletResponse
	 * @param message  alert window message
	 * @param charset  charset string
	 * @throws IOException IOException
	 */
	public static void setAlertWindowResponse(
			HttpServletResponse response, String message, String charset) throws IOException {
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'charset='" + charset + "'>");
		out.println("window.alert('" + message + "');");
		out.println("</script>");
		out.close();
	}
	
}
