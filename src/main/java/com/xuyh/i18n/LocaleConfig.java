package com.xuyh.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @Author: XUYH
 * @Description: 国际化配置
 * @Date: 2019/1/14
 * @Version:
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        // session 级别国际化参数，初始化一次之后session失效前均为该环境
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return slr;
        // cookie 级别国际化参数，初始化一次之后cookie失效前均为该环境
//        CookieLocaleResolver clr = new CookieLocaleResolver();
//        clr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return clr;
        // 向容器中添加自定义的 LocaleResolver 对象，定义一次后单次有效
        return new MyLocaleResolver();
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("l");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
