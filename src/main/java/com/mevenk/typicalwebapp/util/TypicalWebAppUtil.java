/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppUtil {

	private static final String PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID = TypicalWebAppPropertiesLoader.CORRELATION_ID_DATE_FORMAT_PATTERN;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_CORRELATION_ID = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID);

	private static final String PATTERN_SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER = TypicalWebAppPropertiesLoader.TIMELY_LOGGER_DATE_FORMAT_PATTERN;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER);

	public static String dateForCorrelationId(Date date) {
		return "_" + SIMPLE_DATE_FORMAT_CORRELATION_ID.format(date);
	}

	public static String currentDateForTimelyDateLogger() {
		return SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER.format(new Date());
	}

	public static String exceptionStactTraceAsString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

}
