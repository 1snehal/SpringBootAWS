package com.awsassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String savedata() {
		bankservice.handleRequest();
		return "done";
	}
}
