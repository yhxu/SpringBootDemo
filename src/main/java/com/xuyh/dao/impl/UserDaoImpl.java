package com.xuyh.dao.impl;

import com.xuyh.dao.UserDao;
import com.xuyh.model.UserModel;

import javax.persistence.EntityManager;

public class UserDaoImpl extends BaseDaoImpl<UserModel> implements UserDao {

    public UserDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
