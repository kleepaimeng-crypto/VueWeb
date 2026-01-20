package com.neuro.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuro.mapper.EmpExprMapper;
import com.neuro.mapper.EmpMapper;
import com.neuro.pojo.*;
import com.neuro.service.EmpService;
import com.neuro.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        if (e!=null){
            log.info("员工登录成功:{}",e);
            //生成JWT令牌并返回
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);

        }
        return null;
    }

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Transactional
    @Override
    public void save(Emp emp) {

        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合 为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });

            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除基本信息
        empMapper.deleteByIds(ids);
        //2.删除工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public List<Emp> getEmpList() {
        List<Emp> emp = empMapper.list(new EmpQueryParam());
        return emp;
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());
    }




}
    /*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        //调用Mapper接口查询记录数,查询结果,封装
//        Long total = empMapper.count();
//
//        Integer start = (page - 1)  * pageSize;
//
//        List<Emp> rows = empMapper.list(start,pageSize);
//
//        return new PageResult<Emp>(total,rows);

        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list(name,gender,begin,end);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());
    }

}
     */

