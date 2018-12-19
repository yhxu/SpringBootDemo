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
@MyAnnotation(value = "myBeans1")
public class MyBeans1 implements Beans{
    Logger mLogger = LoggerFactory.getLogger(MyBeans1.class);

    @Override
    public void init(){
        mLogger.info(this.toString()+"init");
    }

    @Override
    public void destroy() {
        mLogger.info(this.toString()+"destroy");
    }

    @Override
    public void process() {
        mLogger.info(this.toString()+"process");
    }
}
