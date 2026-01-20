package com.neuro.mapper;

import com.neuro.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);


    void deleteByEmpIds(List<Integer> empIds);
}
