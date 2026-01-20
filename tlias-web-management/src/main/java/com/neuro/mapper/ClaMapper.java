package com.neuro.mapper;

import com.neuro.pojo.ClaQueryParam;
import com.neuro.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClaMapper {

    List<Clazz> getCla(ClaQueryParam claQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteCla(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键
    @Insert("insert into clazz (name,room,begin_date,end_date,create_time,update_time,master_id,subject) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{createTime},#{updateTime},#{masterId},#{subject})")
    void addClazz(Clazz clazz);


//    根据ID查询班级
    @Select("select clazz.id,clazz.name,clazz.room,clazz.create_time,clazz.update_time,clazz.begin_date,clazz.end_date,clazz.master_id,emp.name masterName,subject\n" +
            "               from clazz,emp where clazz.master_id = emp.id&&clazz.id = #{id}")
    Clazz searchById(Integer id);

    @Update("update clazz set name = #{name},room = #{room},begin_date = #{beginDate},end_date = #{endDate},master_id = #{masterId},subject = #{subject} where id = #{id}")
    void updateClazz(Clazz clazz);

    @MapKey("name")
    List<Map<String, Object>> countClaData();
}