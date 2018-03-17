package com.jojoldu.aws.springbootsqs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 17.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties("sqs")
public class SqsQueueNames {
    private Map<String, String> queueNames;

    public String getQueue(String key) {
        return queueNames.get(key);
    }
}
