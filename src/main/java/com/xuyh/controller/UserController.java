package com.xuyh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xuyh.model.UserModel;
import com.xuyh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: XUYH
 * @Description: 用户
 * @Date: 2018/12/12
 * @Version:
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService mUserService;
    /**
     * @Author: xuyh
     * @Description: 当传入参数名与方法参数名不一致时使用@RequestParam进行映射
     * @Date: 15:08 2018/12/14
     */
    @PostMapping(value = "getAllUsers")
    public String getAll(@RequestParam("QueryType") String queryType, @RequestParam("CurrentPage")String currentPage){
        String userInfo = null;
        List<UserModel> users = null;
        if("PAGE".equals(queryType)){
            users = mUserService.getAll4Page(Integer.parseInt(currentPage));
        } else {
            users = mUserService.getAll();
        }

        for(UserModel user:users){
            userInfo += user.toString();
        }
        return userInfo;
    }

    @PostMapping(value = "getUserById", params = "UserId")
    public String getUserById(String userId){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String headerTest = request.getHeader("TEST");
        log.info("获取 HttpServletRequest 测试：" + headerTest);
        HttpSession session = request.getSession();
        String sessionTest = session.getId();
        log.info("获取 HttpSession 测试：" + sessionTest);
        UserModel user = mUserService.getUserById(userId);

        return user.toString();
    }

    @RequestMapping(value = "getUserNameById", params = {"UserId","QueryType"}, method = RequestMethod.POST)
    public String getUserNameById(String userId, String queryType){
        String userName = null;
        if("SQL".equals(queryType)){
            userName = mUserService.getUserNameById4SQL(userId);
        } else {
            userName = mUserService.getUserNameById4JPQL(userId);
        }
        return userName;
    }

    @PostMapping(value = "updateUserNameById", params = {"UserName","UserId"})
    public int updateUserNameById(String userName, String userId){
        return mUserService.updateUserNameById(userName, userId);
    }

    @PostMapping(value = "deleteUserById", params = "UserId")
    public int deleteUserById(String userId){
        return mUserService.deleteUserById(userId);
    }

    @HystrixCommand(fallbackMethod = "getFallback")
    @PostMapping(value = "getUserNameById4Hystrix", params = "UserId")
    public String getUserNameById4Hystrix(String userId){
        UserModel userModel = mUserService.getUserById(userId);
        if(null == userModel){
            throw new RuntimeException(userId);
        }
        return userModel.getUserName();
    }

    public String getFallback(String userId){
        return String.format("用户[%s]不存在", userId);
    }
}
