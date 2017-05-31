package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Owner;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface OwnerMapper {


    @Select("SELECT * FROM owner WHERE ousername=#{username} AND opassword=#{password}")
    @Results(id = "OwnerByUANDP",value = {
            @Result(property = "id", column = "oid", javaType = Long.class),
            @Result(property = "username", column = "ousername", javaType = String.class),
            @Result(property = "password", column = "opassword", javaType = String.class),
            @Result(property = "name", column = "oname", javaType = String.class),
            @Result(property = "email", column = "oemail", javaType = String.class),
            @Result(property = "sex", column = "osex", javaType = String.class),
            @Result(property = "age", column = "oage", javaType = Integer.class)
    })
    Owner loginByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT IGNORE INTO owner(oid, ousername, opassword, oname, oemail, osex, oage) VALUES(null, #{username}, #{password}, #{name}, #{email}, #{sex}, #{age}) ")
    int add(Owner owner);

    @Delete("DELETE FROM owner WHERE oid=#{id}")
    int deleteById(Long id);

    @UpdateProvider(type = OwnerSqlBuilder.class, method = "buildUpdateOwner")
    int update(Owner owner);

    @Select("SELECT * FROM owner WHERE oid=#{id}")
    @Results(id = "OwnerById",value = {
            @Result(property = "id", column = "oid", javaType = Long.class),
            @Result(property = "username", column = "ousername", javaType = String.class),
            @Result(property = "password", column = "opassword", javaType = String.class),
            @Result(property = "name", column = "oname", javaType = String.class),
            @Result(property = "email", column = "oemail", javaType = String.class),
            @Result(property = "sex", column = "osex", javaType = String.class),
            @Result(property = "age", column = "oage", javaType = Integer.class)
    })
    Owner queryOwnerById(Long id);


    @Select("SELECT * FROM owner")
    @Results(id = "OwnerListAll",value = {
            @Result(property = "id", column = "oid", javaType = Long.class),
            @Result(property = "username", column = "ousername", javaType = String.class),
            @Result(property = "password", column = "opassword", javaType = String.class),
            @Result(property = "name", column = "oname", javaType = String.class),
            @Result(property = "email", column = "oemail", javaType = String.class),
            @Result(property = "sex", column = "osex", javaType = String.class),
            @Result(property = "age", column = "oage", javaType = Integer.class)
    })
    List<Owner> queryOwnerListAll();

    @SelectProvider(type = OwnerSqlBuilder.class, method = "buildQueryOwnerListByName")
    @Results(id = "OwnerListByName",value = {
            @Result(property = "id", column = "oid", javaType = Long.class),
            @Result(property = "username", column = "ousername", javaType = String.class),
            @Result(property = "password", column = "opassword", javaType = String.class),
            @Result(property = "name", column = "oname", javaType = String.class),
            @Result(property = "email", column = "oemail", javaType = String.class),
            @Result(property = "sex", column = "osex", javaType = String.class),
            @Result(property = "age", column = "oage", javaType = Integer.class)
    })
    List<Owner> queryOwnerListByName(String name);

    class OwnerSqlBuilder{
        public String buildUpdateOwner(final Owner owner){
            return new SQL(){{
                UPDATE("owner");
                if (!(owner.getUsername() == null)){
                    SET("ousername=#{username}");
                }
                if (!(owner.getPassword() == null)){
                    SET("opassword=#{password}");
                }
                if (!(owner.getName()==null)) {
                    SET("oname=#{name}");
                }
                if (!(owner.getEmail()==null)){
                    SET("oemail=#{email}");
                }
                if (!(owner.getAge()==null)){
                    SET("oage=#{age}");
                }
                if (!(owner.getSex()==null)){
                    SET("osex=#{sex}");
                }
                WHERE("oid=#{id}");

            }}.toString();
        }

        public String buildQueryOwnerListByName(final String name){
            return new SQL(){{
                SELECT("*");
                FROM("owner");
                WHERE("oname like CONCAT(CONCAT('%',#{name}),'%')");
                ORDER_BY("oid");

            }}.toString();
        }
    }

}
