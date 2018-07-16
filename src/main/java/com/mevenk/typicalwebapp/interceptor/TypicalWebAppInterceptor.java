/**
 *
 */
package com.mevenk.typicalwebapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author venky
 *
 */
public class TypicalWebAppInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LogManager.getLogger(TypicalWebAppInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.info("TypicalWebAppInterceptor -preHandle ");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.info("TypicalWebAppInterceptor - postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOG.info("TypicalWebAppInterceptor - afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.info("TypicalWebAppInterceptor - afterConcurrentHandlingStarted");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
