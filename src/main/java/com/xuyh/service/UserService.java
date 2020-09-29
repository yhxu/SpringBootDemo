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
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PageableImpl pageable;

    @Transactional
    public List<UserModel> getAll(){
        return mUserRepository.findAll();
    }

    @Transactional
    @Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator")
    public UserModel getUserById(String UserId){
        mLogger.info("|无缓存时调用这里。。。");
        Optional<UserModel> optional = mUserRepository.findById(UserId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
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

    /**
     * select usermodel0_.userId as userId1_1_, usermodel0_.userAge as userAge2_1_, usermodel0_.UserCardId as UserCard3_1_, usermodel0_.userMail as userMail4_1_, usermodel0_.userName as userName5_1_, usermodel0_.userPhoneNumber as userPhon6_1_, usermodel0_.userSex as userSex7_1_ from USER usermodel0_ where usermodel0_.UserCardId=? and (usermodel0_.userName like ?) and (usermodel0_.userSex=? or usermodel0_.userSex=?) and usermodel0_.userAge=18 order by usermodel0_.userId asc
     * @param userModel
     * @return
     */
    public List<UserModel> getList(UserModel userModel){
        List<Predicate> predicate=new ArrayList<>();
        CriteriaBuilder cb = mUserRepository.getCriteriaBuilder();
        Root<UserModel> register = mUserRepository.getRegister();
        //身份证号
        if (!StringUtils.isEmpty(userModel.getUserCardId())) {
            predicate.add(cb.equal(register.<String> get("userCardId"), userModel.getUserCardId() ));
        }

        //姓名
        if (!StringUtils.isEmpty(userModel.getUserName())) {
            predicate.add(cb.like(register.get("userName"), '%' + userModel.getUserName() + '%'));
        }

        //性别
        if (!StringUtils.isEmpty(userModel.getUserSex())) {
            Predicate p1 = cb.equal(register.<String> get("userSex"), userModel.getUserSex());
            Predicate p2 = cb.equal(register.<String> get("userSex"), userModel.getUserSex());
            predicate.add(cb.or(p1, p2));
        }

        //年龄
        if (!StringUtils.isEmpty(userModel.getUserAge())) {
            predicate.add(cb.equal(register.<Integer> get("userAge"), userModel.getUserAge()));
        }
        Order order = cb.asc(register.<String> get("userId"));
        return mUserRepository.queryList(predicate.toArray(new Predicate[0]), order);
    }
}
