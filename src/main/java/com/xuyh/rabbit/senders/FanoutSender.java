package com.xuyh.rabbit.senders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: cleve
 * @Description: fanout exchange 学习
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.queues.type.fanout.exchange}")
    private String fanoutExchange;

    public void send(){
        String msg1 = "fanout Sender : hello i am cleve";
        log.info(msg1);
        amqpTemplate.convertAndSend(fanoutExchange, "fanoutTest.ee", msg1);
    }
}
