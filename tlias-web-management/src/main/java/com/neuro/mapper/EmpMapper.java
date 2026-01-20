package com.neuro.mapper;

import com.neuro.pojo.Emp;
import com.neuro.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
员工基本信息
 */
@Mapper
public interface EmpMapper {

    public List<Emp> list(EmpQueryParam empQueryParam);



    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "            values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);


    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
