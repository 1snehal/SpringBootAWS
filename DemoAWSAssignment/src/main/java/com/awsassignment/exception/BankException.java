package com.awsassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankException extends ResponseEntityExceptionHandler {
	@ResponseStatus
	@ExceptionHandler(value = BankHandleException.class)
	public ResponseEntity<String> bankexception(BankHandleException bankhandleexception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bankhandleexception.getMessage());
	}
}
