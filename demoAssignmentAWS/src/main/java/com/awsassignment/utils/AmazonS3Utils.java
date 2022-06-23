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
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.awsassignment.pojo.Bank;

@Service
public class AmazonS3Utils {
	public static List<Bank> amazons3client() {
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAXI3EZFYHU4VVLHF3",
				"M34ivx4/AjVW63SIqNpWM8BG5GUKB2IpSIyOquaR");
		AmazonS3 amazons3 = AmazonS3Client.builder().withRegion("us-east-1")
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		ListObjectsV2Result result = amazons3.listObjectsV2("bankapplicationbuckets");
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		List<Bank> bankcollect = new ArrayList<>();
		for (S3ObjectSummary os : objects) {
			S3Object s = amazons3.getObject("bankapplicationbuckets", os.getKey());
			BufferedReader ib = new BufferedReader(new InputStreamReader(s.getObjectContent()));
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
		}
		return bankcollect;

	}
}
