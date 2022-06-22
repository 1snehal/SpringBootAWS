package com.awsassignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.awsassignment.pojo.Bank;

@Service
public class AmazonS3Utils {
	public static List<Bank> amazons3client() {
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAXI3EZFYH7WF5K25S",
				"cDzfiVjrLy6Rx+dQCU0Au/FqIk2qYD9L9VHUK5Zk");
		AmazonS3 amazons3 = AmazonS3Client.builder().withRegion("us-east-1")
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		S3Object s = amazons3.getObject("bankapplicationbuckets", "Demo.csv");
		BufferedReader ib = new BufferedReader(new InputStreamReader(s.getObjectContent()));
		List<Bank> bankcollect = new ArrayList<>();
		Bank bank = null;
		String line = null;
		try {
			ib.readLine();
			while ((line = ib.readLine()) != null) {
				String[] data = line.split(",");
				for (int i = 0; i < data.length; i++) {
					int custid = Integer.parseInt(data[0]);
					int account = Integer.parseInt(data[1]);
					String branch = data[2];
					bank = new Bank(custid, branch, account);
				}
				bankcollect.add(bank);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bankcollect;
	}
}
