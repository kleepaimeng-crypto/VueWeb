package com.neuro.service;

import com.neuro.pojo.Emp;
import com.neuro.pojo.EmpQueryParam;
import com.neuro.pojo.LoginInfo;
import com.neuro.pojo.PageResult;

import java.util.List;

public interface EmpService{
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    List<Emp> getEmpList();

    LoginInfo login(Emp emp);
}


//public interface EmpService{
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
//                         LocalDate begin,
//                         LocalDate end);
//}
