package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfiguration {
	
	
	private static final String ACCESS_KEY = "AKIAZ4C6HBGWMEGH6267";
	private static final String SECRET_KEY = "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";
	
	@Bean
	public BasicAWSCredentials awsCredentials() {
		return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
	}
	
	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder
				.standard().withRegion("sa-east-1")
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
				.build();
	}
}
