/**
 * 
 */
package com.mevenk.typicalwebapp.webservice.rest.controller;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.PARAM_TYPICAL_WEB_APP_BEAN_ID;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_BEAN_REST_WEB_SERVICE_REQUEST_PATH;
import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.addParametersToCorrelationId;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.BRACES_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.BRACES_OPEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SLASH;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;
import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;

/**
 * @author Venkatesh
 *
 */
@Path(TYPICAL_WEB_APP_BEAN_REST_WEB_SERVICE_REQUEST_PATH)
public class TypicalWebAppBeanRESTWSController {

	private static final Logger log = LogManager.getLogger(TypicalWebAppBeanRESTWSController.class);

	@Autowired
	private TypicalWebAppBeanService typicalWebAppBeanService;

	@GET
	@Path(SLASH + BRACES_OPEN + PARAM_TYPICAL_WEB_APP_BEAN_ID + BRACES_CLOSE)
	@Produces(APPLICATION_XML)
	public TypicalWebAppBean getTypicalWebAppBean(@PathParam(PARAM_TYPICAL_WEB_APP_BEAN_ID) int typicalWebAppBeanId) {
		addParametersToCorrelationId(typicalWebAppBeanId);
		TypicalWebAppBean typicalWebAppBean = typicalWebAppBeanService.getTypicalWebAppBean(typicalWebAppBeanId);
		// HttpStatus httpStatusReturn = typicalWebAppBean != null ? OK : NOT_FOUND;
		log.info("Bean id: {}; Bean:{}; Responded:{}", typicalWebAppBeanId, typicalWebAppBean);
		return typicalWebAppBean;
	}

}
