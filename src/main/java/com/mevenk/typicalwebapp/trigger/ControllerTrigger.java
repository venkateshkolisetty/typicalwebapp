/**
 * 
 */
package com.mevenk.typicalwebapp.trigger;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.TRIGGER;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_OPEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.exceptionStactTraceAsString;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.objectArrayAsString;
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
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Venkatesh
 *
 */
@Aspect
public class ControllerTrigger extends TypicalWebAppBaseTrigger {

	private static final Logger log = LogManager.getLogger(ControllerTrigger.class);

	protected String requestMappingFormatted;
	private static final String POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION = "methodsWithRequestMappingAnnotation()";

	// *******************************POINTCUTS*******************************

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void methodsWithRequestMappingAnnotation() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************methodsWithRequestMappingAnnotation*******************************

	@Around(POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logAroundControllerRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		generateCorrelationId(proceedingJoinPoint);
		generateProceedingJoinPointDetail(proceedingJoinPoint);
		log.log(TRIGGER, "Trigger Around {}", proceedingJoinPointFormatted);
		log.log(TRIGGER, "Request {}", generateRequestMappingFormatted(proceedingJoinPoint));
		proceedingJoinPoint.proceed();
	}

	@Before(POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logPreControllerRequest(JoinPoint joinPoint) {
		generateJointPointDetail(joinPoint);
	}

	@After(POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION)
	protected void logPostControllerRequest(JoinPoint joinPoint) {
	}

	@AfterReturning(pointcut = POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION, returning = "retVal")
	protected void logReturnPageFileActionsPage(JoinPoint joinPoint, Object retVal) {
	}

	@AfterThrowing(pointcut = POINT_CUT_METHODS_WITH_REQUEST_MAPPING_ANNOTATION, throwing = "exception")
	protected void logIfExceptionControllerRequest(JoinPoint joinPoint, Exception exception) {
		log.log(TRIGGER, "Trigger Exception " + joinPointFormatted + exception.getMessage());
		log.error("Trigger Exception " + joinPointFormatted + exceptionStactTraceAsString(exception));
	}

	// *******************************methodsWithRequestMappingAnnotation-END*******************************

	// *******************************ADVICES-END*******************************

	private String generateRequestMappingFormatted(JoinPoint joinPoint) {
		StringBuilder stringBuilderRequestMappingParams = new StringBuilder();
		MethodSignature methodSignatureRequestMapping = (MethodSignature) joinPoint.getSignature();
		RequestMapping requestMapping = methodSignatureRequestMapping.getMethod().getAnnotation(RequestMapping.class);
		stringBuilderRequestMappingParams
				.append(SQUARE_BRACKET_OPEN + objectArrayAsString(requestMapping.value()) + SQUARE_BRACKET_CLOSE);
		stringBuilderRequestMappingParams
				.append(SQUARE_BRACKET_OPEN + objectArrayAsString(requestMapping.method()) + SQUARE_BRACKET_CLOSE);
		stringBuilderRequestMappingParams
				.append(SQUARE_BRACKET_OPEN + objectArrayAsString(requestMapping.headers()) + SQUARE_BRACKET_CLOSE);
		stringBuilderRequestMappingParams
				.append(SQUARE_BRACKET_OPEN + objectArrayAsString(requestMapping.params()) + SQUARE_BRACKET_CLOSE);
		stringBuilderRequestMappingParams
				.append(SQUARE_BRACKET_OPEN + objectArrayAsString(requestMapping.produces()) + SQUARE_BRACKET_CLOSE);
		requestMappingFormatted = stringBuilderRequestMappingParams.toString();
		return requestMappingFormatted;
	}

}
