package com.mevenk.typicalwebapp.controller.config;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerRequestConfig.TYPICAL_WEB_APP;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TypicalWebAppBeanInvocationService;
import com.mevenk.typicalwebapp.exception.DisgracedConstructorInvocationException;

public final class TypicalWebAppControllerResponseConfig {

	private TypicalWebAppControllerResponseConfig() {
		throw new DisgracedConstructorInvocationException(this.getClass());
	}

	public static final String TYPICAL_WEB_APP_RESPONSE_VIEW_WELCOME = "welcome";
	public static final String TYPICAL_WEB_APP_RESPONSE_VIEW_404 = "404";

	private static final String APPLICATION = "Application";

	public static synchronized MultiValueMap<String, String> headersTypicalWebAppGeneral() {
		MultiValueMap<String, String> headersTypicalWebAppGeneral = new LinkedMultiValueMap<>();
		headersTypicalWebAppGeneral.add(APPLICATION, TYPICAL_WEB_APP);
		return headersTypicalWebAppGeneral;
	}

	public static synchronized MultiValueMap<String, String> addHeadersTypicalWebAppGeneral(
			MultiValueMap<String, String> headers) {
		headers.addAll(headersTypicalWebAppGeneral());
		return headers;
	}

	public static synchronized MultiValueMap<String, String> headerTypicalWebAppBean(
			TypicalWebAppBeanInvocationService typicalWebAppBeanInvocationService) {
		MultiValueMap<String, String> headersTypicalWebAppBen = new LinkedMultiValueMap<>();
		headersTypicalWebAppBen.addAll(headersTypicalWebAppGeneral());
		headersTypicalWebAppBen.add(typicalWebAppBeanInvocationService.getHeaderParam(),
				typicalWebAppBeanInvocationService.value());
		return headersTypicalWebAppBen;
	}

}
