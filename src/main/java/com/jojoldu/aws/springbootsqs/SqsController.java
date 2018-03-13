package com.jojoldu.aws.springbootsqs;

import lombok.AllArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 13.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RestController
@AllArgsConstructor
public class SqsController {

    private QueueMessagingTemplate messagingTemplate;

    public void send(String topicName, Object message) {
        messagingTemplate.convertAndSend(topicName, message);
    }
}
