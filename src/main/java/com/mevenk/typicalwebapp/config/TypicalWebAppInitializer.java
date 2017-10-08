package com.mevenk.typicalwebapp.config;

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

	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext webApplicationRootContext = getRootContext();
		WebApplicationContext webApplicationDispatcherContext = getDispatcherContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationRootContext));

		ServletRegistration.Dynamic dispatcherDynamicServlet = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(webApplicationDispatcherContext));

		dispatcherDynamicServlet.addMapping("/");
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

	}

	private AnnotationConfigWebApplicationContext getRootContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationRootContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationRootContext.setConfigLocation("com.mevenk.typicalwebapp.config");
		//annotationConfigWebApplicationRootContext.register(TypicalWebAppRootConfiguration.class);
		return annotationConfigWebApplicationRootContext;
	}

	private AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationDispatcherContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationDispatcherContext.setConfigLocation("com.mevenk.typicalwebapp.config");
		//annotationConfigWebApplicationDispatcherContext.register(TypicalWebAppServletConfiguration.class);
		return annotationConfigWebApplicationDispatcherContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
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