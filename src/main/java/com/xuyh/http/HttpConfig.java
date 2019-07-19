package com.xuyh.http;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: cleve
 * @Description:
 * @Date: 2019/7/19
 * @Version:
 */
@Component
public class HttpConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
