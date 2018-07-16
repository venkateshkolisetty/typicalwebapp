/**
 *
 */
package com.mevenk.typicalwebapp.trigger;

import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.TRIGGER;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.exceptionStactTraceAsString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Venkatesh
 *
 */
@Order(4)
@Aspect
public class FileActionsTrigger extends TypicalWebAppBaseTrigger {

	private static final Logger log = LogManager.getLogger(FileActionsTrigger.class);

	protected static final String CORRELATION_ID_PREFIX_FILE_ACTIONS_PAGE = "fileActionsPage";

	private static final String POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE = "initializeFileActionsPage()";

	// *******************************POINTCUTS*******************************

	@Pointcut("execution (* com.mevenk.typicalwebapp.controller.FileActionsController.fileActionsPage(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest))")
	private void initializeFileActionsPage() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************initializeFileActionsPage*******************************

	@Around(POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE)
	protected void logAroundProcessFileActionsPage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.log(TRIGGER, "Trigger Around File Actions Page");
		proceedingJoinPoint.proceed();
	}

	@Before(POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE)
	protected void logPreProcessFileActionsPage(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
	}

	@After(POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE)
	protected void logPostProcessFileActionsPage(JoinPoint joinPoint) {
	}

	@AfterReturning(pointcut = POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE, returning = "retVal")
	protected void logReturnPageFileActionsPage(JoinPoint joinPoint, Object retVal) {
	}

	@AfterThrowing(pointcut = POINT_CUT_INITIALIZE_FILE_ACTIONS_PAGE, throwing = "exception")
	protected void logIfExceptionProcessFileActionsPage(JoinPoint joinPoint, Exception exception) {
		log.log(TRIGGER, "Trigger Exception " + joinPointFormatted + exception.getMessage());
		log.error("Trigger Exception " + joinPointFormatted + exceptionStactTraceAsString(exception));
	}

	// *******************************initializeFileActionsPage-END*******************************

	// *******************************ADVICES-END*******************************
}
