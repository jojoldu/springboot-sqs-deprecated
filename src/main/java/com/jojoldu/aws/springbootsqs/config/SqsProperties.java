package com.jojoldu.aws.springbootsqs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 15.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties("sqs")
public class SqsProperties {

    private List<String> queues;

    @NonNull
    private String endPointHost;
    @NonNull
    private String endPointPort;

}
