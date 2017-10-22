/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;
import com.mevenk.typicalwebapp.service.impl.TypicalWebAppBeanServiceImpl;

/**
 * @author Venkatesh
 *
 */
@PropertySource("${typicalWebappPropertiesFileSource}")
@ComponentScan(basePackages = "com.mevenk.typicalwebapp")
@EnableWebMvc
@EnableAspectJAutoProxy
@Configuration
public class TypicalWebAppBeanConfiguration {

	@Bean(name = "typicalWebAppBeanService")
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public TypicalWebAppBeanService typicalWebAppBeanService() {
		return new TypicalWebAppBeanServiceImpl();
	}

}
