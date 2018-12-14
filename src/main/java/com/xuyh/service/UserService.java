package com.xuyh.service;

import com.xuyh.model.UserModel;
import com.xuyh.repository.UserRepository;
import com.xuyh.utils.PageableImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
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
    public UserModel getUserById(String UserId){
        mLogger.info("|无缓存时调用这里。。。");
        return mUserRepository.findById(UserId).get();
    }

    @Transactional
    @Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator")
    public String getUserNameById4SQL(String UserId){
        mLogger.info("|无缓存时调用这里。。。");
        return mUserRepository.getUserById4SQL(UserId).getUserName();
    }

    @Transactional
    @Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator")
    public String getUserNameById4JPQL(String UserId){
        mLogger.info("|无缓存时调用这里。。。");
        return mUserRepository.getUserById4JPQL(UserId).getUserName();
    }

    @Transactional
    public List<UserModel> getAll4Page(int currentPage){
        PageableImpl pageable = new PageableImpl();
        pageable.setPageNumber(currentPage);
        // 以下属性可以设置也可以使用接口定义中的默认值
        pageable.setPageSize(2);
        pageable.setSort(new Sort(Sort.Direction.DESC, "UserId", "UserName"));
        return mUserRepository.findAll(pageable).getContent();
    }

    @Transactional
    public int updateUserNameById(String UserName, String UserId){
        return mUserRepository.updateUserNameById4SQL(UserName, UserId);
    }

    @Transactional
    public int deleteUserById(String UserId){
        return mUserRepository.deleteUserById4SQL(UserId);
    }
}
