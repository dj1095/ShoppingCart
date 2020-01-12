package com.mindtree.shoppingcart.exceptions;

import java.util.Date;

import lombok.Getter;

@Getter
public class ExceptionResponse {
	
	/**
	 * constructs an instance of ExceptionResponse
	 * 
	 * @param timestamp , may be null
	 * @param message, may be null
	 * @param details , may be null
	 */
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	/**
	 * timestamp.
	 */
	private Date timestamp;
	/**
	 * message.
	 */
	private String message;
	/**
	 * details.
	 */
	private String details;
}
