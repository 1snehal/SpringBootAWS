package com.awsassignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.awsassignment.entity.Bank;
import com.awsassignment.exception.BankHandleException;

@Service
public class AmazonS3Utils {
	@Value(value = "${custom.bucketname}")
	String bucketname;
	@Value(value = "${custom.accesskey}")
	String accesskey;
	@Value(value = "${custom.secretekey}")
	String secretekey;
	@Value(value = "${custom.region}")
	String region;

	public AmazonS3 amazons3client() {
		BasicAWSCredentials creds = new BasicAWSCredentials(accesskey, secretekey);
		AmazonS3 amazons3 = AmazonS3Client.builder().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		return amazons3;
	}

	public List<S3Object> gets3object() {
		AmazonS3 amazons3 = amazons3client();
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

	public List<Bank> datacollection() throws BankHandleException, IOException {
		List<S3Object> s3objectcollection = gets3object();
		List<Bank> bankcollect = new ArrayList<>();
		for (S3Object s3object : s3objectcollection) {
			BufferedReader ib = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
			Bank bank = null;
			String line = null;
			if (ib.readLine() == null) {
				throw new BankHandleException("data not found");
			} else {
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
		}
		return bankcollect;
	}
}
