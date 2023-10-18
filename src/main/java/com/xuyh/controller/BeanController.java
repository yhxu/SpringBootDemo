package com.xuyh.controller;

import com.xuyh.annotation.MyAnnotation;
import com.xuyh.beans.Beans;
import com.xuyh.beans.MyBeans2;
import com.xuyh.utils.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    /**
     * @Author: xuyh
     * @Description: 当一个bean类型对应多个实现类时，
     * 可以使用@Resource注解指定名称注入，
     * 也可以使用@Autowired + @Qualifier指定名称注入
     * @Date: 14:18 2019/1/15
     */
    @Qualifier(value = "myBeans1")
    @Autowired
//    @Resource(name = "myBeans1")
    private Beans beans;

    @Autowired
//    @Resource
    private Map<String, Beans> beansMap;

    @GetMapping(value = "bean")
    public Map beanController(){
        beans.init();
        Beans beans = (Beans) MyBeanUtils.getBean("myBeans1");
        beans.process();
        beans = beansMap.get("myBeans1");
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
