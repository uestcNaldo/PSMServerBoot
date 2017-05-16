package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Feedback;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface FeedbackMapper {

    @Insert("INSERT INTO feedback(fbid, fb_title, fb_content, fb_datetime, owner_oid) VALUES(null, #{title}, #{content}, #{date}, #{oid}")
    int add(Feedback feedback);




    @Select("SELECT * FROM feedback")
    @Results(id = "FeedbackListAll",value = {
            @Result(property = "id", column = "fbid", javaType =Long.class ),
            @Result(property = "title", column = "fb_title", javaType = String.class),
            @Result(property = "content", column = "fb_title", javaType = String.class),
            @Result(property = "date", column = "fb_date", javaType = Date.class),
            @Result(property = "oid", column = "fb_title", javaType = Long.class)
    })
    List<Feedback> queryAttendanceListAll();

}
