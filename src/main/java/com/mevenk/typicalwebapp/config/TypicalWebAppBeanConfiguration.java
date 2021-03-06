/**
 *
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppRootConfiguration.TYPICAL_WEB_APP_PROPERTIES_LOADER;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.service.impl.TypicalWebAppBeanServiceImpl;
import com.mevenk.typicalwebapp.service.impl.TypicalWebAppServiceImpl;

/**
 * @author Venkatesh
 *
 */
@Import(TypicalWebAppRootConfiguration.class)
@Configuration
public class TypicalWebAppBeanConfiguration {

	private static final String TYPICAL_WEB_APP_BEAN_SERVICE = "typicalWebAppBeanService";
	private static final String TYPICAL_WEB_APP_SERVICE = "typicalWebAppService";

	@Bean(name = TYPICAL_WEB_APP_BEAN_SERVICE)
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public TypicalWebAppBeanService typicalWebAppBeanService() {
		return new TypicalWebAppBeanServiceImpl(TYPICAL_WEB_APP_BEAN_SERVICE);
	}

	@Bean(name = TYPICAL_WEB_APP_SERVICE)
	@DependsOn(TYPICAL_WEB_APP_PROPERTIES_LOADER)
	public TypicalWebAppService typicalWebAppService() {
		return new TypicalWebAppServiceImpl(TYPICAL_WEB_APP_SERVICE);
	}

}
