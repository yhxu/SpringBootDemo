package com.xuyh.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author: cleve
 * @Description: 过滤器、拦截器配置
 * @Date: 2019/8/21
 * @Version:
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Qualifier("MyFilter")
    @Autowired
    private Filter myFilter;

    @Autowired
    private HttpSessionListener myHttpSessionListener;

    @Qualifier("MyInterceptor")
    @Autowired
    private HandlerInterceptor myHandlerInterceptor;
    /**
     * 拦截器注册
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor)
                .addPathPatterns("/getUser*");
        log.info("MyInterceptor add!");
    }
    /**
     * 过滤器注册
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> MyFilterRegister() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(myFilter);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(0);
        log.info("MyFilter register!");
        return filterFilterRegistrationBean;
    }

    /**
     * 监听器注册
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<HttpSessionListener> listenerRegister() {
        ServletListenerRegistrationBean<HttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(myHttpSessionListener);
        log.info("MyHttpSessionListener register!");
        return servletListenerRegistrationBean;
    }
}
