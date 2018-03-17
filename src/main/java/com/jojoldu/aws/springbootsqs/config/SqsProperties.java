package com.jojoldu.aws.springbootsqs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 15.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties("mock.sqs")
public class SqsProperties {

    private String host;
    private Integer port;

    public String getEndPoint() {
        return String.format("http://%s:%s", getHost(), getPort());
    }

    public String getHost() {
        return StringUtils.isEmpty(host)? "localhost" : host;
    }

    public Integer getPort() {
        return port == null? 9324: port;
    }
}
