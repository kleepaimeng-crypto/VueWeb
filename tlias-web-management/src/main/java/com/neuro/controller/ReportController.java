package com.neuro.controller;

import com.neuro.pojo.ClazzOption;
import com.neuro.pojo.JobOption;
import com.neuro.pojo.Result;
import com.neuro.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("获取员工入职日期统计数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("获取员工性别统计数据");
        List<Map<String,Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("获取学生学历统计数据");
        List<Map<String,Object>> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }

    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("班级人数统计");
        ClazzOption clazzOption = reportService.getClassData();
        return Result.success(clazzOption);

    }
}
