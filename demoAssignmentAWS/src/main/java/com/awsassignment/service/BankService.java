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

	@Autowired
	public BankService(BankDao bankdao) {
		this.bankdao = bankdao;
	}

	public void handleRequest() {
		List<Bank> banklist = AmazonS3Utils.amazons3client();
		bankdao.saveBankdatails(banklist);
	}
}
