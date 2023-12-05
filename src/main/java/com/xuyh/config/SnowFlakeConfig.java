package com.xuyh.config;

import com.xuyh.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>雪花算法生成器</p>
 * @fileName： SnowFlakeConfig.java
 * @Author： cleve
 * @Date： 2023/11/28 20:52
 */
@Slf4j
@Configuration
public class SnowFlakeConfig {
    @Value("${snowflake.data-center-id:1}")
    private int dataCenterId;
    @Value("${snowflake.machine-id:1}")
    private int machineId;

    @Bean("snowflakeGenerator")
    public SnowFlake snowflakeGenerator(){
        log.info("data-center-id={},machine-id={}", dataCenterId, machineId);
        return new SnowFlake(dataCenterId, machineId);
    }
}
