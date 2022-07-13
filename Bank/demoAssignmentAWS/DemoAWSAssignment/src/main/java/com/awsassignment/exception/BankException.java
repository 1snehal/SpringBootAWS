package com.awsassignment.exception;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<Map<String, Object>> bankexception(IOException ioexception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "file not found");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
