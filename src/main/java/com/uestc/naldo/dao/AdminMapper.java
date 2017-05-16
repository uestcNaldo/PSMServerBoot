package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface AdminMapper {

    @Insert("INSERT IGNORE INTO admin(aid, ausername, apassword, aname, aemail) VALUES(null, #{username}, #{password}, #{name}, #{email})")
    int add(Admin admin);

    @Update("UPDATE admin SET ausername=#{username}, apassword=#{password}, aname=#{name}, aemail=#{email} WHERE aid=#{id}")
    int update(Admin admin);

    @Select("SELECT * FROM admin WHERE aid=#{id}")
    @Results(id = "AdminResultById", value = {
            @Result(property = "id", column = "aid", javaType = Long.class),
            @Result(property = "username", column = "ausername", javaType = String.class),
            @Result(property = "password", column = "apassword", javaType = String.class),
            @Result(property = "name", column = "aname", javaType = String.class),
            @Result(property = "email", column = "aemail", javaType = String.class)
    })

    Admin queryById(Long id);


    @Select("SELECT * FROM admin")
    @Results(id = "AdminListAll", value = {
            @Result(property = "id", column = "aid", javaType = Long.class),
            @Result(property = "username", column = "ausername", javaType = String.class),
            @Result(property = "password", column = "apassword", javaType = String.class),
            @Result(property = "name", column = "aname", javaType = String.class),
            @Result(property = "email", column = "aemail", javaType = String.class)
    })
    List<Admin> queryAdminListAll();

    @Delete("DELETE FROM admin WHERE aid=#{id}")
    int deleteById(Long id);


}
