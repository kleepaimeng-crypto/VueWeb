package com.neuro.service;

import com.neuro.pojo.ClaQueryParam;
import com.neuro.pojo.Clazz;
import com.neuro.pojo.PageResult;

import java.util.List;

public interface ClaService {
    PageResult<Clazz> getCla(ClaQueryParam claQueryParam);

    void deleteCla(Integer id);

    void addClazz(Clazz clazz);

    Clazz searchById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> searchAllClazz();
}
