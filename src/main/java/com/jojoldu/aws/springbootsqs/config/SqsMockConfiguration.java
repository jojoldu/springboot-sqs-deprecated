package com.jojoldu.aws.springbootsqs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.*;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 15.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Configuration
@ConditionalOnExpression("${sqs.mock.enabled}")
public class SqsMockConfiguration {

    @Autowired
    private SqsProperties sqsProperties;

    @Bean
    @Primary //cloud-starter로 자동생성되는 amaznoSQS 보다 우선순위를 높이기 위해
    @Profile("local")
    @DependsOn("sqsRestServer") //sqsRestServer가 생성된 후, 생성
    public AmazonSQSAsync amazonSQS() {
        AmazonSQSAsync sqsAsync = createMockSqsAsync();
        sqsAsync.createQueue("springboot-cloud-sqs");
        return sqsAsync;
    }

    private AmazonSQSAsync createMockSqsAsync() {
        AmazonSQSAsyncClientBuilder sqsBuilder = AmazonSQSAsyncClientBuilder.standard();
        sqsBuilder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("x", "x")));
        sqsBuilder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9324", ""));
        return sqsBuilder.build();
    }

    @Bean
    @Profile("local")
    public SQSRestServer sqsRestServer() {
        return SQSRestServerBuilder.start();
    }
}
