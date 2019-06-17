package com.xuyh.rabbit.senders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: cleve
 * @Description: 生产者2 用于学习 多生产者-多消费者
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.queues.key.hello}")
    private String hellQueue;

    public void send(String sendMsg) {
        sendMsg = sendMsg + " " + new Date();
        log.info("Sender2 : " + sendMsg);
        amqpTemplate.convertAndSend(hellQueue, sendMsg);
    }
}
