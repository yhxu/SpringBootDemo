package com.xuyh.controller;

import com.xuyh.beans.Beans;
import com.xuyh.beans.MyBeans2;
import com.xuyh.interfaces.MyAnnotation;
import com.xuyh.utils.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/18
 * @Version:
 */
@RestController
public class BeanController {
    @GetMapping(value = "bean")
    public Map beanController(){
        Beans beans1 = (Beans) BeanUtils.getBean("myBeans1");
        beans1.init();
        Beans beans2 = BeanUtils.getBean(MyBeans2.class);
        beans2.init();
        Map map = BeanUtils.getBeansWithAnnotation(MyAnnotation.class);
        beans1 = (Beans) map.get("myBeans1");
        beans1.init();
        return map;
    }
}
