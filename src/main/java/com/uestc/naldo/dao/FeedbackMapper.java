package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Feedback;
import com.uestc.naldo.domain.FeedbackItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface FeedbackMapper {

    @Insert("INSERT INTO feedback(fbid, fb_title, fb_content, fb_date, owner_oid) VALUES(null, #{title}, #{content}, #{date}, #{oid})")
    int add(Feedback feedback);

    @Select("SELECT * FROM feedback WHERE fbid=#{id}")
    @Results(id = "FeedbackById",value = {
            @Result(property = "id", column = "fbid", javaType =Long.class ),
            @Result(property = "title", column = "fb_title", javaType = String.class),
            @Result(property = "content", column = "fb_content", javaType = String.class),
            @Result(property = "date", column = "fb_date", javaType = Date.class),
            @Result(property = "oid", column = "owner_oid", javaType = Long.class)
    })
    Feedback queryFeedbackById(Long id);

    @Select("SELECT * FROM feedback")
    @Results(id = "FeedbackListAll",value = {
            @Result(property = "id", column = "fbid", javaType =Long.class ),
            @Result(property = "title", column = "fb_title", javaType = String.class),
            @Result(property = "content", column = "fb_content", javaType = String.class),
            @Result(property = "date", column = "fb_date", javaType = Date.class),
            @Result(property = "oid", column = "owner_oid", javaType = Long.class)
    })
    List<Feedback> queryFeedbackListAll();


    @Select("SELECT fbid,fb_title,fb_content,fb_date,owner_oid,oname FROM feedback,owner WHERE owner_oid=oid")
    @Results(id = "FeedbackItemListAll",value = {
            @Result(property = "id", column = "fbid", javaType =Long.class ),
            @Result(property = "title", column = "fb_title", javaType = String.class),
            @Result(property = "content", column = "fb_content", javaType = String.class),
            @Result(property = "date", column = "fb_date", javaType = Date.class),
            @Result(property = "oid", column = "owner_oid", javaType = Long.class),
            @Result(property = "oname", column = "oname", javaType = String.class)
    })
    List<FeedbackItem> queryFeedbackItemListAll();


    @SelectProvider(type = FeedbackSqlBuilder.class, method = "buildQueryFeedbackItemListByNameAndDate")
    @Results(id = "FeedbackItemListByNameAndDate",value = {
            @Result(property = "id", column = "fbid", javaType =Long.class ),
            @Result(property = "title", column = "fb_title", javaType = String.class),
            @Result(property = "content", column = "fb_content", javaType = String.class),
            @Result(property = "date", column = "fb_date", javaType = Date.class),
            @Result(property = "oid", column = "owner_oid", javaType = Long.class),
            @Result(property = "oname", column = "oname", javaType = String.class)
    })
    List<FeedbackItem> queryFeedbackItemListByNameAndDate(@Param("name") String name, @Param("date") Date date);


    class FeedbackSqlBuilder{
        public String buildQueryFeedbackItemListByNameAndDate(@Param("name") final String name, @Param("date") final Date date){
            return new SQL(){{
                SELECT("fbid,fb_title,fb_content,fb_date,owner_oid,oname");
                FROM("feedback,owner");
                WHERE("owner_oid=oid");
                if (name!=null){
                    WHERE("oname LIKE CONCAT(CONCAT('%',#{name}),'%')");
                }
                if (date!=null){
                    WHERE("fb_date=#{date}");
                }
                ORDER_BY("fb_date DESC");
            }}.toString();
        }



    }

}
