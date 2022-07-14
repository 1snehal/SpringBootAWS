package com.awsassignment.exception;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class BankExceptionAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = BankException.class)
	public ResponseEntity<Map<String, Object>> bankexception(BankException bankexception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "error found");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, Object>> bankexception(Exception exception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "Something went wrong");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
