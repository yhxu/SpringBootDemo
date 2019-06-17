package com.xuyh.rabbit.senders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: cleve
 * @Description: topic exchange 学习
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.queues.type.topic.exchange}")
    private String topicExchange;
    @Value("${spring.rabbitmq.queues.key.topic.message}")
    private String topicMessage;
    @Value("${spring.rabbitmq.queues.key.topic.messages}")
    private String topicMessages;

    public void send(){
        String msg1 = "I am topic.message msg======";
        log.info("sender1 : " + msg1);
        amqpTemplate.convertAndSend(topicExchange, topicMessage, msg1);

        String msg2 = "I am topic.messages msg######";
        log.info("sender2 : " + msg2);
        amqpTemplate.convertAndSend(topicExchange, topicMessages, msg2);
    }
}
