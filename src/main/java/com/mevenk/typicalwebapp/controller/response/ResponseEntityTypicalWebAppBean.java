/**
 * 
 */
package com.mevenk.typicalwebapp.controller.response;

import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.addHeadersTypicalWebAppGeneral;
import static com.mevenk.typicalwebapp.controller.config.TypicalWebAppControllerResponseConfig.headersTypicalWebAppGeneral;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;

/**
 * @author Venkatesh
 *
 */
public class ResponseEntityTypicalWebAppBean extends ResponseEntity<TypicalWebAppBean> {

	public ResponseEntityTypicalWebAppBean(TypicalWebAppBean body, MultiValueMap<String, String> headers,
			HttpStatus status) {
		super(body, addHeadersTypicalWebAppGeneral(headers), status);
	}

	public ResponseEntityTypicalWebAppBean(TypicalWebAppBean body, HttpStatus status) {
		super(body, headersTypicalWebAppGeneral(), status);
	}

}
