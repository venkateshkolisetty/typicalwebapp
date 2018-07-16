/**
 *
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.PROPERTY_SOURCE_TYPICAL_WEB_APP_PROPERTIES_FILE_SOURCE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.TYPICAL_WEB_APP_BASE_PACKAGES;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.mevenk.typicalwebapp.logger.TypicalWebAppLogger;
import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.service.impl.ClientUtilServiceimpl;
import com.mevenk.typicalwebapp.util.TypicalWebAppUtil;

/**
 * @author Venkatesh
 *
 */
@Configuration
@PropertySource(PROPERTY_SOURCE_TYPICAL_WEB_APP_PROPERTIES_FILE_SOURCE)
@EnableScheduling
@ComponentScan(basePackages = TYPICAL_WEB_APP_BASE_PACKAGES)
public class TypicalWebAppRootConfiguration implements SchedulingConfigurer {

	public static final String TYPICAL_WEB_APP_PROPERTIES_LOADER = "typicalWebAppPropertiesLoader";

	private static final String TYPICAL_WEB_APP_UTIL = "typicalWebAppUtil";
	private static final String TYPICAL_WEB_APP_LOGGER = "typicalWebAppLogger";
	private static final String CLIENT_UTIL_SERVICE = "clientUtilService";

	@Bean(name = TYPICAL_WEB_APP_PROPERTIES_LOADER)
	@Scope(scopeName = SCOPE_SINGLETON)
	public TypicalWebAppPropertiesLoader typicalWebAppPropertiesLoader() {
		return new TypicalWebAppPropertiesLoader(TYPICAL_WEB_APP_PROPERTIES_LOADER);
	}

	@Bean(name = TYPICAL_WEB_APP_UTIL)
	@DependsOn(TYPICAL_WEB_APP_PROPERTIES_LOADER)
	@Scope(scopeName = SCOPE_SINGLETON)
	public TypicalWebAppUtil typicalWebAppUtil() {
		return new TypicalWebAppUtil(TYPICAL_WEB_APP_UTIL);
	}

	@Bean(name = TYPICAL_WEB_APP_LOGGER)
	@DependsOn(TYPICAL_WEB_APP_PROPERTIES_LOADER)
	@Scope(scopeName = SCOPE_SINGLETON)
	public TypicalWebAppLogger typicalWebAppLogger() {
		return new TypicalWebAppLogger(TYPICAL_WEB_APP_LOGGER);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(10);
	}

	@Bean(name = "typicalWebAppBeanPostProcessor")
	public TypicalWebAppBeanPostProcessor typicalWebAppBeanPostProcessor() {
		return new TypicalWebAppBeanPostProcessor();
	}

	@Bean(name = CLIENT_UTIL_SERVICE)
	@Scope(scopeName = SCOPE_SINGLETON)
	@Lazy()
	public ClientUtilService clientUtilService() {
		return new ClientUtilServiceimpl(CLIENT_UTIL_SERVICE);
	}

}
