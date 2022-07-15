package com.awsassignment.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awsassignment.dao.BankDao;
import com.awsassignment.entity.Bank;
import com.awsassignment.utils.AmazonS3Utils;

@Service
public class BankService {
	private BankDao bankdao;
	private AmazonS3Utils amazons3utils;

	@Autowired
	public BankService(BankDao bankdao, AmazonS3Utils amazons3utils) {
		this.bankdao = bankdao;
		this.amazons3utils = amazons3utils;
	}

	public boolean handleRequest() throws NumberFormatException, IOException {
		List<Bank> banklist = amazons3utils.dataCollection();
		bankdao.saveBankDetails(banklist);
		return true;
	}

	public void deleteFilesFromBucket() {
		amazons3utils.deleteFiles();
	}
}
