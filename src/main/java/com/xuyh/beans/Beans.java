package com.xuyh.beans;

import com.xuyh.annotation.MyAnnotation;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/18
 * @Version:
 */
@MyAnnotation
public interface Beans {
    void init();
    void destroy();
    void process();
}
