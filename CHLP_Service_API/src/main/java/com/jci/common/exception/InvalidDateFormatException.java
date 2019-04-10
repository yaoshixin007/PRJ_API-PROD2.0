package com.jci.common.exception;

public class InvalidDateFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public InvalidDateFormatException(String errorMessage) {
		super(errorMessage);
	}

}
