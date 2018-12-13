package com.xuyh.service;

import com.xuyh.model.UserModel;
import com.xuyh.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: XUYH
 * @Description: 服务端
 * @Date: 2018/12/12
 * @Version:
 */
@Service
public class UserService {
    Logger mLogger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository mUserRepository;

    @Transactional
    public List<UserModel> getAll(){
        return mUserRepository.findAll();
    }

    @Transactional
    @Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator")
    public UserModel getUserByid(String UserId){
        mLogger.info("|无缓存时调用这里。。。");
        return mUserRepository.findById(UserId).get();
    }
}
