package com.mevenk.typicalwebapp.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.mevenk.typicalwebapp.trigger.ControllerTrigger;
import com.mevenk.typicalwebapp.trigger.FileActionsTrigger;
import com.mevenk.typicalwebapp.trigger.PollingTrigger;
import com.mevenk.typicalwebapp.trigger.impl.ControllerRequestTrigger;
import com.mevenk.typicalwebapp.trigger.impl.FileAcionsLoggingTrigger;
import com.mevenk.typicalwebapp.trigger.impl.TimelyDatePollingTrigger;

@Import(TypicalWebAppRootConfiguration.class)
@EnableAspectJAutoProxy
@Configuration
public class TypicalWebAppAspectConfiguration {

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

	@Bean(name = "timelyDatePollingTrigger")
	@Scope(scopeName = SCOPE_PROTOTYPE)
	public PollingTrigger timelyDatePollingTrigger() {
		return new TimelyDatePollingTrigger();
	}
}
