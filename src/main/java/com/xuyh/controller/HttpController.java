package com.xuyh.controller;

import com.xuyh.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cleve
 * @Description: http
 * @Date: 2019/7/19
 * @Version:
 */
@Slf4j
@RestController
public class HttpController {
    @Autowired
    private HttpService httpService;

    @GetMapping(value = "getForObjectNoParameter")
    public String getForObject() {
        return httpService.getForObject("http://192.168.22.128:1011/SpringBootDemo-0.0.1-SNAPSHOT/hello");
    }

    @GetMapping(value = "getForEntityNoParameter")
    public String getForEntity() {
        return httpService.getForEntity("http://192.168.22.128:1011/SpringBootDemo-0.0.1-SNAPSHOT/hello");
    }

    /**
     * postMan有返回值
     * nullUserModel{UserId='0008', UserName='琪琪', UserAge=27, UserSex='M', UserCardId='null', UserPhoneNumber='null', UserMail='null'}UserModel{UserId='0007', UserName='王五1', UserAge=26, UserSex='F', UserCardId='null', UserPhoneNumber='null', UserMail='null'}
     * @return
     */
    @PostMapping(value = "postForObjectNoParameter")
    public String postForObject() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("QueryType", "PAGE");
        map.add("CurrentPage", "1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return httpService.postForObject("http://192.168.22.128:1010/SpringBootDemo-0.0.1-SNAPSHOT/getAllUsers", request);
    }

    /**
     * postman 有返回值
     * nullUserModel{UserId='0008', UserName='琪琪', UserAge=27, UserSex='M', UserCardId='null', UserPhoneNumber='null', UserMail='null'}UserModel{UserId='0007', UserName='王五1', UserAge=26, UserSex='F', UserCardId='null', UserPhoneNumber='null', UserMail='null'}
     * @param CurrentPage
     * @param QueryType
     * @return
     */
    @PostMapping(value = "postForObject", params = {"QueryType","CurrentPage"})
    public String postForObject(String CurrentPage, String QueryType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("QueryType", QueryType);
        map.add("CurrentPage", CurrentPage);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return httpService.postForObject("http://192.168.22.128:1010/SpringBootDemo-0.0.1-SNAPSHOT/getAllUsers", request, QueryType, CurrentPage);
    }

    /**
     * postMan有返回值
     * <200,nullUserModel{UserId='0008', UserName='琪琪', UserAge=27, UserSex='M', UserCardId='null', UserPhoneNumber='null', UserMail='null'}UserModel{UserId='0007', UserName='王五1', UserAge=26, UserSex='F', UserCardId='null', UserPhoneNumber='null', UserMail='null'},{Content-Type=[text/plain;charset=UTF-8], Content-Length=[261], Date=[Fri, 19 Jul 2019 03:46:46 GMT]}>
     * @return
     */
    @PostMapping(value = "postForEntityNoParameter")
    public String postForEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("QueryType", "PAGE");
        map.add("CurrentPage", "1");
        return httpService.postForEntity("http://192.168.22.128:1010/SpringBootDemo-0.0.1-SNAPSHOT/getAllUsers", new HttpEntity<>(map, headers));
    }

    /**
     * postman 有返回值
     * <200,nullUserModel{UserId='0008', UserName='琪琪', UserAge=27, UserSex='M', UserCardId='null', UserPhoneNumber='null', UserMail='null'}UserModel{UserId='0007', UserName='王五1', UserAge=26, UserSex='F', UserCardId='null', UserPhoneNumber='null', UserMail='null'},{Content-Type=[text/plain;charset=UTF-8], Content-Length=[261], Date=[Fri, 19 Jul 2019 03:46:46 GMT]}>
     * @param CurrentPage
     * @param QueryType
     * @return
     */
    @PostMapping(value = "postForEntity", params = {"QueryType","CurrentPage"})
    public String postForEntity(String CurrentPage, String QueryType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("QueryType", QueryType);
        map.add("CurrentPage", CurrentPage);
        return httpService.postForEntity("http://192.168.22.128:1010/SpringBootDemo-0.0.1-SNAPSHOT/getAllUsers", new HttpEntity<>(map, headers), QueryType, CurrentPage);
    }

    @PostMapping(value = "exchangeNoParameter")
    public String exchange() {
        return httpService.exchange("http://192.168.22.128:1011/SpringBootDemo-0.0.1-SNAPSHOT/hello", HttpMethod.GET, null);
    }


    @PostMapping(value = "exchange", params = {"QueryType","CurrentPage"})
    public String exchange(String CurrentPage, String QueryType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("QueryType", QueryType);
        map.add("CurrentPage", CurrentPage);
        return httpService.exchange("http://192.168.22.128:1010/SpringBootDemo-0.0.1-SNAPSHOT/getAllUsers", HttpMethod.POST, new HttpEntity<>(map, headers));
    }

}
