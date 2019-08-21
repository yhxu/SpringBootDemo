package com.xuyh.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: cleve
 * @Description: 自定义标签，filter
 * @Date: 2019/8/21
 * @Version:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Filter {
    String value() default "";
}
