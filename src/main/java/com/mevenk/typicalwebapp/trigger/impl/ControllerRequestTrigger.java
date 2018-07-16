/**
 * 
 */
package com.mevenk.typicalwebapp.trigger.impl;

import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.TRIGGER;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.mevenk.typicalwebapp.trigger.ControllerTrigger;

/**
 * @author Venkatesh
 *
 */
public class ControllerRequestTrigger extends ControllerTrigger {

	private static final Logger log = LogManager.getLogger(ControllerRequestTrigger.class);

	@Override
	protected void logPreControllerRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
		log.log(TRIGGER, "Performing Pre Request Activities {}", joinPointFormatted);
	}

	@Override
	protected void logPostControllerRequest(JoinPoint joinPoint) {
		log.log(TRIGGER, "Performing Post Request Activities {}", joinPointFormatted);
	}

}
