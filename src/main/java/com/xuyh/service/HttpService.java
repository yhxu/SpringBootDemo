package com.xuyh.service;

import com.xuyh.model.HolidayModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: cleve
 * @Description: http服务
 * @Date: 2019/7/19
 * @Version:
 */
@Slf4j
@Service
public class HttpService {
    @Autowired
    private RestTemplate restTemplate;

    public String getForObject(String url) {
        String returnData = restTemplate.getForObject(url, String.class);
        log.info("GET请求返回消息：" + returnData);
        return returnData;
    }

    public String getForEntity(String url) {
        ResponseEntity<String> returnEntity = restTemplate.getForEntity(url, String.class);
        log.info("GET请求返回消息：" + returnEntity.toString());
        return returnEntity.toString();
    }

    public String postForObject(String url, @Nullable Object request) {
        String returnData = restTemplate.postForObject(url, request, String.class);
        log.info("POST请求返回消息：" + returnData);
        return returnData;
    }

    public String postForObject(String url, @Nullable Object request, String... requestData) {
        String returnData = restTemplate.postForObject(url, request, String.class, (Object[]) requestData);
        log.info("POST请求返回消息：" + returnData);
        return returnData;
    }

    public HolidayModel postForHoliday(String url, @Nullable Object request, Map<String, ?> uriVariables) {
        HolidayModel returnData = restTemplate.postForObject(url, request, HolidayModel.class, uriVariables);
        log.info("POST请求返回消息：" + returnData);
        return returnData;
    }

    public String postForEntity(String url, @Nullable Object request) {
        ResponseEntity<String> returnData = restTemplate.postForEntity(url, request, String.class);
        log.info("POST请求返回消息：" + returnData.toString());
        return returnData.toString();
    }

    public String postForEntity(String url, @Nullable Object request, String... requestData) {
        ResponseEntity<String> returnData = restTemplate.postForEntity(url, request, String.class, (Object[]) requestData);
        log.info("exchange请求返回消息：" + returnData.toString());
        return returnData.toString();
    }

    public String exchange(String url, HttpMethod httpMethod, HttpEntity httpEntity) {
        ResponseEntity<String> returnData = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
        log.info("POST请求返回消息：" + returnData.getBody());
        return returnData.toString();
    }
}
