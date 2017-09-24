/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import org.apache.logging.log4j.Level;

/**
 * @author Venkatesh
 *
 */
public interface TypicalWebAppLogger {

	String THREAD_CONTEXT_KEY = "TypicalWebAppCorrelationId";

	Level POOLING = Level.forName("POOLING", 490);

}
