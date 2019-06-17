package com.xuyh.controller;

import com.xuyh.rabbit.senders.HelloSender1;
import com.xuyh.rabbit.senders.HelloSender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cleve
 * @Description: RabbitMQ
 * @Date: 2019/6/17
 * @Version:
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }

    /**
     * 单生产者-多消费者
     */
    @PostMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("helloMsg" + i);
        }
    }

    /**
     * 多生产者-多消费者
     */
    @PostMapping("/manyToMany")
    public void manyToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("helloMsg" + i);
            helloSender2.send("helloMsg" + i);
        }
    }
}
