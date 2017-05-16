package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface CourseMapper {

    @Insert("INSERT INTO course(pcid, pc_name, pc_inst, pc_condition, pc_duration, pc_content, pc_prise, pc_note) VALUES(null, #{name}, #{inst}, #{condition}, #{duration}, #{content}, #{prise}, #{note})")
    int add(Course course);

    @Update("UPDATE course SET pc_name=#{name}, pc_inst=#{inst}, pc_condition=#{condition}, pc_duration=#{duration}, pc_content=#{content}, pc_prise=#{prise}, pc_note=#{note} WHERE id=#{pcid}")
    int update(Course course);

    @Delete("DELETE FROM course WHERE pcid=#{id}")
    int deleteById(Long id);

    @Select("SELECT * FROM course")
    @Results(id = "CourseListAll",value = {
            @Result(property = "id", column = "pcid",javaType = Long.class),
            @Result(property = "name", column = "pc_name", javaType = String.class),
            @Result(property = "inst", column = "pc_inst", javaType = String.class),
            @Result(property = "condition", column = "pc_condition", javaType = String.class),
            @Result(property = "duration", column = "pc_duration", javaType = String.class),
            @Result(property = "content", column = "pc_content", javaType = String.class),
            @Result(property = "prise", column = "pc_prise", javaType = String.class),
            @Result(property = "note", column = "pc_note", javaType = String.class)
    })
    List<Course> queryCourseListAll();

    @Select("SELECT * FROM course WHERE pc_name like '%#{name}%' || '%' ORDER BY pcid")
    @Results(id = "CourseListByName",value = {
            @Result(property = "id", column = "pcid",javaType = Long.class),
            @Result(property = "name", column = "pc_name", javaType = String.class),
            @Result(property = "inst", column = "pc_inst", javaType = String.class),
            @Result(property = "condition", column = "pc_condition", javaType = String.class),
            @Result(property = "duration", column = "pc_duration", javaType = String.class),
            @Result(property = "content", column = "pc_content", javaType = String.class),
            @Result(property = "prise", column = "pc_prise", javaType = String.class),
            @Result(property = "note", column = "pc_note", javaType = String.class)
    })
    List<Course> queryCourseListByName(String name);


}
