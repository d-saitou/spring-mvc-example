package util.common;

import org.apache.commons.io.FilenameUtils;

/**
 * File and directory utility<br>
 * <p>
 * Implementing utility methods on files and directories.
 */
public class FileUtility {
	
	/**
	 * Private constructor
	 */
	private FileUtility() {};
	
	/**
	 * Get the file name or directory name at the end
	 * @param path path, null returns null
	 * @return file name or directory name
	 */
	public static String getName(String path) {
		return FilenameUtils.getName(path);
	}
	
	/**
	 * Get file name or directory name at the end as get (without extension)
	 * @param path path, null returns null
	 * @return file name or directory name (without extension)
	 */
	public static String getBaseName(String path) {
		return FilenameUtils.getBaseName(path);
	}
	
	/**
	 * Get file extension
	 * @param path path, null returns null
	 * @return file extension
	 */
	public static String getExtension(String path) {
		return FilenameUtils.getExtension(path);
	}
	
	/**
	 * Get parent directory path (without prefix, with end separator)
	 * @param path path, null returns null
	 * @return parent directory path (without prefix, with end separator)
	 */
	public static String getPath(String path) {
		return FilenameUtils.getPath(path);
	}
	
	/**
	 * Get parent directory path (without prefix and end separator)
	 * @param path path, null returns null
	 * @return parent directory path (without prefix and end separator)
	 */
	public static String getPathNoEndSeparator(String path) {
		return FilenameUtils.getPathNoEndSeparator(path);
	}
	
	/**
	 * Get parent directory path (with prefix and end separator)
	 * @param path path, null returns null
	 * @return parent directory path (with prefix and end separator)
	 */
	public static String getFullPath(String path) {
		return FilenameUtils.getFullPath(path);
	}
	
	/**
	 * Get parent directory path (with prefix, without end separator)
	 * @param path path, null returns null
	 * @return parent directory path (with prefix, without end separator)
	 */
	public static String getFullPathNoEndSeparator(String path) {
		return FilenameUtils.getFullPathNoEndSeparator(path);
	}
	
	/**
	 * Get escaped file name or directory name
	 * @param path path, null returns null
	 * @return escaped file name or directory name
	 */
	public static String getEscapeFileName(String path) {
		if (path == null) { return null; }
		return getName(path).replaceAll("(/|<|>|,|;|:|\"|\\*|\\?|\\||\\\\)", "");
	}
	
}
