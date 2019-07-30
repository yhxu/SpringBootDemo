package com.xuyh.controller;

import com.xuyh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cleve
 * @Description: 文件上传控制器
 * @Date: 2019/7/30
 * @Version:
 */
@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "please choose one file!";
        }
        return fileService.upload(file);
    }

    @PostMapping(value = "/multiUpload")
    @ResponseBody
    public List<String> multiUpload(HttpServletRequest request) {
        List<String> returnList = new ArrayList<>();
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        if (fileList.isEmpty()) {
            returnList.add("please choose one file!");
            return returnList;
        }
        for (int i=0 ;i< fileList.size(); i++) {
            returnList.add("no["+ (i+1) +"] :" + fileService.upload(fileList.get(i)));
        }
        return returnList;
    }
}
