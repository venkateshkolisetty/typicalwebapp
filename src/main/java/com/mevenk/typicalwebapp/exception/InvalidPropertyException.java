/**
 * 
 */
package com.mevenk.typicalwebapp.exception;

/**
 * @author Venkatesh
 *
 */
public class InvalidPropertyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6241794701278163434L;
	private static final String INVALID_PROPERTY_EXCEPTION_MESSAGE_PREFIX = "INLALID property: ";

	/**
	 * 
	 */
	public InvalidPropertyException(String property) {
		super(INVALID_PROPERTY_EXCEPTION_MESSAGE_PREFIX + property);
	}

}
