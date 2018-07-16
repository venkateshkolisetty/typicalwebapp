/**
 * 
 */
package com.mevenk.typicalwebapp.trigger.impl;

import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.TRIGGER;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.mevenk.typicalwebapp.trigger.PollingTrigger;

/**
 * @author Venkatesh
 *
 */
public class TimelyDatePollingTrigger extends PollingTrigger {

	private static final Logger log = LogManager.getLogger(TimelyDatePollingTrigger.class);

	@Override
	protected void logPrePollingRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
		log.log(TRIGGER, "Performing Pre Request Activities {}", joinPointFormatted);
	}

	@Override
	protected void logPostPollingRequest(JoinPoint joinPoint) {
		log.log(TRIGGER, "Performing Post Request Activities {}", joinPointFormatted);
	}

}
