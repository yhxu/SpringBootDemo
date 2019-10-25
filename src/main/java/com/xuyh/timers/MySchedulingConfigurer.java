package com.xuyh.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: XUYH
 * @Description: 定时任务配置类，设置多个定时任务在不同的进程
 * @Date: 2018/12/27
 * @Version:
 */
@Configuration
public class MySchedulingConfigurer implements SchedulingConfigurer {
    private static final Logger mLogger = LoggerFactory.getLogger(MySchedulingConfigurer.class);

    /**
     * 获取自定义参数
     */
    @Value("${timer.pool.number}")
    private int numbers;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        mLogger.info("number of triggerTasks is    :" + scheduledTaskRegistrar.getTriggerTaskList().size());
        mLogger.info("number of cronTasks is       :" + scheduledTaskRegistrar.getCronTaskList().size());
        mLogger.info("number of fixedRateTasks is  :" + scheduledTaskRegistrar.getFixedRateTaskList().size());
        mLogger.info("number of fixedDelayTasks is :" + scheduledTaskRegistrar.getFixedDelayTaskList().size());
        scheduledTaskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(numbers, new MySchedulingThreadFactory()));
    }

    static class MySchedulingThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MySchedulingThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = String.format("pool- %d -thread-", POOL_NUMBER.getAndIncrement());
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
