package com.jojoldu.aws.springbootsqs.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 17.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Order(Ordered.HIGHEST_PRECEDENCE + 40)
class OnMockSqsCondition extends SpringBootCondition {

    private static final String ACCESS_KEY_NAME = "cloud.aws.credentials.accessKey";
    private static final String SECRET_KEY_NAME = "cloud.aws.credentials.secretKey";

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String accessKey = context.getEnvironment().getProperty(ACCESS_KEY_NAME);
        String secretKey = context.getEnvironment().getProperty(SECRET_KEY_NAME);
        boolean match = StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(secretKey);
        return new ConditionOutcome(match, createMessage(match));
    }

    private String createMessage(boolean match){
        return match? "Execute Mock Sqs" : "Execute AWS SQS";
    }
}
