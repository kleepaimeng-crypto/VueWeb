package com.neuro.mapper;

import com.neuro.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {


    @Select("select id, name, create_time, update_time from dept order by  update_time desc")
    List<Dept> findAll();

    @Delete("Delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name ,create_time, update_time)value(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name},update_time =#{updateTime} where id =#{id} ")
    void update(Dept dept);
}
