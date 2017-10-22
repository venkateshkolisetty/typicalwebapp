/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.config.TypicalWebAppControllerConfig.TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING;
import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.addParametersToCorrelationId;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;
import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;

/**
 * @author Venkatesh
 *
 */
@RestController()
public class TypicalWebAppBeanRESTController {

	private static final Logger log = LogManager.getLogger(TypicalWebAppBeanRESTController.class);

	@Autowired
	private TypicalWebAppBeanService typicalWebAppBeanService;

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING, method = HEAD)
	public BodyBuilder isTypicalWebAppBeanAvailable(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "typicalWebAppBeanId", required = true) int typicalWebAppBeanId) {
		log.debug("Received verify availability request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		boolean isTypicalWebAppBeanAvailable = typicalWebAppBeanService
				.isTypicalWebAppBeanAvailable(typicalWebAppBeanId);
		HttpStatus httpStatusReturn = isTypicalWebAppBeanAvailable ? OK : NOT_FOUND;
		log.info("Verify Request for {}; Found?{}; Responded:{}", typicalWebAppBeanId, isTypicalWebAppBeanAvailable,
				httpStatusReturn);
		return ResponseEntity.status(httpStatusReturn);
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING, method = DELETE)
	public BodyBuilder deleteTypicalWebAppBean(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "typicalWebAppBeanId", required = true) int typicalWebAppBeanId) {
		log.debug("Received Delete request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		boolean isTypicalWebAppBeanAvailable = typicalWebAppBeanService.deleteTypicalWebAppBean(typicalWebAppBeanId);
		HttpStatus httpStatusReturn = isTypicalWebAppBeanAvailable ? OK : NOT_FOUND;
		log.info("Delete Request for {}; Found?{}; Responded:{}", typicalWebAppBeanId, isTypicalWebAppBeanAvailable,
				httpStatusReturn);
		return ResponseEntity.status(httpStatusReturn);
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING, method = PUT)
	public ResponseEntity<String> addTypicalWebAppBean(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		log.debug("Received add request");
		String responseString = "";
		int typicalWebAppBeanId = typicalWebAppBeanService.addTypicalWebAppBean();
		if (typicalWebAppBeanId != -1) {
			responseString = "Add Success";
		} else {
			responseString = "Add Error";
		}
		HttpStatus httpStatusReturn = (typicalWebAppBeanId != -1) ? OK : BAD_REQUEST;
		log.info("Bean id:{}, responded:{}", typicalWebAppBeanId, httpStatusReturn);
		return ResponseEntity.status(httpStatusReturn).body(responseString);
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_REST_CONTROLLER_REQUEST_MAPPING, method = GET)
	public @ResponseBody TypicalWebAppBean getTypicalWebAppBean(ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestParam(name = "typicalWebAppBeanId", required = true) int typicalWebAppBeanId) {
		log.debug("Received retreive request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		TypicalWebAppBean typicalWebAppBean = typicalWebAppBeanService.getTypicalWebAppBean(typicalWebAppBeanId);
		HttpStatus httpStatusReturn = typicalWebAppBean != null ? OK : NOT_FOUND;
		log.info("Bean:{}; Responded:{}", typicalWebAppBeanId, typicalWebAppBean, httpStatusReturn);
		// return ResponseEntity.status(httpStatusReturn).body(typicalWebAppBean);
		return typicalWebAppBean;
	}
}
