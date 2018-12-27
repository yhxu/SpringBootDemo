package com.xuyh.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: XUYH
 * @Description: 自定义配置文件测试类
 * @Date: 2018/12/27
 * @Version:
 */
@Component
@ConfigurationProperties(prefix = "database.mysql")
@PropertySource("classpath:profiles/database.properties")
public class ConnModel {
    private String username;

    private String password;

    private String url;

    private String driver;

    @Override
    public String toString() {
        return "ConnModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
