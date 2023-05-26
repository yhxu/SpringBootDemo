package com.xuyh.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

 /**
   * @Description: JSON工具类
   * @Author: xuyh
   * @Date: 2023/5/26
   */
public class JsonUtil{
    private static final String PIPE = "|";
    private static final String DOT = ".";
    private static final String ROOT = "ROOT";

    /**
     * 用于解析复杂json串，返回key对应的value值
     * @param jsonString String json字符串（可能是嵌套结构）
     * @param params 想要获取value值的key，指定层级用“.”分割 例如 first.second.third
     * @return 传入key值的value字符串，如果多个用 | 分隔。
     */
    public static String getJsonValue(String jsonString, String params) {
        String result = "";
        // 判断传入值是否为空
        if(StringUtils.isEmpty(jsonString) || StringUtils.isEmpty(params)){
            return result;
        }
        // 最后一个"."的位置+1与字符串长度相等，说明“.”在最后一位处理掉
        if(params.lastIndexOf(DOT) + 1 == params.length()){
            params = params.substring(0, params.lastIndexOf(DOT));
        }
        // 判断是否包含“.”如果包含说明是指定目标层的key
        boolean havePoint = params.indexOf(DOT) > 0;
        // 用于拼接返回值
        StringBuffer resultSB = new StringBuffer();
        // 执行获取方法
        doGetJsonValue(jsonString, params, resultSB, havePoint);
        // 如果有返回值会多一个|，删除掉
        if(resultSB.lastIndexOf(PIPE) > 0) {
            result = resultSB.deleteCharAt(resultSB.length() - 1).toString();
        }
        return result;
    }

    private static void doGetJsonValue(String jsonString, String params, StringBuffer resultSB, boolean havePoint) {
        // 将字符串转为json对象
        Object parseObject = JSON.parse(jsonString);
        // 判断是jsonObject还是jsonArray，针对不同对象分别处理
        if(parseObject instanceof JSONObject){
            // 执行从jsonObject中获取对象的方法
            doGetJsonObjectValue((JSONObject) parseObject, params, resultSB, havePoint);
        }
        if(parseObject instanceof JSONArray){
            JSONArray jsonArray = ((JSONArray) parseObject);
            // 遍历数组，获取具体对象
            for(Object object : jsonArray){
                // 执行从jsonObject中获取对象的方法
                doGetJsonObjectValue( (JSONObject) object, params, resultSB, havePoint);
            }
        }
    }

    private static void doGetJsonObjectValue(JSONObject jsonObject, String params, StringBuffer resultSB, boolean havePoint) {
         // 判断处理还是进入下一层
        int indexPoint = params.indexOf(DOT);
        if(indexPoint > 0){
            String jsonKey = params.substring(0, indexPoint);
            // key值为root说明传参为ROOT.key类型，此时直接将jsonObject进行下一次递归
            if(ROOT.equals(jsonKey)){
                doGetJsonValue(jsonObject.toJSONString(), params.substring(indexPoint + 1), resultSB, havePoint);
            }
            if(jsonObject.containsKey(jsonKey)) {
                doGetJsonValue(jsonObject.getString(jsonKey), params.substring(indexPoint + 1), resultSB, havePoint);
            }
        } else {
            // 判断对象中是否存在key，存在获取value并拼接
            if (jsonObject.containsKey(params)) {
                resultSB.append(jsonObject.getString(params)).append(PIPE);
            }
            // 不指定层级需要遍历每一层
            if(!havePoint) {
                // 遍历所有key的value，取出value值为jsonObject和jsonArray类型的继续递归处理
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    String objectKey = entry.getKey();
                    Object objectValue = entry.getValue();
                    if (!params.equals(objectKey) // 这种情况在上边已经处理过了
                            && (objectValue instanceof JSONObject || objectValue instanceof JSONArray)) {
                        // 执行获取方法
                        doGetJsonValue(objectValue.toString(), params, resultSB, false);
                    }
                }
            }
        }
    }
}

