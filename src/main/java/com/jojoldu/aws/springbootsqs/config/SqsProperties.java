package com.jojoldu.aws.springbootsqs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 15.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties("sqs")
@ConditionalOnExpression("${sqs.mock.enabled}")
public class SqsProperties {

    private Map<String, String> queueNames;
    private Integer port;

    public String getEndPoint() {
        return String.format("http://localhost:%s", port);
    }

    public Integer getPort() {
        return StringUtils.isEmpty(port)? 9324: port;
    }

    public String getQueue(String key) {
        return queueNames.get(key);
    }
}
