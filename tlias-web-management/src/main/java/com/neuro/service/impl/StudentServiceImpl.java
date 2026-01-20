package com.neuro.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuro.mapper.StudentMapper;
import com.neuro.pojo.Clazz;
import com.neuro.pojo.PageResult;
import com.neuro.pojo.Student;
import com.neuro.pojo.StudentQueryParam;
import com.neuro.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public PageResult<Student> getStudent(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.getStudent(studentQueryParam);

        LocalDate now = LocalDate.now();
        Page<Student> studentPage = (Page<Student>) studentList;

        return new PageResult<>(studentPage.getTotal(), studentPage.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {

        return studentMapper.getStudentById(id);
    }

}
