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

	@RequestMapping("/savedata")
	public ResponseEntity<String> savedata() throws IOException {
		bankservice.handleRequest();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteobjects")
	public ResponseEntity<String> deleteFile() {
		bankservice.deleteobjects();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
