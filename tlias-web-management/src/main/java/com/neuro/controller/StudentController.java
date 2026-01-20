package com.neuro.controller;

import com.neuro.pojo.PageResult;
import com.neuro.pojo.Result;
import com.neuro.pojo.Student;
import com.neuro.pojo.StudentQueryParam;
import com.neuro.service.ReportService;
import com.neuro.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public Result getStudent(StudentQueryParam studentQueryParam){
        log.info("查询学生信息: {}",studentQueryParam);
        PageResult<Student> pageResult = studentService.getStudent(studentQueryParam);
        return Result.success(pageResult);

    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("批量删除学生");
        studentService.deleteByIds(ids);
        return Result.success();

    }

    @PostMapping
    public  Result addStudent(@RequestBody Student student){
        log.info("添加学生: {}",student);
        studentService.addStudent(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result searchById(@PathVariable Integer id){
        log.info("根据ID查询学生: {}",id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);

    }

}
