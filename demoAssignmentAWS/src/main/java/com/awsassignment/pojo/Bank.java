package com.awsassignment.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bankinfo")
public class Bank {
	@Id
	int customerID;
	String branchID;
	int accountID;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public Bank() {
	}

	public Bank(int customerID, String branchID, int accountID) {
		this.customerID = customerID;
		this.branchID = branchID;
		this.accountID = accountID;
	}

	@Override
	public String toString() {
		return "Bank [customerID=" + customerID + ", branchID=" + branchID + ", accountID=" + accountID + "]";
	}
}
