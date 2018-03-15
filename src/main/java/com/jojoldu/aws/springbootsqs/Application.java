package com.jojoldu.aws.springbootsqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:access-application.yml,";

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

	@Bean
	@Profile("local")
	@Primary
	public AmazonSQSAsync amazonSQS() {
		AmazonSQSAsyncClientBuilder sqsBuilder = AmazonSQSAsyncClientBuilder.standard();
		sqsBuilder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("x", "x")));
		sqsBuilder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9324", ""));
		AmazonSQSAsync sqsAsync = sqsBuilder.build();

		createMockSqs();
		sqsAsync.createQueue("springboot-cloud-sqs");
		return sqsAsync;
	}

	private SQSRestServer createMockSqs() {
		return SQSRestServerBuilder.start();
	}

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
		return new QueueMessagingTemplate(amazonSqs);
	}

}
