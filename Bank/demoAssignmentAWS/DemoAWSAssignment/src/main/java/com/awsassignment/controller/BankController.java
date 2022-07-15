package com.awsassignment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awsassignment.service.BankService;

@RestController
@RequestMapping("/bankdata")
public class BankController {
	private BankService bankservice;

	@Autowired
	public BankController(BankService bankservice) {
		this.bankservice = bankservice;
	}

	@RequestMapping("/processData")
	public ResponseEntity<String> processData() throws NumberFormatException, IOException {
		boolean result = bankservice.handleRequest();
		if (result == true)
			bankservice.deleteFilesFromBucket();
		return ResponseEntity.status(HttpStatus.OK).body("Sucessful");
	}
}
