package com.awsassignment.dao;

import java.util.List;

import com.awsassignment.entity.Bank;

public interface BankDao {
	List<Bank> saveBankdatails(List<Bank> bank);
}
