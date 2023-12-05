package com.xuyh.config;

import com.xuyh.SpringBootDemoApplication;
import com.xuyh.utils.SnowFlake;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: cleve
 * @Date: 2023/12/5 14:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Slf4j
public class SnowFlakeConfigTest extends TestCase {
    @Resource(name = "snowflakeGenerator")
    private SnowFlake snowFlake;
    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Test
    public void test() throws InterruptedException {
        for(int i=0;i<1000;i++) {
            threadPoolTaskExecutor.execute(() -> System.out.println(snowFlake.nextId()));
        }
        Thread.sleep(100);
    }
    @Test
    public void test1() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.toBinaryString(System.currentTimeMillis()-1701175289000L));
        System.out.println(Long.parseLong("0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111".replaceAll(" ",""), 2));
    }
}