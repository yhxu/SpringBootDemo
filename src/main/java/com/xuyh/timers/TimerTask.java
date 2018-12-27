package com.xuyh.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: XUYH
 * @Description: 定时任务
 * @Date: 2018/12/27
 * @Version:
 */
@Component

public class TimerTask {
    private static final Logger mLogger = LoggerFactory.getLogger(TimerTask.class);
    private static final SimpleDateFormat mDataFormate = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * @Author: xuyh
     * @Description: 按照时间间隔执行定时任务
     * @Date: 10:02 2018/12/27
     */
    @Scheduled(fixedRate = 2000)    // 上一次开始执行时间点后2秒再次执行；
//    @Scheduled(fixedDelay = 2000) // 上一次执行完毕时间点后2秒再次执行；
//    @Scheduled(initialDelay=1000, fixedDelay=3000);//第一次延迟1秒执行，然后在上一次执行完毕时间点3秒再次执行；
    public void task1(){
        mLogger.info("TimerTask.task1 :"+ mDataFormate.format(new Date()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Scheduled(fixedRate = 2000)
    public void task11(){
        mLogger.info("TimerTask.task11:"+ mDataFormate.format(new Date()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author: xuyh
     * @Description: 定点执行定时任务
     * @Date: 10:14 2018/12/27
     */
    @Scheduled(cron = "0 28 10 ? * *") // 具体格式见cronexpression.txt
    public void task2(){
        mLogger.info("TimerTask.task2 :"+mDataFormate.format(new Date()));
    }
}
