/**
 * 
 */
package com.mevenk.typicalwebapp.trigger.impl;

import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.TRIGGER;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.mevenk.typicalwebapp.trigger.FileActionsTrigger;

/**
 * @author Venkatesh
 *
 */
public class FileAcionsLoggingTrigger extends FileActionsTrigger {

	private static final Logger log = LogManager.getLogger(FileAcionsLoggingTrigger.class);

	public void logPreProcessFileActionsPage(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
		log.log(TRIGGER, "File Actions Page requested!! Performing pre-processing activities : " + joinPointFormatted);
	}

	public void logPostProcessFileActionsPage(JoinPoint joinPoint) {
		log.log(TRIGGER, "Performing post-processing activities : " + joinPointFormatted);
	}

	public void logReturnPageFileActionsPage(JoinPoint joinPoint, Object retVal) {
		log.log(TRIGGER,
				"Returned view : " + joinPointFormatted + " :: " + (joinPoint == null) + " :: " + (retVal == null));
		// log.log(TRIGGER, "Returned view : " + retVal.toString());
	}
}
