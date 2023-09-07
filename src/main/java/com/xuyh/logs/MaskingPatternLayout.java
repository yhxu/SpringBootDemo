package com.xuyh.logs;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对敏感信息进行掩盖。
 * 1.实现原理
 * 对产生的日志信息,进行正则匹配和替换。
 * <p>
 * 2.目前包括如下类型的信息:手机号，座机号
 * <p>
 * 3.如何进行扩展新的正则类型
 * (1)在PatternType枚举中新增一个正则
 * (2)extractMatchesByType对新增的正则做处理
 * (3)maskByType对新增的正则做处理
 * <p>
 */
public class MaskingPatternLayout extends PatternLayout {
    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);
        try {
            for (PatternType patternType : PatternType.values()) {
                // 1.生成matcher
                Matcher matcher = patternType.getPattern().matcher(message);
                // 2.获取匹配的信息
                Set<String> matches = extractMatchesByType(matcher);
                // 3.掩盖匹配的信息
                if (!ObjectUtils.isEmpty(matches)) {
                    message = maskByType(patternType, message, matches);
                }
            }
        } catch (Throwable ignored){

        }
        return message;
    }

    /**
     * 1.提取匹配的所有字符串中某一个分组
     * group(0):表示不分组,整个表达式的值
     * group(i),i>0:表示某一个分组的值
     * <p>
     * 2.使用Set进行去重
     */
    private Set<String> extractMatchesByType(Matcher matcher) {
        Set<String> matches = new HashSet<>();
        int count = matcher.groupCount();
        while (matcher.find()) {
            if (count == 0) {
                matches.add(matcher.group());
                continue;
            }
            for (int i = 1; i <= count; i++) {
                String match = matcher.group(i);
                if (null != match) {
                    matches.add(match);
                }
            }
        }
        return matches;
    }

    /**
     * 根据不同类型敏感信息做相应的处理
     */
    private String maskByType(PatternType key, String message, Set<String> matches) {
        switch (key){
            case PHONE_NUMBER:
                return maskPhoneNumber(message, matches);
            case TEL_NUMBER_THREE:
                return maskTelNumber(message, matches, 3);
            case TEL_NUMBER_FOUR:
                return maskTelNumber(message, matches, 4);
            default:
                return message;
        }
    }

    private String maskPhoneNumber(String message, Set<String> matches) {
        for (String match : matches) {
            // 1.处理获取的字符
            char[] chars = match.toCharArray();
            for(int i=0;i<chars.length;i++){
                if(i>3&&i<8){
                    chars[i] = '*';
                }
            }
            // 2.String的替换
            message = message.replace(match, new String(chars));
        }
        return message;
    }

    private String maskTelNumber(String message, Set<String> matches, int number) {
        for (String match : matches) {
            // 1.处理获取的字符
            char[] chars = match.toCharArray();
            int index = match.indexOf("-");
            for(int i=0;i<chars.length;i++){
                if(index>0 && i>=index && i<chars.length-3){
                    chars[i] = '*';
                }
                if(index<0 && i>number &&i<chars.length-3){
                    chars[i] = '*';
                }
            }
            // 2.String的替换
            message = message.replace(match, new String(chars));
        }
        return message;
    }

    /**
     * 定义敏感信息类型
     */
    private enum PatternType {
        PHONE_NUMBER("手机号", Pattern.compile("[^\\d]1[3-9]\\d{9}[^\\d]")),
        TEL_NUMBER_THREE("座机号三位区号", Pattern.compile("[^\\d][0,8][1,2][0-9]-?\\d{7,8}[^\\d]")),
        TEL_NUMBER_FOUR("座机号四位区号", Pattern.compile("[^\\d]0[3-9]\\d{2}-?\\d{7,8}[^\\d]")),
        ;

        private final String description;
        private final Pattern pattern;

        PatternType(String description, Pattern pattern) {
            this.description = description;
            this.pattern = pattern;
        }

        public String getDescription() {
            return description;
        }

        public Pattern getPattern() {
            return pattern;
        }
    }

}