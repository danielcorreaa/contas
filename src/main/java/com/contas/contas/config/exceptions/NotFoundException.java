package com.contas.contas.config.exceptions;

public class NotFoundException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);		
	}

	public NotFoundException(String message) {
		super(message);
	}
	

}
