package com.jojoldu.aws.springbootsqs;

import com.jojoldu.aws.springbootsqs.dto.SqsMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 13.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RestController
@AllArgsConstructor
public class SqsController {

    private QueueMessagingTemplate messagingTemplate;

    private static final String QUEUE_NAME = "springboot-cloud-sqs";

    @GetMapping("/send")
    public String send(){
        send(new SqsMessage("test", LocalDate.now()));
        return "success";
    }

    private void send(SqsMessage message) {
        messagingTemplate.convertAndSend(QUEUE_NAME, message);
    }

    @SqsListener(value = "${sqs.queue}")
    public void receive(String message, @Header("SenderId") String senderId) {
        log.info("senderId: {}, message: {}", senderId, message);
    }
}
