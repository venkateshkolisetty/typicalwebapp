/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.THREAD_CONTEXT_KEY;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.TAB_SPACE_AROUND_DOUBLE_COLUN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.TAB_SPACE_AROUND_SINGLE_COLUN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.formatDateToString;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String applicationStartup(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String sessionId = httpServletRequest.getSession().getId();

		ThreadContext.put(THREAD_CONTEXT_KEY, "*#" + sessionId);

		log.trace("Session Id : {}", sessionId);

		log.debug("Application loaded at : {}{}{}", formatDateToString(new Date()), TAB_SPACE_AROUND_DOUBLE_COLUN,
				httpServletRequest.getRequestURL());

		clientUtilService.logRequestDetails(httpServletRequest);

		clientUtilService.logClientDetails(httpServletRequest);

		log.debug("Redirecting to Welcome Page....");

		return "redirect:/welcome";

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String welcome(ModelMap modelMap, HttpServletRequest httpServletRequest) {

		ThreadContext.put(THREAD_CONTEXT_KEY, "welcome#" + httpServletRequest.getSession().getId());

		log.debug("Welcome Page !!");

		clientUtilService.logRequestDetails(httpServletRequest);

		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "welcome";

	}

	@RequestMapping(value = "testRequestResponse", method = RequestMethod.GET)
	public @ResponseBody String testRequestResponse(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "testRequestResponseParameter1") String testRequestResponseParameter1) {

		//appendCorrelationIdWithPrefix("testRequestResponse#" + testRequestResponseParameter1);

		log.info("Request Parameter : {}", testRequestResponseParameter1);

		clientUtilService.logRequestDetails(httpServletRequest);

		return "Response for calling testRequestResponse with parameter" + TAB_SPACE_AROUND_SINGLE_COLUN
				+ testRequestResponseParameter1;

	}

	@RequestMapping(value = "sleepRequest", method = RequestMethod.GET)
	public @ResponseBody String sleepRequest(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "sleepTimeInSeconds") int sleepTimeInSeconds) {

		String requestReceivedDateString = formatDateToString(new Date());

		//appendCorrelationIdWithPrefix("sleepRequest#" + sleepTimeInSeconds + "#" + requestReceivedDateString);

		log.info("Sleep Request -> Time : {} Request Received : {}", sleepTimeInSeconds, requestReceivedDateString);

		clientUtilService.logClientDetails(httpServletRequest);
		clientUtilService.logRequestDetails(httpServletRequest);

		try {
			Thread.sleep(sleepTimeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "Received : " + requestReceivedDateString + " :: Responded : " + formatDateToString(new Date());
	}

}
