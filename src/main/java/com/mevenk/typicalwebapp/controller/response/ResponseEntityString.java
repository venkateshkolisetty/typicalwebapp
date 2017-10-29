/**
 * 
 */
package com.mevenk.typicalwebapp.controller.response;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.addHeadersTypicalWebAppGeneral;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.headersTypicalWebAppGeneral;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @author Venkatesh
 *
 */
public class ResponseEntityString extends ResponseEntity<String> {

	public ResponseEntityString(String body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, addHeadersTypicalWebAppGeneral(headers), status);
	}

	public ResponseEntityString(String body, HttpStatus status) {
		super(body, headersTypicalWebAppGeneral(), status);
	}

	public static ResponseEntityString responseEntityStringError() {
		return new ResponseEntityString(ERROR, headersTypicalWebAppGeneral(), INTERNAL_SERVER_ERROR);
	}
}
