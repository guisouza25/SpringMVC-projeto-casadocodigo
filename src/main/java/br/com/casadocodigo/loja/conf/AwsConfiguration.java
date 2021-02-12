package br.com.casadocodigo.loja.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfiguration {
	

	@Bean
	@Profile(value = "prod")
	public BasicAWSCredentials awsCredentials() throws FileNotFoundException {
												//C:/apache-tomcat-9.0.37/wtpwebapps/casadocodigo/resources
		Scanner scanner = new Scanner(new File("/var/lib/tomcat9/webapps/casadocodigo/resources/aws_accesskey_secretkey"), "utf-8");
		scanner.useDelimiter(",");
		String acessKey = scanner.next();
		String privateKey = scanner.next();
		
		scanner.close();
		
		return new BasicAWSCredentials(acessKey, privateKey);
	}
	
	@Bean
	public AmazonS3 amazonS3() throws FileNotFoundException {
		return AmazonS3ClientBuilder
				.standard().withRegion("sa-east-1")
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
				.build();
	}
	
}
