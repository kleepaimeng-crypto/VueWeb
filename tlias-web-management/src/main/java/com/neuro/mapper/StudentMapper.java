package com.neuro.mapper;

import com.neuro.pojo.Student;
import com.neuro.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {


    List<Student> getStudent(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into student (name,no,gender,phone,degree,clazz_id,id_card,is_college,address,graduation_date,create_time,update_time)" +
            "value (#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{idCard},#{isCollege},#{address},#{graduationDate},#{createTime},#{updateTime})")
    void addStudent(Student student);

    @Select("select * from student,clazz where student.id = #{id}")
    Student getStudentById(Integer id);

    @MapKey("class")
    List<Map<String, Object>> getStudentDegreeData();
}
