package com.xuyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Author: XUYH
 * @Description: 控制类
 * @Date: 2018/12/12
 * @Version:
 */
@Slf4j
@RestController
public class HelloController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/hello")
    public String helloController(){
        return "Hello Spring Boot Controller!";
    }

    @GetMapping(value = "/i18n")
    public String i18nController(){
        Locale locale = LocaleContextHolder.getLocale();
        log.info("is simplified Chinese:" +locale.equals(Locale.SIMPLIFIED_CHINESE)); // is simplified Chinese:true
        log.info("the localed language is :" +locale.getLanguage()); // the localed language is :zh
        log.info("the display language is :" +locale.getDisplayLanguage()); // the display language is :中文
        log.info("the localed country is :" +locale.getCountry()); // the localed country is :CN
        log.info("the display country is :" +locale.getDisplayCountry()); // the display country is :中国
        log.info("the localed script is :" +locale.getScript()); // the localed script is :
        log.info("the display script is :" +locale.getDisplayScript()); // the display script is :
        log.info("the display name is :" +locale.getDisplayName()); // the display name is :中文 (中国)
        log.info("the display variant is :" +locale.getDisplayVariant()); // the display variant is :
        log.info("the localed variant is :" +locale.getVariant()); // the localed variant is :
        log.info("toString :" +locale.toString()); // toString :zh_CN
        log.info("the ISO3 country is :" +locale.getISO3Country()); // the ISO3 country is :CHN
        log.info("the ISO3 language is :" +locale.getISO3Language()); // the ISO3 language is :zho
        log.info("toLanguageTag :" +locale.toLanguageTag()); // toLanguageTag :zh-CN
        log.info("ExtensionKeys :" +locale.getExtensionKeys().toString()); // ExtensionKeys :[]
        log.info("UnicodeLocaleKeys :" +locale.getUnicodeLocaleKeys().toString()); // UnicodeLocaleKeys :[]
        log.info("UnicodeLocaleAttributes :" +locale.getUnicodeLocaleAttributes().toString()); // UnicodeLocaleAttributes :[]
        return messageSource.getMessage("international.message", null, "默认值", locale);
    }
}
