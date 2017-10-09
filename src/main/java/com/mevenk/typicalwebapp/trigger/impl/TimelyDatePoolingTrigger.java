/**
 * 
 */
package com.mevenk.typicalwebapp.trigger.impl;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.TRIGGER;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.mevenk.typicalwebapp.trigger.PoolingTrigger;

/**
 * @author Venkatesh
 *
 */
public class TimelyDatePoolingTrigger extends PoolingTrigger {

	private static final Logger log = LogManager.getLogger(TimelyDatePoolingTrigger.class);

	protected void logPrePoolingRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
		log.log(TRIGGER, "Performing Pre Request Activities {}", joinPointFormatted);
	}

	protected void logPostPoolingRequest(JoinPoint joinPoint) {
		log.log(TRIGGER, "Performing Post Request Activities {}", joinPointFormatted);
	}

}
