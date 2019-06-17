package com.xuyh.rabbit.receivers;

import com.xuyh.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: cleve
 * @Description: 用户消费者
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
public class UserReceiver {

    @RabbitListener(queues = "${spring.rabbitmq.queues.key.user}", containerFactory = "rabbitListenerContainerFactory")
    public void process(List<UserModel> users) {
        log.info("users : " + users.toString());
    }
}
