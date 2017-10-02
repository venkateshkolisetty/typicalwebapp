/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import org.apache.logging.log4j.Level;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppLogger {

	// OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | DEBUG 500 | TRACE 600 |
	// ALL Integer.MAX_VALUE

	public static final String THREAD_CONTEXT_KEY = "TypicalWebAppCorrelationId";

	private static final String LOG_LEVEL_POOLING = "POOLING";
	public static final Level POOLING = Level.forName(LOG_LEVEL_POOLING, 490);

	private static final String LOG_LEVEL_CONFIG = "CONFIG";
	public static final Level CONFIG = Level.forName(LOG_LEVEL_CONFIG, 470);

	private static final String LOG_LEVEL_TRIGGER = "TRIGGER";
	public static final Level TRIGGER = Level.forName(LOG_LEVEL_TRIGGER, 395);

}
