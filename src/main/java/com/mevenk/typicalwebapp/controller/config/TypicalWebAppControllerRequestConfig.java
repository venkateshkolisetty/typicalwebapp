/**
 * 
 */
package com.mevenk.typicalwebapp.controller.config;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SLASH;

import com.mevenk.typicalwebapp.exception.DisgracedConstructorInvocationException;

/**
 * @author Venkatesh
 *
 */
public final class TypicalWebAppControllerRequestConfig {

	private TypicalWebAppControllerRequestConfig() {
		throw new DisgracedConstructorInvocationException(this.getClass());
	}

	public static final String TYPICAL_WEB_APP = "typicalwebapp";

	public static final String TYPICAL_WEB_APP_REST_CONTROLLER_REQUEST_MAPPING = "rc";
	public static final String TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING = "rws";
	public static final String TYPICAL_WEB_APP_SOAP_WEB_SERVICE_REQUEST_MAPPING = "sws";

	public static final String TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING = "typicalwebappbean";

	public static final String TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING = TYPICAL_WEB_APP_REST_CONTROLLER_REQUEST_MAPPING
			+ SLASH + TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING;
	public static final String TYPICAL_WEB_APP_BEAN_REST_WEB_SERVICE_REQUEST_PATH = SLASH
			+ TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING + SLASH
			+ TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING;
	public static final String TYPICAL_WEB_APP_BEAN_SOAP_WEB_SERVICE_REQUEST_PATH = SLASH
			+ TYPICAL_WEB_APP_SOAP_WEB_SERVICE_REQUEST_MAPPING + SLASH
			+ TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING;

	public static final String TYPICAL_WEB_APP_REQUEST_MAPPING_WELCOME = "welcome";
	public static final String TYPICAL_WEB_APP_REQUEST_MAPPING_TEST_REQUEST_RESPONSE = "testRequestResponse";
	public static final String TYPICAL_WEB_APP_REQUEST_MAPPING_SLEEP_REQUEST = "sleepRequest";

	public static final String PARAM_TEST_REQUEST_RESPONSE_PARAMETER_1 = "testRequestResponseParameter1";
	public static final String PARAM_SLEEP_TIME_IN_SECONDS = "sleepTimeInSeconds";
	public static final String PARAM_TYPICAL_WEB_APP_BEAN_ID = "typicalWebAppBeanId";

}
