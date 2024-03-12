package com.xuyh.zookeeper.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>className: AdapterZKConfig.java </p>
 * <p>Copyright: Copyright (C) 2006 </p>
 * <p>Company: 青牛技术股份有限公司 </p>
 * <p>
 * Description: zk配置项
 * </p>
 * <p>Author: cleve </p>
 * <p>CreateDate: 2024/3/12 14:30 </p>
 */
@Configuration
@ConfigurationProperties(prefix = "adapter.zookeeper")
@Data
@ToString
public class AdapterZKConfig {
    private String root;
    private String platformId;
    private String platformSubId;
}
