package com.mindtree.shoppingcart.exceptions;

public class EmptyCartException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructs an instance 
	 */
	public EmptyCartException() {
		super();
	}

	/**
	 * construct an instance with the message.
	 * 
	 * @param message , may be null;
	 */
	public EmptyCartException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyCartException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
