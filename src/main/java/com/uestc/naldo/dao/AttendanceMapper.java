package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Attendance;
import com.uestc.naldo.domain.AttendanceItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface AttendanceMapper {

    @Insert("INSERT INTO attendance(taid, ta_date, ta_status, trainer_tid) VALUES(null, #{date}, #{status}, #{tid})")
    int add(Attendance attendance);

    @Delete("DELETE FROM attendance WHERE taid=#{id}")
    int deleteById(Long id);

    @Update("UPDATE attendance SET ta_date=#{date}, ta_status=#{status} WHERE taid=#{id}")
    int update(Attendance attendance);

    @Select("SELECT * FROM attendance WHERE taid=#{id}")
    @Results(id = "AttendanceById", value = {
            @Result(property = "id", column = "taid", javaType = Long.class),
            @Result(property = "date", column = "ta_date", javaType = Date.class),
            @Result(property = "time", column = "ta_time", javaType = Time.class),
            @Result(property = "status", column = "ta_status", javaType = String.class),
            @Result(property = "tid", column = "trainer_tid", javaType = Long.class)
    })
    Attendance queryAttendanceById(Long id);

    @Select("SELECT taid,ta_date,ta_time,ta_status,trainer_tid,tname FROM attendance,trainer WHERE trainer_tid=tid")
    @Results(id = "AttendanceItemListAll", value = {
            @Result(property = "id", column = "taid", javaType = Long.class),
            @Result(property = "date", column = "ta_date", javaType = Date.class),
            @Result(property = "time", column = "ta_time", javaType = Time.class),
            @Result(property = "status", column = "ta_status", javaType = String.class),
            @Result(property = "tid", column = "trainer_id", javaType = Long.class),
            @Result(property = "tname", column = "tname", javaType = String.class)
    })
    List<AttendanceItem> queryAttendanceItemListAll();

    @SelectProvider(type = AttendanceSqlBuilder.class, method = "buildQueryAttendanceItemListByNameAndDate")
    @Results(id = "AttendanceItemListByNameAndDate", value = {
            @Result(property = "id", column = "taid", javaType = Long.class),
            @Result(property = "date", column = "ta_date", javaType = Date.class),
            @Result(property = "time", column = "ta_time", javaType = Time.class),
            @Result(property = "status", column = "ta_status", javaType = String.class),
            @Result(property = "tid", column = "trainer_id", javaType = Long.class),
            @Result(property = "tname", column = "tname", javaType = String.class)
    })
    List<AttendanceItem> queryAttendanceItemListByNameAndDate(@Param("name") String name, @Param("date") Date date);

    @Select("SELECT * FROM attendance WHERE trainer_tid=#{tid}")
    @Results(id = "AttendanceListByTid", value = {
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

    class AttendanceSqlBuilder{

        public String buildQueryAttendanceItemListByNameAndDate(@Param("name") final String name, @Param("date") final Date date){
            return new SQL(){{
                SELECT("taid,ta_date,ta_time,ta_status,trainer_tid,tname");
                FROM("attendance,trainer");
                WHERE("trainer_tid=tid");
                if (name != null){
                    WHERE("tname LIKE CONCAT(CONCAT('%',#{name}),'%')");
                }
                if (date != null){
                    WHERE("wp_date =#{date}");
                }
                ORDER_BY("taid");


            }}.toString();
        }

    }


}
