package com.awsassignment.exception;

public class BankHandleException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;

	public BankHandleException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BankHandleException [message=" + message + "]";
	}
}
