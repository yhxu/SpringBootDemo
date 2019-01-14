package com.xuyh.utils;

import org.apache.log4j.RollingFileAppender;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @Author: xuyh
 * @Description: log4j日志记录扩展类，实现文件不存在自动创建
 * @Date: 9:11 2017/12/15
 */
@Component
public class ExtRollingFileAppender extends RollingFileAppender {

    @Override
    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        if(null != fileName && !"".equals(fileName)){
            if (System.getProperty("os.name").startsWith("Win") && fileName.startsWith("/")){
                fileName    =   "D:"+ fileName;
                File file = new File(fileName);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
            } else {
            	File file = new File(fileName);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
            }
        }
        super.setFile(fileName, append, bufferedIO, bufferSize);
    }
}
