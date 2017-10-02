/**
 * 
 */
package com.mevenk.typicalwebapp.exception;

/**
 * @author Venkatesh
 *
 */
public class UsernameException extends UserAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972402476330199088L;

	public static enum UsernameError {
		USERNAME_NOT_AVAILABLE("Username Not Available"), USER_ACCOUNT_LOCKED("User Account Locked");

		private String detailedMessage;

		private UsernameError(String detailedMessage) {
			this.detailedMessage = detailedMessage;
		}

		public String toString() {
			return detailedMessage;
		}
	}

	/**
	 * 
	 */
	public UsernameException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public UsernameException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public UsernameException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public UsernameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public UsernameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
