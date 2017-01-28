/**
 * 
 */
package com.mevenk.typicalwebapp.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author VENKATESH
 *
 */
@Component
public class ClientUtilServiceimpl implements ClientUtilService {

	/**
	 * @param httpServletRequest
	 */
	@Override
	public void logRequestDetails(HttpServletRequest httpServletRequest) {
		System.out.println(httpServletRequest.getRequestURL() + TypicalWebAppConstants.tabSpaceWithSingleColun
				+ httpServletRequest.getMethod() + TypicalWebAppConstants.tabSpaceWithDoubleColun + new Date());

		Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
		if (requestParameterMap.size() > 0) {

			System.out.println("Request Parameters");

			for (Entry<String, String[]> currentEntry : requestParameterMap.entrySet()) {
				System.out.println(TypicalWebAppConstants.tabSpace + currentEntry.getKey()
						+ TypicalWebAppConstants.tabSpaceWithSingleColun
						+ Arrays.toString(currentEntry.getValue()).replaceAll("^\\[|\\]$", ""));
			}

		}
	}

	/**
	 * @param httpServletRequest
	 */
	@Override
	public void logClientDetails(HttpServletRequest httpServletRequest) {
		System.out.println(TypicalWebAppConstants.lineSeparator + "Client Details from Headers..."
				+ TypicalWebAppConstants.lineSeparator);
		Enumeration<?> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String paramName = (String) headerNames.nextElement();
			String paramValue = httpServletRequest.getHeader(paramName);
			System.out.println(paramName + TypicalWebAppConstants.tabSpaceWithDoubleColun + paramValue);
		}

		System.out.println(TypicalWebAppConstants.lineSeparator);

		System.out.println("Client Details from UserAgent utils..." + TypicalWebAppConstants.lineSeparator);

		UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));

		System.out.println("Client" + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ httpServletRequest.getRemoteHost() + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ userAgent.getId() + TypicalWebAppConstants.tabSpaceWithDoubleColun + userAgent.toString());
	}

}
