package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Attendance;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface AttendanceMapper {

    @Insert("INSERT INTO attendance(taid, ta_date, ta_status, trainer_tid) VALUES(null, #{date}, #{status}, #{tid}")
    int add(Attendance attendance);


    @Update("UPDATE attendance SET ta_date=#{date}, ta_status=#{status} WHERE trainer_tid=#{tid}")
    int update(Attendance attendance);



    @Select("SELECT * FROM attendance WHERE trainer_tid=#{tid}")
    @Results(id = "AttendanceResultById", value = {
            @Result(property = "id", column = "taid", javaType = Long.class),
            @Result(property = "date", column = "ta_date", javaType = Date.class),
            @Result(property = "time", column = "ta_time", javaType = Time.class),
            @Result(property = "status", column = "ta_status", javaType = String.class),
            @Result(property = "tid", column = "trainer_id", javaType = Long.class)
    })
    List<Attendance> queryAttendanceListByTid(Long tid);


    @Select("SELECT * FROM attendance")
    @Results(id = "AttendanceListAll", value = {
            @Result(property = "id", column = "taid", javaType = Long.class),
            @Result(property = "date", column = "ta_date", javaType = Date.class),
            @Result(property = "time", column = "ta_time", javaType = Time.class),
            @Result(property = "status", column = "ta_status", javaType = String.class),
            @Result(property = "tid", column = "trainer_id", javaType = Long.class)
    })
    List<Attendance> queryAttendanceListAll();

}
