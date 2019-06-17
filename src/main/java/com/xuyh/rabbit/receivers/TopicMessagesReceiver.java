package com.xuyh.rabbit.receivers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: cleve
 * @Description: topic exchange 学习
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
@RabbitListener(queues = "${spring.rabbitmq.queues.key.topic.messages}")
public class TopicMessagesReceiver {
    @RabbitHandler
    public void process(String msg){
        log.info("topic Messages Receiver :" + msg);
    }
}
