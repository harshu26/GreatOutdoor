package com.capgemini.go.exceptions;

public class AddressAlreadyExistsException extends RuntimeException {
	public AddressAlreadyExistsException(String message) {
		super(message);
	}
}
