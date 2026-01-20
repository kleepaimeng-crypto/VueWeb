package com.neuro.controller;

import com.neuro.pojo.Emp;
import com.neuro.pojo.LoginInfo;
import com.neuro.pojo.Result;
import com.neuro.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){

        log.info("登陆:{}",emp);
        LoginInfo info = empService.login(emp);
        if (info!=null){
            return Result.success(info);
        }
        return Result.error("用户名密码错误");


    }
}
