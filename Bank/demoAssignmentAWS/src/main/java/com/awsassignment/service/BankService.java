package com.awsassignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awsassignment.dao.BankDao;
import com.awsassignment.pojo.Bank;
import com.awsassignment.utils.AmazonS3Utils;

@Service
public class BankService {
	private BankDao bankdao;

	public BankService() {

	}

	@Autowired
	public BankService(BankDao bankdao) {
		this.bankdao = bankdao;
	}

	public String handleRequest() {
		List<Bank> banklist = AmazonS3Utils.amazons3client();
		for (int i = 0; i < banklist.size(); i++) {
			Bank banks = banklist.get(i);
			bankdao.saveBankdatails(banks);
		}
		return "Done";
	}
}
