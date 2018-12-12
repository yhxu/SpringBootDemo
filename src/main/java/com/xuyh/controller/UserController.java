package com.xuyh.controller;

import com.xuyh.model.UserModel;
import com.xuyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: XUYH
 * @Description: 用户
 * @Date: 2018/12/12
 * @Version:
 */
@RestController
public class UserController {
    @Autowired
    private UserService mUserService;
    @PostMapping(value = "getAllUsers")
    public String getAll(){
        String userInfo = null;
        List<UserModel> users = mUserService.getAll();
        for(UserModel user:users){
            userInfo += user.toString();
        }
        return userInfo;
    }
}
