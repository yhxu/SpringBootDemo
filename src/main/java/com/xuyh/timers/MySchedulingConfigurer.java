package com.xuyh.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger mLogger = LoggerFactory.getLogger(MySchedulingConfigurer.class);

    // 获取自定义参数
    @Value("${timer.pool.number}")
    private int numbers;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        mLogger.info("number of triggerTasks is    :" + scheduledTaskRegistrar.getTriggerTaskList().size());
        mLogger.info("number of cronTasks is       :" + scheduledTaskRegistrar.getCronTaskList().size());
        mLogger.info("number of fixedRateTasks is  :" + scheduledTaskRegistrar.getFixedRateTaskList().size());
        mLogger.info("number of fixedDelayTasks is :" + scheduledTaskRegistrar.getFixedDelayTaskList().size());
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(numbers));
    }
}
