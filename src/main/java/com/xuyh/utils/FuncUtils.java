package com.xuyh.utils;

import com.xuyh.annotation.Function;
import org.nfunk.jep.JEP;
import org.nfunk.jep.function.PostfixMathCommandI;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: XUYH
 * @Description: 工具类
 * @Date: 2019/3/4
 * @Version:
 */
@Component
public class FuncUtils {
    private static JEP mExpDriver = null;

    static {
        try {
            loadFunctions();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化函数失败！");
        }
    }
    private static boolean loadFunctions() {
        if(mExpDriver == null) {
            mExpDriver = new JEP();
            Map<String, Object> mFuncMap = MyBeanUtils.getBeansWithAnnotation(Function.class);
            for (Map.Entry<String, Object> stringObjectEntry : mFuncMap.entrySet()) {
                Map.Entry<String, Object> entry = stringObjectEntry;
                mExpDriver.addFunction(entry.getKey(), (PostfixMathCommandI) entry.getValue());
            }
        }
        return true;
    }

    public static boolean calcExpressionTextAsBoolean(String pRES) throws Exception {
        synchronized(mExpDriver) {
            mExpDriver.parseExpression(pRES);
            Object pClassRes = mExpDriver.getValueAsObject();
            if(pClassRes instanceof Double) {
                double pVal = (Double)pClassRes;
                return pVal > 0.0D;
            } else {
                return false;
            }
        }
    }
}
