package com.xuyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.ClassUtils;

@SpringBootApplication
@EnableCaching
//@EnableScheduling
@EnableConfigurationProperties
public class SpringBootDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		String path = ClassUtils.getDefaultClassLoader().getResource("application.properties").getPath();
		System.setProperty("APP.HOME", path.substring(1, path.indexOf("WEB-INF")));
		return builder.sources(SpringBootDemoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
