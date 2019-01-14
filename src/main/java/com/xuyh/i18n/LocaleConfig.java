package com.xuyh.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @Author: XUYH
 * @Description: 国际化配置
 * @Date: 2019/1/14
 * @Version:
 */
@Configuration
public class LocaleConfig {
    @Bean
    public LocaleResolver localeResolver(){
        //向容器中添加自定义的 LocaleResolver 对象
        return new MyLocaleResolver();
    }
}
