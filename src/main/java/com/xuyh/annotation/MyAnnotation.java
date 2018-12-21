package com.xuyh.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: XUYH
 * @Description: 自定义注释测试
 * @Date: 2018/12/18
 * @Version:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyAnnotation{
    String value() default "";
}
