package com.xuyh.service;

import com.xuyh.files.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: cleve
 * @Description:
 * @Date: 2019/7/30
 * @Version:
 */
@Slf4j
@Service
public class FileService {
    @Autowired
    private FileConfig fileConfig;

    public String upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = fileConfig.getFileUploadPath();
        File destfile = new File(filePath + fileName);
        try {
            file.transferTo(destfile);
            log.info("upload success file path :" + destfile.getPath());
            return "success!";
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "upload file fail!";
    }
}
