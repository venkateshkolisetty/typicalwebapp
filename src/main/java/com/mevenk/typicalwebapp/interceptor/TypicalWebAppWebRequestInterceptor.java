/**
 *
 */
package com.mevenk.typicalwebapp.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author venky
 *
 */
public class TypicalWebAppWebRequestInterceptor implements WebRequestInterceptor {

	private static final Logger LOG = LogManager.getLogger(TypicalWebAppWebRequestInterceptor.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#preHandle(org.
	 * springframework.web.context.request.WebRequest)
	 */
	@Override
	public void preHandle(WebRequest request) throws Exception {
		LOG.info("TypicalWebAppWebRequestInterceptor - preHandle");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#postHandle(org.
	 * springframework.web.context.request.WebRequest,
	 * org.springframework.ui.ModelMap)
	 */
	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		LOG.info("TypicalWebAppWebRequestInterceptor - postHandle");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#afterCompletion
	 * (org.springframework.web.context.request.WebRequest, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		LOG.info("TypicalWebAppWebRequestInterceptor - afterCompletion");

	}

}
