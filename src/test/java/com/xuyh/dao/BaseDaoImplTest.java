package com.xuyh.dao;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BaseDaoImplTest {

    private UserDaoImplTest userDaoImplTest = new UserDaoImplTest();

    @Test
    public void test() throws Exception {
        System.out.println(userDaoImplTest.getGenericClazz());
    }
}
