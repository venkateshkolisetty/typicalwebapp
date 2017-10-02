/**
 * 
 */
package com.mevenk.typicalwebapp.exception;

/**
 * @author Venkatesh
 *
 */
public class DownloadFileNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6959527638307317192L;

	/**
	 * 
	 */
	public DownloadFileNotFoundException() {
	}

	/**
	 * @param fileToBeDownloaded
	 */
	public DownloadFileNotFoundException(String fileToBeDownloaded) {
		super(fileToBeDownloaded + " - File Not Found");
	}

	/**
	 * @param cause
	 */
	public DownloadFileNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param fileToBeDownloaded
	 * @param cause
	 */
	public DownloadFileNotFoundException(String fileToBeDownloaded, Throwable cause) {
		super(fileToBeDownloaded, cause);
	}

	/**
	 * @param fileToBeDownloaded
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DownloadFileNotFoundException(String fileToBeDownloaded, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(fileToBeDownloaded, cause, enableSuppression, writableStackTrace);
	}

}
