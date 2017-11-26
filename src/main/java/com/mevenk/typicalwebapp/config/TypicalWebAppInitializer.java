package com.mevenk.typicalwebapp.config;

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

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext webApplicationDispatcherContext = getDispatcherContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationDispatcherContext));

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
		
		/*ServletRegistration.Dynamic servletRESTWebService = servletContext.addServlet(TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_NAME,
				new DefaultServlet());
		servletRESTWebService.setInitParameter(TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_NAME,
				TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_CONFIG_PROPERTY_PACKAGES_PARAM_VALUE);
		servletRESTWebService.addMapping(TypicalWebAppRESTWSServlet.TYPICALWEBAPP_REST_WEB_SERVICE_SERVLET_URL_PATTERN);
		servletRESTWebService.setLoadOnStartup(1);*/
		
		
		
	}

	private AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationDispatcherContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationDispatcherContext.register(TypicalWebAppServletConfiguration.class);
		annotationConfigWebApplicationDispatcherContext.register(TypicalWebAppRootConfiguration.class);
		annotationConfigWebApplicationDispatcherContext.register(TypicalWebAppBeanConfiguration.class);
		return annotationConfigWebApplicationDispatcherContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { SLASH };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { TypicalWebAppRootConfiguration.class, TypicalWebAppBeanConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { TypicalWebAppServletConfiguration.class };
	}

}