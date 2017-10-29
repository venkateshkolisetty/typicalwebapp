package com.mevenk.typicalwebapp.controller.config;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.TYPICAL_WEB_APP_RESPONSE_VIEW_404;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class TypicalWebAppControllerAdvisor {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception exception) {

		return TYPICAL_WEB_APP_RESPONSE_VIEW_404;
	}
}