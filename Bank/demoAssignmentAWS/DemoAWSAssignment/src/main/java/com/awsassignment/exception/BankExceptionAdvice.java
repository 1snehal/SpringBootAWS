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
public class BankExceptionAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<Map<String, Object>> bankNumberFormatException(NumberFormatException numberformatexception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "Invalid format");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Map<String, Object>> bankNullPointerException(NullPointerException nullpointerexception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "Parameters can`t be null");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<Map<String, Object>> bankIOException(IOException exception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "File Not Found");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, Object>> bankException(Exception exception) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "Something went wrong");
		response.put("localtime", LocalTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
