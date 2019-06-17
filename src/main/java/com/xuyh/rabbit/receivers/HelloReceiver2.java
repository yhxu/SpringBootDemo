package com.xuyh.rabbit.receivers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: cleve
 * @Description: 消费者2， 学习 单生产者-多消费者
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
@RabbitListener(queues = "${spring.rabbitmq.queues.key.hello}")
public class HelloReceiver2 {
    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver2 : " + hello);
    }
}
