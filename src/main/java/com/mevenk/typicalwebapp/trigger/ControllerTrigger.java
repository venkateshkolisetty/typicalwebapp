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
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Venkatesh
 *
 */
public class ControllerTrigger extends TypicalWebAppBaseTrigger {

	private static final Logger log = LogManager.getLogger(ControllerTrigger.class);

	private static final String WITHIN_CONTROLLER_PACKAGE = "withinControllerPackage()";
	private static final String ALL_CONTROLLER_CLASS_METHODS = "allControllerClassMethods()";
	private static final String METHODS_WITH_REQUEST_MAPPING_ANNOTATION = "methodsWithRequestMappingAnnotation()";

	// *******************************POINTCUTS*******************************

	@Pointcut("@within (com.mevenk.typicalwebapp.controller..*))")
	private void withinControllerPackage() {

	}

	@Pointcut("execution(* * *(..))")
	private void allControllerClassMethods() {

	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping))")
	private void methodsWithRequestMappingAnnotation() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************allControllersWithinControllerPackageWithRequestMappingMethods*******************************

	@Around(METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logAroundControllerRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		generateCorrelationId(proceedingJoinPoint);
		generateProceedingJoinPointDetail(proceedingJoinPoint);
		log.log(TRIGGER, "Trigger Around {}", proceedingJoinPointFormatted);
		proceedingJoinPoint.proceed();
	}

	@Before(WITHIN_CONTROLLER_PACKAGE + ADVICE_AND + ALL_CONTROLLER_CLASS_METHODS + ADVICE_AND
			+ METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logPreControllerRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
	}

	@After(WITHIN_CONTROLLER_PACKAGE + ADVICE_AND + ALL_CONTROLLER_CLASS_METHODS + ADVICE_AND
			+ METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logPostControllerRequest(JoinPoint joinPoint) {
	}

	@AfterReturning(pointcut = WITHIN_CONTROLLER_PACKAGE + ADVICE_AND + ALL_CONTROLLER_CLASS_METHODS + ADVICE_AND
			+ METHODS_WITH_REQUEST_MAPPING_ANNOTATION, returning = "retVal")
	protected void logReturnPageFileActionsPage(JoinPoint joinPoint, Object retVal) {
	}

	@AfterThrowing(pointcut = WITHIN_CONTROLLER_PACKAGE + ADVICE_AND + ALL_CONTROLLER_CLASS_METHODS + ADVICE_AND
			+ METHODS_WITH_REQUEST_MAPPING_ANNOTATION, throwing = "exception")
	protected void logIfExceptionControllerRequest(JoinPoint joinPoint, Exception exception) {
		log.log(TRIGGER, "Trigger Exception " + joinPointFormatted + exception.getMessage());
		log.error("Trigger Exception " + joinPointFormatted + exceptionStactTraceAsString(exception));
	}

	// *******************************allControllersWithinControllerPackageWithRequestMappingMethods-END*******************************

	// *******************************ADVICES-END*******************************

}
