package com.xuyh.controller;

import com.xuyh.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/17
 * @Version:
 */
@RestController
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/jdbc")
    @Transactional
    public List jdbcController(){
        return jdbcService.getUsers4Jdbc(jdbcTemplate);
    }
}
