package com.neuro.service;

import com.neuro.pojo.ClazzOption;
import com.neuro.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzOption getClassData();
}
