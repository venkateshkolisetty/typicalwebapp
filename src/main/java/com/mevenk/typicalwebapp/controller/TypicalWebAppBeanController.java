/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService.ADD_ERROR;
import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService.ADD_SUCCESS;
import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService.AVAILABLE;
import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService.NOT_AVAILABLE;
import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.addParametersToCorrelationId;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.PARAM_TYPICAL_WEB_APP_BEAN_ID;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.headerTypicalWebAppBean;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;
import com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService;
import com.mevenk.typicalwebapp.controller.response.ResponseEntityTypicalWebAppBean;
import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;

/**
 * @author Venkatesh
 *
 */
@Controller()
public class TypicalWebAppBeanController {

	private static final Logger log = LogManager.getLogger(TypicalWebAppBeanController.class);

	@Autowired
	private TypicalWebAppBeanService typicalWebAppBeanService;

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING, method = HEAD)
	public void isTypicalWebAppBeanAvailable(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "typicalWebAppBeanId", required = true) int typicalWebAppBeanId) {
		log.debug("Received verify availability request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		boolean isTypicalWebAppBeanAvailable = typicalWebAppBeanService
				.isTypicalWebAppBeanAvailable(typicalWebAppBeanId);
		HttpStatus httpStatusReturn = isTypicalWebAppBeanAvailable ? OK : NOT_FOUND;
		log.info("Verify Request for {}; Found?{}; Responded:{}", typicalWebAppBeanId, isTypicalWebAppBeanAvailable,
				httpStatusReturn);
		httpServletResponse.setStatus(httpStatusReturn.value());
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING, method = DELETE)
	public void deleteTypicalWebAppBean(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "typicalWebAppBeanId", required = true) int typicalWebAppBeanId) {
		log.debug("Received Delete request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		boolean isTypicalWebAppBeanAvailable = typicalWebAppBeanService.deleteTypicalWebAppBean(typicalWebAppBeanId);
		HttpStatus httpStatusReturn = isTypicalWebAppBeanAvailable ? OK : NOT_FOUND;
		log.info("Delete Request for {}; Found?{}; Responded:{}", typicalWebAppBeanId, isTypicalWebAppBeanAvailable,
				httpStatusReturn);
		httpServletResponse.setStatus(httpStatusReturn.value());
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING, method = PUT)
	public @ResponseBody ResponseEntityTypicalWebAppBean addTypicalWebAppBean(ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		log.debug("Received add request");
		TypicalWebAppBeanInvocationService typicalWebAppBeanInvocationService = null;
		HttpStatus httpStatusReturn = null;
		TypicalWebAppBean typicalWebAppBean = typicalWebAppBeanService.addTypicalWebAppBean();
		if (typicalWebAppBean != null) {
			httpStatusReturn = OK;
			typicalWebAppBeanInvocationService = ADD_SUCCESS;
		} else {
			httpStatusReturn = BAD_REQUEST;
			typicalWebAppBeanInvocationService = ADD_ERROR;
		}
		log.info("Bean:{}, responded:{}", typicalWebAppBean, httpStatusReturn);
		httpServletResponse.setStatus(httpStatusReturn.value());

		return new ResponseEntityTypicalWebAppBean(typicalWebAppBean,
				headerTypicalWebAppBean(typicalWebAppBeanInvocationService), httpStatusReturn);
	}

	@RequestMapping(path = TYPICAL_WEB_APP_BEAN_CONTROLLER_REQUEST_MAPPING, method = GET)
	public @ResponseBody ResponseEntityTypicalWebAppBean getTypicalWebAppBean(ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestParam(name = PARAM_TYPICAL_WEB_APP_BEAN_ID, required = true) int typicalWebAppBeanId) {
		log.debug("Received retreive request for {}", typicalWebAppBeanId);
		addParametersToCorrelationId(typicalWebAppBeanId);
		TypicalWebAppBean typicalWebAppBean = typicalWebAppBeanService.getTypicalWebAppBean(typicalWebAppBeanId);
		TypicalWebAppBeanInvocationService typicalWebAppBeanInvocationService = null;
		HttpStatus httpStatusReturn = null;
		if (typicalWebAppBean != null) {
			httpStatusReturn = OK;
			typicalWebAppBeanInvocationService = AVAILABLE;
		} else {
			httpStatusReturn = NOT_FOUND;
			typicalWebAppBeanInvocationService = NOT_AVAILABLE;
		}
		log.info("Bean id: {}; Bean:{}; Responded:{}", typicalWebAppBeanId, typicalWebAppBean, httpStatusReturn);
		httpServletResponse.setStatus(httpStatusReturn.value());
		return new ResponseEntityTypicalWebAppBean(typicalWebAppBean,
				headerTypicalWebAppBean(typicalWebAppBeanInvocationService), httpStatusReturn);
	}
}
