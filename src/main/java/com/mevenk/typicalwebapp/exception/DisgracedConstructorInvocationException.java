/**
 * 
 */
package com.mevenk.typicalwebapp.exception;

/**
 * @author Venkatesh
 *
 */
public class DisgracedConstructorInvocationException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1132848194232756490L;

	/**
	 * 
	 */
	public DisgracedConstructorInvocationException(Class<?> invokedClass) {
		super(invokedClass.getName());
	}

	/**
	 * @param cause
	 */
	public DisgracedConstructorInvocationException(Throwable cause) {
		super(cause);
	}

}
