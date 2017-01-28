/**
 * 
 */
package com.mevenk.typicalwebapp.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author VENKATESH
 *
 */
public interface ClientUtilService {
	
	void logRequestDetails(HttpServletRequest httpServletRequest);
	void logClientDetails(HttpServletRequest httpServletRequest);
	
}
