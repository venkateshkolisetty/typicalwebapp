package com.mevenk.typicalwebapp.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class MyControllerAdvisor {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception exception) {

		return "404";
	}
}