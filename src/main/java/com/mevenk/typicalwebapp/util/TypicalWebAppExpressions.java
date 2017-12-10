/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import com.mevenk.typicalwebapp.exception.DisgracedConstructorInvocationException;

/**
 * @author Venkatesh
 *
 */
public final class TypicalWebAppExpressions {

	private TypicalWebAppExpressions() {
		throw new DisgracedConstructorInvocationException(TypicalWebAppExpressions.class);
	}

	public static final String DATE_POLLING_CRON_EXPRESSION = "${datePollingCronExpression}";

}
