package com.mevenk.typicalwebapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class TypicalWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext webApplicationContext = getContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationContext));

		ServletRegistration.Dynamic dispatcherDynamicServlet = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(webApplicationContext));

		dispatcherDynamicServlet.setLoadOnStartup(1);
		dispatcherDynamicServlet.addMapping("/");
		dispatcherDynamicServlet.setAsyncSupported(true);
		dispatcherDynamicServlet.setLoadOnStartup(1);

		/*ServletRegistration.Dynamic defaultServlet = servletContext.addServlet("default",
				new DefaultServlet());
		
		defaultServlet.addMapping("*.js");
		defaultServlet.addMapping("*.css");
		defaultServlet.addMapping("*.ico");
		defaultServlet.addMapping("*.png");
		defaultServlet.addMapping("*.gif");
		defaultServlet.addMapping("*.woff");
		defaultServlet.addMapping("*.woff2");
		defaultServlet.addMapping("*.ttf");*/
		
	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.setConfigLocation("com.mevenk.typicalwebapp.config");
		return annotationConfigWebApplicationContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { TypicalWebAppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

}