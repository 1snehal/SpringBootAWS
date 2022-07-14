package com.awsassignment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
@PropertySource(value = { "application.properties" })
public class BankConfiguration {
	@Value(value = "${custom.accesskey}")
	String accesskey;
	@Value(value = "${custom.secretekey}")
	String secretekey;
	@Value(value = "${custom.region}")
	String region;

	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials creds = new BasicAWSCredentials(accesskey, secretekey);
		AmazonS3 amazons3 = AmazonS3Client.builder().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		return amazons3;
	}
}
