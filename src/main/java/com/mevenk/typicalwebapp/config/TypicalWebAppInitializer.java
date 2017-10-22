package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppControllerConfig.TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ASTERISK;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SLASH;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class TypicalWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	static final String PROFILE_TYPICAL_WEB_APP_ROOT_CONFIGURATION = "TypicalWebAppRoot";
	static final String PROFILE_TYPICAL_WEB_APP_SERVLET_CONFIGURATION = "TypicalWebAppServlet";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext webApplicationRootContext = getRootContext();
		WebApplicationContext webApplicationDispatcherContext = getDispatcherContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationRootContext));

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationDispatcherContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic dispatcherDynamicServlet = servletContext.addServlet("DispatcherServlet",
				dispatcherServlet);
		dispatcherDynamicServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		dispatcherDynamicServlet.addMapping(SLASH);
		dispatcherDynamicServlet.setAsyncSupported(true);
		dispatcherDynamicServlet.setLoadOnStartup(1);

		ServletRegistration.Dynamic defaultServlet = servletContext.addServlet("default", new DefaultServlet());

		defaultServlet.addMapping("*.js");
		defaultServlet.addMapping("*.css");
		defaultServlet.addMapping("*.ico");
		defaultServlet.addMapping("*.png");
		defaultServlet.addMapping("*.gif");
		defaultServlet.addMapping("*.woff");
		defaultServlet.addMapping("*.woff2");
		defaultServlet.addMapping("*.ttf");

		ServletRegistration.Dynamic servletRESTWebService = servletContext.addServlet("REST-WebService",
				new DefaultServlet());
		servletRESTWebService.setInitParameter("com.sun.jersey.config.property.packages",
				"com.mevenk.typicalwebapp.webservice.rest");
		servletRESTWebService.addMapping(SLASH + TYPICAL_WEB_APP_REST_WEB_SERVICE_REQUEST_MAPPING + SLASH + ASTERISK);
		servletRESTWebService.setLoadOnStartup(1);
	}

	private AnnotationConfigWebApplicationContext getRootContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationRootContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationRootContext.setConfigLocation("com.mevenk.typicalwebapp.config");
		return annotationConfigWebApplicationRootContext;
	}

	private AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationDispatcherContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationDispatcherContext.setConfigLocation("com.mevenk.typicalwebapp.config");
		return annotationConfigWebApplicationDispatcherContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { SLASH };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { TypicalWebAppRootConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { TypicalWebAppServletConfiguration.class };
	}

}