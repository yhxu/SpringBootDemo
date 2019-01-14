package com.xuyh.i18n;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2019/1/14
 * @Version:
 */
@Component
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //在 url 链接上带有 locale 参数，所以在这儿获取参数，使用指定地区语言
        String localName = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(localName)){
            String[] split = localName.split("_");
            locale = new Locale(split[0], split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
