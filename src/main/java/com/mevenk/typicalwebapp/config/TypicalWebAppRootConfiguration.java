/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.service.impl.ClientUtilServiceimpl;
import com.mevenk.typicalwebapp.service.impl.TypicalWebAppServiceImpl;
import com.mevenk.typicalwebapp.trigger.FileActionsTrigger;

/**
 * @author Venkatesh
 *
 */
@Configuration
@PropertySource("${typicalWebappPropertiesFileSource}")
@EnableScheduling
@EnableWebMvc
@ComponentScan(basePackages = "com.mevenk.typicalwebapp")
@EnableAspectJAutoProxy
public class TypicalWebAppRootConfiguration implements SchedulingConfigurer {

	private static final String SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES_PATTERN = TypicalWebAppPropertiesLoader.SOURCE_BEAN_DATE_FORMAT_PATTERN;
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES = new SimpleDateFormat(
			SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES_PATTERN);

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

	@Bean(name = "clientUtilService")
	@Scope(scopeName = SCOPE_SINGLETON)
	@Lazy()
	public ClientUtilService clientUtilService() {
		return new ClientUtilServiceimpl();
	}

	@Bean(name = "typicalWebAppService")
	public TypicalWebAppService typicalWebAppService() {
		return new TypicalWebAppServiceImpl();
	}

	@Bean(name = "fileActionsTrigger")
	public FileActionsTrigger fileActionsTrigger() {
		return new FileActionsTrigger();
	}
}
