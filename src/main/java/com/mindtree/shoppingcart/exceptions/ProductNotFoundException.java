package com.mindtree.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
