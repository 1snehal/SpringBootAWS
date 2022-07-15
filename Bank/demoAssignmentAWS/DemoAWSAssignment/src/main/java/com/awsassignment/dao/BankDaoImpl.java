package com.awsassignment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awsassignment.entity.Bank;
import com.awsassignment.repository.BankRepository;

@Component
public class BankDaoImpl implements BankDao {
	private BankRepository bankrepo;

	@Autowired
	public BankDaoImpl(BankRepository bankrepo) {
		this.bankrepo = bankrepo;
	}

	@Override
	public List<Bank> saveBankDetails(List<Bank> banklist) {
		return bankrepo.saveAll(banklist);
	}
}
