package com.mindtree.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class InvalidUserException extends Exception {

	public InvalidUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
