package com.neuro.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuro.mapper.ClaMapper;
import com.neuro.pojo.ClaQueryParam;
import com.neuro.pojo.Clazz;
import com.neuro.pojo.PageResult;
import com.neuro.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaServiceImpl implements ClaService {

    @Autowired
    ClaMapper claMapper;

    @Override
    public PageResult<Clazz> getCla(ClaQueryParam claQueryParam) {

        PageHelper.startPage(claQueryParam.getPage(), claQueryParam.getPageSize());

        List<Clazz> clazzList = claMapper.getCla(claQueryParam);

        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            setStatus(clazz);
        }
        Page<Clazz> clazzPage = (Page<Clazz>) clazzList;

        return new PageResult<>(clazzPage.getTotal(), clazzPage.getResult());
    }

    @Override
    public void deleteCla(Integer id) {
        claMapper.deleteCla(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        claMapper.addClazz(clazz);
    }

    @Override
    public Clazz searchById(Integer id) {

        Clazz clazz = claMapper.searchById(id);
        setStatus(clazz);

        return clazz;
    }

    @Override
    public void updateClazz(Clazz clazz) {
        claMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> searchAllClazz() {
        return claMapper.getCla(null);
    }

    public void setStatus(Clazz clazz) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = clazz.getBeginDate();
        LocalDate endDate = clazz.getEndDate();
        if (startDate != null && endDate != null) {
            if (now.isBefore(startDate)) {
                clazz.setStatus("未开始");
            } else if (now.isAfter(endDate)) {
                clazz.setStatus("已结束");
            } else {
                clazz.setStatus("进行中");
            }
        } else {
            clazz.setStatus("未知");
        }
    }
}
