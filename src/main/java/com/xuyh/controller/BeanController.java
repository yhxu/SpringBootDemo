package com.xuyh.controller;

import com.xuyh.beans.Beans;
import com.xuyh.beans.MyBeans2;
import com.xuyh.interfaces.MyAnnotation;
import com.xuyh.utils.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/18
 * @Version:
 */
@RestController
public class BeanController {
    Logger mLogger = LoggerFactory.getLogger(BeanController.class);
    @GetMapping(value = "bean")
    public Map beanController(){
        Beans beans = (Beans) MyBeanUtils.getBean("myBeans1");
        beans.process();
        beans = MyBeanUtils.getBean(MyBeans2.class);
        beans.process();
        Map map = MyBeanUtils.getBeansWithAnnotation(MyAnnotation.class);
        beans = (Beans) map.get("myBeans1");
        beans.process();
        Method method = MyBeanUtils.getBeanMethod(MyBeans2.class, "process", String.class, int.class);
        try {
            String returnMsg = (String) method.invoke(MyBeanUtils.getBean(MyBeans2.class), "test", 1);
            mLogger.info(returnMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
