package com.neuro.controller;

import com.neuro.pojo.Result;
import com.neuro.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController()
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传:{}", file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传到oss,url:{}",url);
        return Result.success(url);
    }


/*    本地
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        log.info("接收参数: {},{},{}",name,age,file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //新文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+extension;
        //保存文件
        file.transferTo(new File("E:/Temp/images/"+newFileName));
        return Result.success();
    }*/
}
