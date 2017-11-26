/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.CONFIG;
import static com.mevenk.typicalwebapp.config.TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_NAME;
import static com.mevenk.typicalwebapp.config.TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_VALUE;
import static com.mevenk.typicalwebapp.config.TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_DISPLAY_NAME;
import static com.mevenk.typicalwebapp.config.TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_NAME;
import static com.mevenk.typicalwebapp.config.TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_URL_PATTERN;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ASTERISK;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SLASH;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Venkatesh
 *
 */
@ApplicationPath(value = TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_URL_PATTERN)

// @WebServlet(name = TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_NAME, displayName =
// TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_DISPLAY_NAME, loadOnStartup = 1,
// initParams = @WebInitParam(name =
// TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_NAME,
// value =
// TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_VALUE),
// urlPatterns = TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_URL_PATTERN)
public class TypicalWebAppRESTWSServlet extends Application {

	private static final Logger log = LogManager.getLogger(TypicalWebAppRESTWSServlet.class);

	static final String TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_NAME = "TypicalWebAppRESTWSServlet";

	static final String TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_DISPLAY_NAME = "TypicalWebApp REST WS Servlet";

	static final String TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_URL_PATTERN = SLASH
			+ TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING + SLASH + ASTERISK;

	static final String TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_NAME = "com.sun.jersey.config.property.packages";

	static final String TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_VALUE = "com.mevenk.typicalwebapp.webservice.rest";

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> getProperties = super.getProperties();
		log.log(CONFIG, "Adding package property to existing properties: {}", getProperties);
		getProperties.put(TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_NAME,
				TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_VALUE);
		return getProperties();
	}
	
}
