/**
 *
 */
package com.mevenk.typicalwebapp.logger;

import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.argumentsAsAppendableString;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author venky
 *
 */
@Order(1)
@Aspect
public class TypicalWebAppAutoLogger {

	private static final String POINT_CUT_EXPRESSION_TYPICALWEBAPP_METHODS = "execution(* com.mevenk.typicalwebapp..*(..))";

	private static final String POINT_CUT_TYPICALWEBAPP_METHODS = "typicalWebAppMethods()";

	@Pointcut(POINT_CUT_EXPRESSION_TYPICALWEBAPP_METHODS)
	private void typicalWebAppMethods() {

	}

	@Before(POINT_CUT_TYPICALWEBAPP_METHODS)
	public void logBeforeEnteringMethods(JoinPoint joinPoint) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Entering Method: " + joinPoint.getSignature().getName() + "("
				+ argumentsAsAppendableString(true, joinPoint.getArgs() + ")"));

		getLogger(joinPoint.getTarget().getClass()).info(stringBuilder.toString());
	}

	@AfterReturning(pointcut = POINT_CUT_TYPICALWEBAPP_METHODS, returning = "result")
	public void logAfterReturningFromMethods(JoinPoint joinPoint, Object result) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Exiting Method: " + joinPoint.getSignature().getName());

		stringBuilder.append("Return Value: Has Data?" + result != null);

		getLogger(joinPoint.getTarget().getClass()).info(stringBuilder.toString());

	}

	@AfterThrowing(pointcut = POINT_CUT_TYPICALWEBAPP_METHODS, throwing = "error")
	public void logAfterThrowingFromMethods(JoinPoint joinPoint, Throwable error) {

		Class<? extends Object> errorOrrcuredClass = joinPoint.getTarget().getClass();
		getLogger(errorOrrcuredClass).info("Error Occured Method: " + joinPoint.getSignature().getName());
		getLogger(errorOrrcuredClass).debug("Arguments: " + argumentsAsAppendableString(true, joinPoint.getArgs()));
		getLogger(errorOrrcuredClass).error(error);

	}

}
