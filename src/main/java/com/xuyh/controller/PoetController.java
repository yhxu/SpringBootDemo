package com.xuyh.controller;

import com.xuyh.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XUYH
 * @Description: poet测试
 * @Date: 2018/12/21
 * @Version:
 */
@RestController
public class PoetController {

    @Autowired
    private PoetService mPoetService;

    @GetMapping(value = "poet")
    public String poetController(){
        return mPoetService.genJavaFile();
    }
}
