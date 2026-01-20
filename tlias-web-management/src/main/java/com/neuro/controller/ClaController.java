package com.neuro.controller;

import com.neuro.pojo.*;
import com.neuro.service.ClaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClaController {

    @Autowired
    ClaService claService;

    @GetMapping
    public Result getCla(ClaQueryParam claQueryParam){
        log.info("查询班级信息: {}",claQueryParam);
        PageResult<Clazz> pageResult = claService.getCla(claQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("删除班级: {}",id);
        claService.deleteCla(id);
        return Result.success();
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级: {}",clazz);
        claService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result searchById(@PathVariable Integer id){
        log.info("根据ID查询班级: {}",id);
        Clazz clazz = claService.searchById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("修改班级: {}",clazz);
        claService.updateClazz(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectAllClazz(){
        log.info("查询所有班级");
        List<Clazz> result =  claService.searchAllClazz();
        return Result.success(result);
    }


}
