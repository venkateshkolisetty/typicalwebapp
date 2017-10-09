/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.UNDERSCORE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.SIMPLE_DATE_FORMAT_CORRELATION_ID;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.argumentsAsAppendableString;
import static com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader.CORRELATION_ID_DATE_FORMAT_PATTERN;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.ThreadContext;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppLogger {

	/*
	 * OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | POOLING 470 | TRIGGER
	 * 490 | DEBUG 500 | CONFIG 590 | TRACE 600 | ALL Integer.MAX_VALUE
	 */

	public static final String THREAD_CONTEXT_KEY = "TypicalWebAppCorrelationId";

	private static final String LOG_LEVEL_POOLING = "POOLING";
	public static final Level POOLING = Level.forName(LOG_LEVEL_POOLING, 470);

	private static final String LOG_LEVEL_TRIGGER = "TRIGGER";
	public static final Level TRIGGER = Level.forName(LOG_LEVEL_TRIGGER, 490);

	private static final String LOG_LEVEL_CONFIG = "CONFIG";
	public static final Level CONFIG = Level.forName(LOG_LEVEL_CONFIG, 590);

	public static void resetCorrelationId(String correlationIdPrefix) {
		ThreadContext.put(THREAD_CONTEXT_KEY,
				correlationIdPrefix + UNDERSCORE + SIMPLE_DATE_FORMAT_CORRELATION_ID.format(new Date()));
	}

	public static void addParametersToCorrelationId(Object... parameters) {
		String correlationIdExisting = ThreadContext.get(THREAD_CONTEXT_KEY);
		int lengthDateWithUnderScore = CORRELATION_ID_DATE_FORMAT_PATTERN.length() + 1;
		int lengthExistingCorrelationId = correlationIdExisting.length();
		String unserscoreAndDate = correlationIdExisting
				.substring(lengthExistingCorrelationId - lengthDateWithUnderScore);
		String correlationIdWithoutUnserscoreAndDate = correlationIdExisting.substring(0,
				lengthExistingCorrelationId - unserscoreAndDate.length());
		StringBuilder stringBuilderCorrelationIdModified = new StringBuilder();
		stringBuilderCorrelationIdModified.append(correlationIdWithoutUnserscoreAndDate);
		stringBuilderCorrelationIdModified.append(UNDERSCORE + argumentsAsAppendableString(true, parameters));
		stringBuilderCorrelationIdModified.append(unserscoreAndDate);
		ThreadContext.put(THREAD_CONTEXT_KEY, stringBuilderCorrelationIdModified.toString());
	}

}
