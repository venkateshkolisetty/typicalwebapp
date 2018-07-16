package com.mevenk.typicalwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import com.mevenk.typicalwebapp.interceptor.TypicalWebAppInterceptor;
import com.mevenk.typicalwebapp.interceptor.TypicalWebAppWebRequestInterceptor;

@Configuration
@Import(TypicalWebAppRootConfiguration.class)
public class TypicalWebAppServletConfiguration extends WebMvcConfigurationSupport {

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Bean(name = "viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

//	@Bean(name = "xmlViewResolver")
//	public ViewResolver xmlViewResolver() {
//		XmlViewResolver xmlViewResolver = new XmlViewResolver();
//
//		return xmlViewResolver;
//	}

	@Bean(name = "typicalWebAppInterceptor")
	public HandlerInterceptor handlerInterceptor() {
		return new TypicalWebAppInterceptor();
	}

	@Bean(name = "webRequestInterceptor")
	public WebRequestInterceptor webRequestInterceptor() {
		return new TypicalWebAppWebRequestInterceptor();
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor());
		registry.addWebRequestInterceptor(webRequestInterceptor());
		super.addInterceptors(registry);
	}

}