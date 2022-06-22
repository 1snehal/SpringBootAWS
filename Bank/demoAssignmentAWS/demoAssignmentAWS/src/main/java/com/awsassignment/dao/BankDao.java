package com.awsassignment.dao;

import java.util.List;

import com.awsassignment.pojo.Bank;

public interface BankDao {
	List<Bank> saveBankdatails(List<Bank> bank);
}
