package com.awsassignment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long customerID;
	String branchID, TransactionType;
	int accountID, TransactionAmount;

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getTransactionAmount() {
		return TransactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		TransactionAmount = transactionAmount;
	}

	public Bank(String branchID, String transactionType, int accountID, int transactionAmount) {
		this.branchID = branchID;
		TransactionType = transactionType;
		this.accountID = accountID;
		TransactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "Bank [customerID=" + customerID + ", branchID=" + branchID + ", TransactionType=" + TransactionType
				+ ", accountID=" + accountID + ", TransactionAmount=" + TransactionAmount + "]";
	}

}
