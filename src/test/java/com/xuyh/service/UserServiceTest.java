package com.xuyh.service;

import com.xuyh.SpringBootDemoApplication;
import com.xuyh.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = SpringBootDemoApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        UserModel userModel = new UserModel();
        userModel.setUserCardId("23081119921010123X");
        userModel.setUserSex("M");
        userModel.setUserAge(18);
        userModel.setUserName("å¼ ä¸‰");//乱码问题待解决
        List<UserModel> userModelList = userService.getList(userModel);
        for(UserModel user:userModelList){
            System.out.println(user.toString());
        }

    }
}
