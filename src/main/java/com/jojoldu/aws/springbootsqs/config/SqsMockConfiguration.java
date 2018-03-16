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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 15.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Configuration
@ConditionalOnExpression("${sqs.mock.enabled}") // sqs.mock.enabled=true 일때만 활성화
public class SqsMockConfiguration {

    @Autowired
    private SqsProperties sqsProperties;

    @Bean
    @Primary //cloud-starter의존성으로 인해 자동생성되는 amaznoSQS 보다 Mock의 우선순위를 높이기 위해
    @DependsOn("sqsRestServer") //sqsRestServer가 생성된 후, amazonSQS 생성
    public AmazonSQSAsync amazonSqs() {
        AmazonSQSAsync sqsAsync = createMockSqsAsync();
        sqsProperties.getQueueNames().values()
                .forEach(sqsAsync::createQueue);
        return sqsAsync;
    }

    private AmazonSQSAsync createMockSqsAsync() {
        AmazonSQSAsyncClientBuilder sqsBuilder = AmazonSQSAsyncClientBuilder.standard();
        sqsBuilder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("x", "x")));
        sqsBuilder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsProperties.getEndPoint(), ""));
        return sqsBuilder.build();
    }

    @Bean
    public SQSRestServer sqsRestServer() {
        return SQSRestServerBuilder
                .withInterface(sqsProperties.getHost())
                .withPort(sqsProperties.getPort())
                .start();
    }
}
