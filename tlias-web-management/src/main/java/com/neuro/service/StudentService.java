package com.neuro.service;

import com.neuro.pojo.PageResult;
import com.neuro.pojo.Student;
import com.neuro.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> getStudent(StudentQueryParam studentQueryParam);

    void deleteByIds( List<Integer> ids);

    void addStudent(Student student);

    Student getStudentById(Integer id);
}
