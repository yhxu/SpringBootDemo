package com.xuyh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XUYH
 * @Description: 控制类
 * @Date: 2018/12/12
 * @Version:
 */
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String helloController(){
        return "Hello Spring Boot Controller!";
    }
}
