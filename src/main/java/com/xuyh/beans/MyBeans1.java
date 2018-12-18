package com.xuyh.beans;

import com.xuyh.interfaces.MyAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/18
 * @Version:
 */
@MyAnnotation("myBeans1")
public class MyBeans1 implements Beans{
    Logger mLogger = LoggerFactory.getLogger(MyBeans1.class);
    public void init(){
        mLogger.info(this.toString());
    }
}
