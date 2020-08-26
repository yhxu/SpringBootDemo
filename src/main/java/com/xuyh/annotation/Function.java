package com.xuyh.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: XUYH
 * @Description: 函数注解
 * @Date: 2020/08/26
 * @Version:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Function {
    String value() default "";
}
