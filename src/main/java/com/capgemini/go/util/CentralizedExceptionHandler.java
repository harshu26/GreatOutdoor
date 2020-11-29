package com.capgemini.go.util;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.go.exceptions.AddressAlreadyExistsException;
import com.capgemini.go.exceptions.AddressNotFoundException;
import com.capgemini.go.exceptions.IncorrectArgumentException;

@ControllerAdvice
public class CentralizedExceptionHandler {

	private static final Logger Log = LoggerFactory.getLogger(CentralizedExceptionHandler.class);

	@ExceptionHandler(AddressAlreadyExistsException.class)
	public ResponseEntity<String> handleAddressAlreadyExists(AddressAlreadyExistsException ex) {
		Log.error("address already present", ex);
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IncorrectArgumentException.class)
	public ResponseEntity<String> handleIncorrectArgument(IncorrectArgumentException ex) {
		Log.error("Incorrect Argument", ex);
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<String> handleAddressNotFound(AddressNotFoundException ex) {
		Log.error("address not found exception", ex);
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
		Log.error("constraint violation", ex);
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) {
		Log.error("exception caught", ex);
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
