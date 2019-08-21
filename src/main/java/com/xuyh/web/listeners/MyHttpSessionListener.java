package com.xuyh.web.listeners;

import com.xuyh.annotation.Listener;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.MessageFormat;

/**
 * @Author: cleve
 * @Description: 自定义监听器
 * @Date: 2019/8/21
 * @Version:
 */
@Slf4j
@Listener
public class MyHttpSessionListener implements HttpSessionListener {
    public static int online = 0;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info(MessageFormat.format("session [{0}] created!", httpSessionEvent.getSession().getId()));
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info(MessageFormat.format("session [{0}] destroyed!", httpSessionEvent.getSession().getId()));
        online--;
    }
}
