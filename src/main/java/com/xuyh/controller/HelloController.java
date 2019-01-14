package com.xuyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Author: XUYH
 * @Description: 控制类
 * @Date: 2018/12/12
 * @Version:
 */
@RestController
public class HelloController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/hello")
    public String helloController(){
        return "Hello Spring Boot Controller!";
    }

    @GetMapping(value = "/i18n")
    public String i18nController(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("international.message", null, "默认值", locale);
    }
}
