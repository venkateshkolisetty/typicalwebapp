/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;

/**
 * @author VENKATESH
 *
 */
@Controller()
public class TypicalWebAppController {

	@Autowired
	ClientUtilService clientUtilService;

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String applicationStartup(ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		System.out.println(TypicalWebAppConstants.lineSeparator + "Application loaded at : " + new Date()
				+ TypicalWebAppConstants.tabSpaceWithDoubleColun + httpServletRequest.getRequestURL()
				+ TypicalWebAppConstants.lineSeparator);

		clientUtilService.logRequestDetails(httpServletRequest);

		clientUtilService.logClientDetails(httpServletRequest);

		System.out.println(TypicalWebAppConstants.lineSeparator + "Redirecting to Welcome Page...."
				+ TypicalWebAppConstants.lineSeparator);

		return "redirect:/welcome";

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String fileUpload(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		clientUtilService.logRequestDetails(httpServletRequest);
		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "welcome";

	}

	@RequestMapping(value = "testRequestResponse", method = RequestMethod.GET)
	public @ResponseBody String testRequestResponse(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "testRequestResponseParameter1") String testRequestResponseParameter1) {
		clientUtilService.logRequestDetails(httpServletRequest);
		return "Response for calling testRequestResponse with parameter"
				+ TypicalWebAppConstants.tabSpaceWithSingleColun + testRequestResponseParameter1;

	}

	@RequestMapping(value = "sleepRequest", method = RequestMethod.GET)
	public @ResponseBody String sleepRequest(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "sleepTimeInSeconds") int sleepTimeInSeconds) {
		Date requestReceivedDate = new Date();
		
		clientUtilService.logClientDetails(httpServletRequest);
		clientUtilService.logRequestDetails(httpServletRequest);

		try {
			Thread.sleep(sleepTimeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}

		return "Received : " + requestReceivedDate + " :: Responded : " + new Date();
	}

}
