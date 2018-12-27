package com.xuyh.controller;

import com.xuyh.model.ConnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/27
 * @Version:
 */
@RestController
public class ConnController {

    @Autowired
    private ConnModel connModel;
    @Value("${database.type}")
    private String databaseType;

    @GetMapping(value = "database")
    public String getDatabaseInfo(){
        return databaseType + connModel.toString();
    }
}
