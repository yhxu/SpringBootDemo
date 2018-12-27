package com.xuyh.timers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Author: XUYH
 * @Description: 定时任务配置类，设置多个定时任务在不同的进程
 * @Date: 2018/12/27
 * @Version:
 */
@Configuration
public class MySchedulingConfigurer implements SchedulingConfigurer {
    // 获取自定义参数
    @Value("${timer.pool.number}")
    private int numbers;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(numbers));
    }
}
