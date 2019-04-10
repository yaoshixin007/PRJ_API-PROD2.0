package com.jci.common.exception;

public class StringTooLongException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	
	
	public StringTooLongException(String parameterName) {
		super(parameterName);
		
	}
	
}
