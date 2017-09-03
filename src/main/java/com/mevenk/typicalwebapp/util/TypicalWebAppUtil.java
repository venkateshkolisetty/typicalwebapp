/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppUtil {

	private static final String PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID = "dd_MM_yyyy_HH_mm_ss_SSS";
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_CORRELATION_ID = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID);

	/**
	 * 
	 */
	public TypicalWebAppUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String dateForCorrelationId(Date date) {
		return "_" + SIMPLE_DATE_FORMAT_CORRELATION_ID.format(date);
	}

	public static String exceptionStactTraceAsString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

}
