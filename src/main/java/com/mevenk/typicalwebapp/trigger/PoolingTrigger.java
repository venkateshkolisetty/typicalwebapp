/**
 * 
 */
package com.mevenk.typicalwebapp.trigger;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.TRIGGER;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.exceptionStactTraceAsString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@Aspect
public class PoolingTrigger extends TypicalWebAppBaseTrigger {

	private static final Logger log = LogManager.getLogger(PoolingTrigger.class);

	private static final String POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION = "methodsWithScheduledAnnotation()";

	// *******************************POINTCUTS*******************************

	@Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
	private void methodsWithScheduledAnnotation() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************methodsWithScheduledAnnotation*******************************

	@Around(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logAroundPoolingRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		generateCorrelationId(proceedingJoinPoint);
		generateProceedingJoinPointDetail(proceedingJoinPoint);
		log.log(TRIGGER, "Trigger Around {}", proceedingJoinPointFormatted);
		proceedingJoinPoint.proceed();
	}

	@Before(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logPrePoolingRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
	}

	@After(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logPostPoolingRequest(JoinPoint joinPoint) {
	}

	@AfterReturning(pointcut = POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logReturnPageFileActionsPage(JoinPoint joinPoint) {
	}

	@AfterThrowing(pointcut = POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION, throwing = "exception")
	protected void logIfExceptionPoolingRequest(JoinPoint joinPoint, Exception exception) {
		log.log(TRIGGER, "Trigger Exception " + joinPointFormatted + exception.getMessage());
		log.error("Trigger Exception " + joinPointFormatted + exceptionStactTraceAsString(exception));
	}

	// *******************************methodsWithScheduledAnnotation-END*******************************

	// *******************************ADVICES-END*******************************
}
