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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger log = LogManager.getLogger(ClientUtilServiceimpl.class);

	private static final String LINE_SEPARATOR = TypicalWebAppConstants.lineSeparator;

	/**
	 * @param httpServletRequest
	 */
	@Override
	public void logRequestDetails(HttpServletRequest httpServletRequest) {

		log.trace(httpServletRequest.getRequestURL() + TypicalWebAppConstants.tabSpaceWithSingleColun
				+ httpServletRequest.getMethod() + TypicalWebAppConstants.tabSpaceWithDoubleColun + new Date());

		Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
		if (requestParameterMap.size() > 0) {

			StringBuffer requestParametersStringBuffer = new StringBuffer();

			for (Entry<String, String[]> currentEntry : requestParameterMap.entrySet()) {
				requestParametersStringBuffer.append(TypicalWebAppConstants.tabSpace + currentEntry.getKey()
						+ TypicalWebAppConstants.tabSpaceWithSingleColun
						+ Arrays.toString(currentEntry.getValue()).replaceAll("^\\[|\\]$", "") + LINE_SEPARATOR);
			}

			log.trace("Request Parameters" + LINE_SEPARATOR + requestParametersStringBuffer.toString());

		}
	}

	/**
	 * @param httpServletRequest
	 */
	@Override
	public void logClientDetails(HttpServletRequest httpServletRequest) {

		UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));

		log.trace("Client" + TypicalWebAppConstants.tabSpaceWithDoubleColun + httpServletRequest.getRemoteHost()
				+ TypicalWebAppConstants.tabSpaceWithDoubleColun + userAgent.getId()
				+ TypicalWebAppConstants.tabSpaceWithDoubleColun + userAgent.toString());

		StringBuffer clientDetailsStringBuffer = new StringBuffer();

		Enumeration<?> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String paramName = (String) headerNames.nextElement();
			String paramValue = httpServletRequest.getHeader(paramName);
			clientDetailsStringBuffer
					.append(paramName + TypicalWebAppConstants.tabSpaceWithDoubleColun + paramValue + LINE_SEPARATOR);
		}

		log.trace("Client Details from Headers..." + LINE_SEPARATOR + clientDetailsStringBuffer.toString());

	}

}
