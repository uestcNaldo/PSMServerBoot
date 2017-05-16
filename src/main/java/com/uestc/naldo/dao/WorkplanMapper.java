package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Workplan;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface WorkplanMapper {

    @Insert("INSERT INTO Workplan(wpid, wp_title, wp_content, wp_date, admin_aid) VALUES(null, #{title}, #{content}, #{date}, #{aid})")
    int add(Workplan workplan);

    @Delete("DELETE FROM workplan WHERE wpid=#{id}")
    int deleteById(Long id);

    @UpdateProvider(type = WorkplanSqlBuilder.class, method = "buildUpdateWorkplan")
    int update(Workplan workplan);


    @Select("SELECT * FROM workplan")
    @Results(id = "WorkplanListAll", value = {
            @Result(property = "id", column = "wpid", javaType = Long.class),
            @Result(property = "title", column = "wp_title", javaType = String.class),
            @Result(property = "content", column = "wp_content", javaType = String.class),
            @Result(property = "date", column = "wp_date", javaType = Date.class),
            @Result(property = "aid", column = "admin_aid", javaType = Long.class)
    })
    List<Workplan> queryWorkplanListAll();



    class WorkplanSqlBuilder{

        public String buildUpdateWorkplan(Workplan workplan){
            return new SQL(){{
                UPDATE("workplan");
                if (StringUtils.isNotBlank(workplan.getTitle())) SET("wp_title=#{title}");
                if (StringUtils.isNotBlank(workplan.getContent())) SET("wp_content=#{content}");
                WHERE("admin_aid=#{aid}");
            }}.toString();
        }

    }

}
