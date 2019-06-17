package com.xuyh.rabbit.senders;

import com.xuyh.model.UserModel;
import com.xuyh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: cleve
 * @Description: 用户生产者
 * @Date: 2019/6/17
 * @Version:
 */
@Slf4j
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private UserService  userService;

    @Value("${spring.rabbitmq.queues.key.user}")
    private String userQueue;

    public void send(){
        List<UserModel> userList = userService.getAll();
        log.info("users :" +userList.toString());
        amqpTemplate.convertAndSend(userQueue, userList);
    }
}
