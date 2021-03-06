package com.sbnz.recovery.exceptions;

public class ExistingFieldValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExistingFieldValueException(String type, String fieldName) {
		super(String.format("%s with given %s already exists.", type, fieldName));
	}
}