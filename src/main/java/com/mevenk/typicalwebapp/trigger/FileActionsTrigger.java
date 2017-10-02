/**
 * 
 */
package com.mevenk.typicalwebapp.trigger;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.THREAD_CONTEXT_KEY;
import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.TRIGGER;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Venkatesh
 *
 */
@Aspect
public class FileActionsTrigger {

	private static final Logger log = LogManager.getLogger(FileActionsTrigger.class);

	private static final String POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE = "initializeFileActionsPage()";

	@Pointcut("execution (* com.mevenk.typicalwebapp.controller.FileActionsController.fileActionsPage(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest))")
	private void initializeFileActionsPage() {
		ThreadContext.put(THREAD_CONTEXT_KEY, "TRIGGER_" + this.getClass().getSimpleName());
		log.log(TRIGGER, "File Actions Page requested!!");
	}

	@Before(POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE)
	private void logPreProcessFileActionsPage() {
		log.log(TRIGGER, "Performing pre-processing activities");
	}

	@After(POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE)
	private void logPostProcessFileActionsPage() {
		log.log(TRIGGER, "Performing post-processing activities");
	}

	@AfterReturning(pointcut = POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE, returning = "retVal")
	private void logReturnPageFileActionsPage(Object retVal) {
		log.log(TRIGGER, "Returned view : " + retVal.toString());
	}
}
