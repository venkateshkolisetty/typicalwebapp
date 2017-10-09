/**
 * 
 */
package com.mevenk.typicalwebapp.trigger;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.resetCorrelationId;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.HYPHEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.objectArrayAsString;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppBaseTrigger {

	protected static final String ADVICE_AND = " && ";
	protected static final String ADVICE_OR = " || ";
	protected static final String ADVICE_ANY_ARGS = "args(..)";

	protected String proceedingJoinPointFormatted;
	protected String joinPointFormatted;

	protected void generateCorrelationId(ProceedingJoinPoint proceedingJoinPoint) {
		StringBuilder stringBuilderCorreltionIdPrefix = new StringBuilder();
		stringBuilderCorreltionIdPrefix.append(proceedingJoinPoint.getSignature().getName());
		resetCorrelationId(stringBuilderCorreltionIdPrefix.toString());
	}

	protected void generateJointPointDetail(JoinPoint joinPoint) {
		StringBuilder stringBuilderJoinPoint = new StringBuilder();
		stringBuilderJoinPoint.append(joinPoint.getKind());
		stringBuilderJoinPoint.append(HYPHEN + joinPoint.toShortString());
		joinPointFormatted = stringBuilderJoinPoint.toString();
	}

	protected void generateProceedingJoinPointDetail(ProceedingJoinPoint proceedingJoinPoint) {
		StringBuilder stringBuilderProceedingJoinPoint = new StringBuilder();
		stringBuilderProceedingJoinPoint.append(proceedingJoinPoint.getKind());
		stringBuilderProceedingJoinPoint.append(HYPHEN + proceedingJoinPoint.toShortString());
		stringBuilderProceedingJoinPoint.append(HYPHEN + objectArrayAsString(proceedingJoinPoint.getArgs()));
		proceedingJoinPointFormatted = stringBuilderProceedingJoinPoint.toString();
	}

}
