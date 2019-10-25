package com.xuyh.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @Author: cleve
 * @Description:
 * @Date: 2019/7/30
 * @Version:
 */
@Configuration
@Slf4j
public class FileConfig {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Bean
    public String getFileUploadPath(){
        String filePath =  (System.getProperty("os.name").toUpperCase().startsWith("W") && fileUploadPath.startsWith("/")) ? System.getProperty("user.home") + File.separator + "Documents"  +  fileUploadPath : fileUploadPath;
        File file = new File(filePath);
        if(!file.exists()){
            if(file.mkdir()) {
                log.info("mkdir success!");
            }
        }
        log.info("upload file path: "+file.getPath());
        return filePath.endsWith("/") ? filePath : filePath + File.separator;
    }
}
