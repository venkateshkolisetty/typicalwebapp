/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

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
import com.mevenk.typicalwebapp.trigger.ControllerTrigger;
import com.mevenk.typicalwebapp.trigger.FileActionsTrigger;
import com.mevenk.typicalwebapp.trigger.PoolingTrigger;
import com.mevenk.typicalwebapp.trigger.impl.ControllerRequestTrigger;
import com.mevenk.typicalwebapp.trigger.impl.FileAcionsLoggingTrigger;
import com.mevenk.typicalwebapp.trigger.impl.TimelyDatePoolingTrigger;

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

	// ***********************************TRIGGERS****************************************

	@Bean(name = "fileActionsLoggingTrigger")
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public FileActionsTrigger fileActionsLoggingTrigger() {
		return new FileAcionsLoggingTrigger();
	}

	@Bean(name = "controllerRequestTrigger")
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public ControllerTrigger controllerRequestTrigger() {
		return new ControllerRequestTrigger();
	}

	@Bean(name = "timelyDatePoolingTrigger")
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public PoolingTrigger timelyDatePoolingTrigger() {
		return new TimelyDatePoolingTrigger();
	}
	// ***********************************TRIGGERS-END************************************
}
