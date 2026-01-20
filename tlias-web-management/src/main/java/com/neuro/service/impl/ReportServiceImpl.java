package com.neuro.service.impl;

import com.neuro.mapper.ClaMapper;
import com.neuro.mapper.EmpMapper;
import com.neuro.mapper.StudentMapper;
import com.neuro.pojo.ClazzOption;
import com.neuro.pojo.JobOption;
import com.neuro.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClaMapper claMapper;

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.getStudentDegreeData();

        return list;
    }

    @Override
    public ClazzOption getClassData() {
        List<Map<String, Object>> list = claMapper.countClaData();
        List<Object> classlist = list.stream().map(dataMap -> dataMap.get("class")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(classlist,datalist);
    }

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {

        List<Map<String, Object>> list = empMapper.countEmpJobData();

        List<Object> joblist = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(joblist, datalist);
    }

    @Override
    public List getEmpGenderData() {

        return empMapper.countEmpGenderData();
    }
}
