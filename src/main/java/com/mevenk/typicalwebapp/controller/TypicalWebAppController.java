/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

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
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;
import com.mevenk.typicalwebapp.util.TypicalWebAppUtil;

/**
 * @author VENKATESH
 *
 */
@Controller()
public class TypicalWebAppController {

	private static final Logger log = LogManager.getLogger(TypicalWebAppController.class);

	private static final String LINE_SEPARATOR = TypicalWebAppConstants.lineSeparator;

	@Autowired
	ClientUtilService clientUtilService;

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String applicationStartup(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ThreadContext.put("TypicalWebAppCorrelationId", "*#" + httpServletRequest.getSession().getId());

		log.debug(LINE_SEPARATOR + "Application loaded at : " + new Date()
				+ TypicalWebAppConstants.tabSpaceWithDoubleColun + httpServletRequest.getRequestURL() + LINE_SEPARATOR);

		clientUtilService.logRequestDetails(httpServletRequest);

		clientUtilService.logClientDetails(httpServletRequest);

		log.debug(LINE_SEPARATOR + "Redirecting to Welcome Page...." + LINE_SEPARATOR);

		return "redirect:/welcome";

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String fileUpload(ModelMap modelMap, HttpServletRequest httpServletRequest) {

		ThreadContext.put("TypicalWebAppCorrelationId", "welcome#" + httpServletRequest.getSession().getId());

		log.debug("Welcome Page !!");

		clientUtilService.logRequestDetails(httpServletRequest);

		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "welcome";

	}

	@RequestMapping(value = "testRequestResponse", method = RequestMethod.GET)
	public @ResponseBody String testRequestResponse(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "testRequestResponseParameter1") String testRequestResponseParameter1) {

		ThreadContext.put("TypicalWebAppCorrelationId", "testRequestResponse#" + testRequestResponseParameter1
				+ TypicalWebAppUtil.dateForCorrelationId(new Date()));

		log.info("Request Parameter : " + testRequestResponseParameter1);

		clientUtilService.logRequestDetails(httpServletRequest);

		return "Response for calling testRequestResponse with parameter"
				+ TypicalWebAppConstants.tabSpaceWithSingleColun + testRequestResponseParameter1;

	}

	@RequestMapping(value = "sleepRequest", method = RequestMethod.GET)
	public @ResponseBody String sleepRequest(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "sleepTimeInSeconds") int sleepTimeInSeconds) {

		ThreadContext.put("TypicalWebAppCorrelationId", "sleepRequest#" + sleepTimeInSeconds);

		Date requestReceivedDate = new Date();

		log.info("Sleep Request -> Time : " + sleepTimeInSeconds + " Request Received : " + requestReceivedDate
				+ TypicalWebAppUtil.dateForCorrelationId(new Date()));

		clientUtilService.logClientDetails(httpServletRequest);
		clientUtilService.logRequestDetails(httpServletRequest);

		try {
			Thread.sleep(sleepTimeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "Received : " + requestReceivedDate + " :: Responded : " + new Date();
	}

}
