/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.PARAM_SLEEP_TIME_IN_SECONDS;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.PARAM_TEST_REQUEST_RESPONSE_PARAMETER_1;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_REQUEST_MAPPING_SLEEP_REQUEST;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_REQUEST_MAPPING_TEST_REQUEST_RESPONSE;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP_REQUEST_MAPPING_WELCOME;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.TYPICAL_WEB_APP_RESPONSE_VIEW_WELCOME;
import static com.mevenk.typicalwebapp.controller.response.ResponseEntityString.responseEntityStringError;
import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.THREAD_CONTEXT_KEY;
import static com.mevenk.typicalwebapp.logger.TypicalWebAppLogger.addParametersToCorrelationId;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ASTERISK;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SLASH;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SPACE_AROUND_DOUBLE_COLUN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SPACE_AROUND_SINGLE_COLUN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.appendSuffixPoundSign;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.formatDateToString;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mevenk.typicalwebapp.controller.response.ResponseEntityString;
import com.mevenk.typicalwebapp.service.ClientUtilService;

/**
 * @author VENKATESH
 *
 */
@Controller()
public class TypicalWebAppController {

	private static final Logger log = LogManager.getLogger(TypicalWebAppController.class);

	@Autowired
	ClientUtilService clientUtilService;

	@RequestMapping(value = SLASH, method = GET)
	public void applicationStartup(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String sessionId = httpServletRequest.getSession().getId();

		ThreadContext.put(THREAD_CONTEXT_KEY, appendSuffixPoundSign(ASTERISK) + sessionId);

		log.trace("Session Id : {}", sessionId);

		log.debug("Application loaded at : {}{}{}", formatDateToString(new Date()), SPACE_AROUND_DOUBLE_COLUN,
				httpServletRequest.getRequestURL());

		clientUtilService.logRequestDetails(httpServletRequest);

		clientUtilService.logClientDetails(httpServletRequest);

		log.debug("Redirecting to Welcome Page....");

		httpServletResponse.sendRedirect(TYPICAL_WEB_APP_REQUEST_MAPPING_WELCOME);

	}

	@RequestMapping(value = TYPICAL_WEB_APP_REQUEST_MAPPING_WELCOME, method = GET)
	public String welcome(ModelMap modelMap, HttpServletRequest httpServletRequest) {

		ThreadContext.put(THREAD_CONTEXT_KEY, appendSuffixPoundSign(TYPICAL_WEB_APP_REQUEST_MAPPING_WELCOME)
				+ httpServletRequest.getSession().getId());

		log.debug("Welcome Page !!");

		clientUtilService.logRequestDetails(httpServletRequest);

		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return TYPICAL_WEB_APP_RESPONSE_VIEW_WELCOME;

	}

	@RequestMapping(value = TYPICAL_WEB_APP_REQUEST_MAPPING_TEST_REQUEST_RESPONSE, method = GET)
	public @ResponseBody ResponseEntityString testRequestResponse(ModelMap modelMap,
			HttpServletRequest httpServletRequest,
			@RequestParam(name = PARAM_TEST_REQUEST_RESPONSE_PARAMETER_1) String testRequestResponseParameter1) {

		log.info("Request Parameter : {}", testRequestResponseParameter1);

		clientUtilService.logRequestDetails(httpServletRequest);

		return new ResponseEntityString("Response for calling testRequestResponse with parameter"
				+ SPACE_AROUND_SINGLE_COLUN + testRequestResponseParameter1, OK);

	}

	@RequestMapping(value = TYPICAL_WEB_APP_REQUEST_MAPPING_SLEEP_REQUEST, method = GET)
	public @ResponseBody ResponseEntityString sleepRequest(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = PARAM_SLEEP_TIME_IN_SECONDS) int sleepTimeInSeconds) {

		addParametersToCorrelationId(sleepTimeInSeconds);

		String requestReceivedDateString = formatDateToString(new Date());

		log.info("Sleep Request -> Time : {} Request Received : {}", sleepTimeInSeconds, requestReceivedDateString);

		clientUtilService.logClientDetails(httpServletRequest);
		clientUtilService.logRequestDetails(httpServletRequest);

		try {
			Thread.sleep(sleepTimeInSeconds * 1_000L);
		} catch (InterruptedException interruptedException) {
			Thread.currentThread().interrupt();
			return responseEntityStringError();
		}

		return new ResponseEntityString(
				"Received : " + requestReceivedDateString + " :: Responded : " + formatDateToString(new Date()), OK);
	}

}
