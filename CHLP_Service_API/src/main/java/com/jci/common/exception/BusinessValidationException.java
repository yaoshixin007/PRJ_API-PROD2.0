package com.jci.common.exception;

public class BusinessValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public BusinessValidationException(String message) {
		
		super(message);
	}

}
