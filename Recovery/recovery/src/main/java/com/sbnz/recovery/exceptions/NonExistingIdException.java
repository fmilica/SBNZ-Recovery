package com.sbnz.recovery.exceptions;

public class NonExistingIdException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NonExistingIdException(String type) {
		super(String.format("%s with given id doesn't exist.", type));
	}
}
