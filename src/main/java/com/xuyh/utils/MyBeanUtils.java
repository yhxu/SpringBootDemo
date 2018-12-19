package com.xuyh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/18
 * @Version:
 */
@Component
public class MyBeanUtils extends BeanUtils implements ApplicationContextAware{
    private Logger mLogger = LoggerFactory.getLogger(MyBeanUtils.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(MyBeanUtils.applicationContext == null){
            MyBeanUtils.applicationContext = applicationContext;
        }
        mLogger.info("ApplicationContext config success!");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过method name,以及Clazz返回指定的method
    public static Method getBeanMethod(Class<?> clazz, String name, Class... paramTypes){
        return findMethod(clazz, name, paramTypes);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType){
        return getApplicationContext().getBeansWithAnnotation(annotationType);
    }
}
