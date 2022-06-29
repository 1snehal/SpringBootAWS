package com.awsassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awsassignment.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

}
