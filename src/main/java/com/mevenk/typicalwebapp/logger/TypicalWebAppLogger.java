/**
 * 
 */
package com.mevenk.typicalwebapp.logger;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.HYPHEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.UNDERSCORE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.SIMPLE_DATE_FORMAT_CORRELATION_ID;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.argumentsAsAppendableString;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;
import com.mevenk.typicalwebapp.config.TypicalWebAppSourceBean;

/**
 * @author Venkatesh
 *
 */
public class TypicalWebAppLogger extends TypicalWebAppSourceBean {

	@Autowired
	private TypicalWebAppPropertiesLoader typicalWebAppPropertiesLoader;

	private static int correlationIdDateFormatPatternLength;

	public TypicalWebAppLogger(String beanName) {
		super(beanName);
	}

	@PostConstruct
	public void setup() {
		correlationIdDateFormatPatternLength = typicalWebAppPropertiesLoader.getCorrelationIdDateFormatPattern()
				.length();
	}

	/*
	 * OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | POLLING 470 | TRIGGER
	 * 490 | DEBUG 500 | CONFIG 590 | TRACE 600 | ALL Integer.MAX_VALUE
	 */

	public static final String THREAD_CONTEXT_KEY = "TypicalWebAppCorrelationId";

	private static final String LOG_LEVEL_POLLING = "POLLING";
	public static final Level POLLING = Level.forName(LOG_LEVEL_POLLING, 470);

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
		int lengthDateWithUnderScore = correlationIdDateFormatPatternLength + 1;
		int lengthExistingCorrelationId = correlationIdExisting.length();
		String unserscoreAndDate = correlationIdExisting
				.substring(lengthExistingCorrelationId - lengthDateWithUnderScore);
		String correlationIdWithoutUnserscoreAndDate = correlationIdExisting.substring(0,
				lengthExistingCorrelationId - unserscoreAndDate.length());
		StringBuilder stringBuilderCorrelationIdModified = new StringBuilder();
		stringBuilderCorrelationIdModified.append(correlationIdWithoutUnserscoreAndDate);
		stringBuilderCorrelationIdModified.append(HYPHEN + argumentsAsAppendableString(true, parameters) + HYPHEN);
		stringBuilderCorrelationIdModified.append(unserscoreAndDate);
		ThreadContext.put(THREAD_CONTEXT_KEY, stringBuilderCorrelationIdModified.toString());
	}

}
