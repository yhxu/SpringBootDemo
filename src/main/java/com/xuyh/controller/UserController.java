package com.xuyh.controller;

import com.xuyh.model.UserModel;
import com.xuyh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
public class UserController {
    Logger mLogger = LoggerFactory.getLogger(UserController.class);
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
    public String getUserById(String UserId){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String headerTest = request.getHeader("TEST");
        mLogger.info("获取 HttpServletRequest 测试：" + headerTest);
        HttpSession session = request.getSession();
        String sessionTest = session.getId();
        mLogger.info("获取 HttpSession 测试：" + sessionTest);
        UserModel user = mUserService.getUserById(UserId);

        return user.toString();
    }
    @RequestMapping(value = "getUserNameById", params = {"UserId","QueryType"}, method = RequestMethod.POST)
    public String getUserNameById(String UserId, String QueryType){
        String userName = null;
        if("SQL".equals(QueryType)){
            userName = mUserService.getUserNameById4SQL(UserId);
        } else {
            userName = mUserService.getUserNameById4JPQL(UserId);
        }
        return userName;
    }

    @PostMapping(value = "updateUserNameById", params = {"UserName","UserId"})
    public int getUserById(String UserName, String UserId){
        return mUserService.updateUserNameById(UserName, UserId);
    }

    @PostMapping(value = "deleteUserById", params = "UserId")
    public int deleteUserById(String UserId){
        return mUserService.deleteUserById(UserId);
    }
}
