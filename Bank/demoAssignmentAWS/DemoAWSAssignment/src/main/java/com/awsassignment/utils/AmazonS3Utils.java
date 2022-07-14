package com.awsassignment.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.awsassignment.entity.Bank;
import com.awsassignment.exception.BankException;

@Service
public class AmazonS3Utils {
	@Value(value = "${custom.bucketname}")
	String bucketname;
	private AmazonS3 amazons3;

	@Autowired
	public AmazonS3Utils(AmazonS3 amazons3) {
		this.amazons3 = amazons3;
	}

	public List<S3Object> getS3Object() {
		S3Object s3objects = null;
		List<S3Object> s3objectlist = new ArrayList<>();
		ListObjectsV2Result result = amazons3.listObjectsV2(bucketname);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			s3objects = amazons3.getObject(bucketname, os.getKey());
			s3objectlist.add(s3objects);
		}
		return s3objectlist;
	}

	public List<Bank> dataCollection() throws BankException, Exception {
		List<S3Object> s3objectcollection = getS3Object();
		List<Bank> bankcollect = new ArrayList<>();
		for (S3Object s3object : s3objectcollection) {
			BufferedReader ib = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
			Bank bank = null;
			String line = null;
			ib.readLine();
			while ((line = ib.readLine()) != null) {
				String[] data = line.split(",");
				for (int i = 0; i < data.length; i++) {
					int transactionamount = Integer.parseInt(data[0]);
					String transactiontype = data[1];
					int account = Integer.parseInt(data[2]);
					String branch = data[3];
					bank = new Bank(branch, transactiontype, account, transactionamount);
				}
				bankcollect.add(bank);
			}
		}
		return bankcollect;
	}

	public void deleteFiles() {
		List<S3Object> s3objectcollection = getS3Object();
		for (S3Object s3object : s3objectcollection) {
			amazons3.deleteObject(bucketname, s3object.getKey());
		}
	}
}
