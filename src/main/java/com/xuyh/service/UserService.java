package com.xuyh.service;

import com.xuyh.model.UserModel;
import com.xuyh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepository mUserRepository;

    @Transactional
    public List<UserModel> getAll(){
        return mUserRepository.findAll();
    }
}
