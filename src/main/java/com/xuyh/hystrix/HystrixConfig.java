package com.xuyh.hystrix;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XUYH
 * @Description: hystrix配置
 * @Date: 2019/1/3
 * @Version:
 */
@Configuration
public class HystrixConfig {
    @Bean
    public HystrixCommandAspect hystrixCommandAspect(){
        return new HystrixCommandAspect();
    }
}
