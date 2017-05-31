package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Trainer;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface TrainerMapper {


    @Select("SELECT * FROM trainer WHERE tusername=#{username} AND tpassword=#{password}")
    @Results(id = "TrainerLogin",value = {
            @Result(property = "id", column = "tid", javaType = Long.class),
            @Result(property = "username", column = "tusername", javaType = String.class),
            @Result(property = "password", column = "tpassword", javaType = String.class),
            @Result(property = "name", column = "tname", javaType = String.class),
            @Result(property = "age", column = "tage", javaType = Integer.class),
            @Result(property = "sex", column = "tsex", javaType = String.class),
            @Result(property = "email", column = "temail", javaType = String.class),
            @Result(property = "position", column = "tposition", javaType = String.class),
            @Result(property = "maxim", column = "tmaxim", javaType = String.class),
            @Result(property = "intro", column = "tintro", javaType = String.class)
    })
    Trainer login(@Param("username") String username, @Param("password") String password);

    @InsertProvider(type = TrainerSqlBuilder.class, method = "buildAddTrainer")
    int add(Trainer trainer);

    @Delete("DELETE FROM trainer WHERE tid=#{id}")
    int deleteById(Long id);

    @UpdateProvider(type = TrainerSqlBuilder.class, method = "buildUpdateTrainer")
    int update(Trainer trainer);

    @Select("SELECT * FROM trainer WHERE tid=#{id}")
    @Results(id = "TrainerById",value = {
            @Result(property = "id", column = "tid", javaType = Long.class),
            @Result(property = "username", column = "tusername", javaType = String.class),
            @Result(property = "password", column = "tpassword", javaType = String.class),
            @Result(property = "name", column = "tname", javaType = String.class),
            @Result(property = "age", column = "tage", javaType = Integer.class),
            @Result(property = "sex", column = "tsex", javaType = String.class),
            @Result(property = "email", column = "temail", javaType = String.class),
            @Result(property = "position", column = "tposition", javaType = String.class),
            @Result(property = "maxim", column = "tmaxim", javaType = String.class),
            @Result(property = "intro", column = "tintro", javaType = String.class)
    })
    Trainer queryTrainerById(Long id);




    @Select("SELECT * FROM trainer WHERE tname like CONCAT(CONCAT('%',#{name}),'%')")
    @Results(id = "TrainerListByName",value = {
            @Result(property = "id", column = "tid", javaType = Long.class),
            @Result(property = "username", column = "tusername", javaType = String.class),
            @Result(property = "password", column = "tpassword", javaType = String.class),
            @Result(property = "name", column = "tname", javaType = String.class),
            @Result(property = "age", column = "tage", javaType = Integer.class),
            @Result(property = "sex", column = "tsex", javaType = String.class),
            @Result(property = "email", column = "temail", javaType = String.class),
            @Result(property = "position", column = "tposition", javaType = String.class),
            @Result(property = "maxim", column = "tmaxim", javaType = String.class),
            @Result(property = "intro", column = "tintro", javaType = String.class)
    })
    List<Trainer> queryTrainerListByName(String name);


    @Select("SELECT * FROM trainer")
    @Results(id = "TrainerListAll",value = {
            @Result(property = "id", column = "tid", javaType = Long.class),
            @Result(property = "username", column = "tusername", javaType = String.class),
            @Result(property = "password", column = "tpassword", javaType = String.class),
            @Result(property = "name", column = "tname", javaType = String.class),
            @Result(property = "age", column = "tage", javaType = Integer.class),
            @Result(property = "sex", column = "tsex", javaType = String.class),
            @Result(property = "email", column = "temail", javaType = String.class),
            @Result(property = "position", column = "tposition", javaType = String.class),
            @Result(property = "maxim", column = "tmaxim", javaType = String.class),
            @Result(property = "intro", column = "tintro", javaType = String.class)
    })
    List<Trainer> queryTrainerListAll();


    class TrainerSqlBuilder{

        public String buildAddTrainer(final Trainer trainer){
            String ignore = " IGNORE";
            StringBuffer sql = new StringBuffer(new SQL(){{
                INSERT_INTO("trainer");
                VALUES("tid, tusername, tpassword, tname, tage, tsex, temail, tposition","null, #{username}, #{password}, #{name}, #{age}, #{sex}, #{email}, #{position}");
                if (!StringUtils.isBlank(trainer.getMaxim())) {
                    INTO_COLUMNS("tmaxim");
                    INTO_VALUES("#{maxim}");
                }
                if (!StringUtils.isBlank(trainer.getIntro())){
                    INTO_COLUMNS("tintro");
                    INTO_VALUES("#{intro}");
                }


            }}.toString());
            sql.insert(6,ignore);
            return sql.toString();
        }
        public String buildUpdateTrainer(final Trainer trainer){
            return new SQL(){{

                UPDATE("trainer");
                if (StringUtils.isNotBlank(trainer.getUsername())) SET("tusername=#{username}");
                if (StringUtils.isNotBlank(trainer.getPassword())) SET("tpassword=#{password}");
                if (StringUtils.isNotBlank(trainer.getName())) SET("tname=#{name}");
                if (!(trainer.getAge()==null)) SET("tage=#{age}");
                if (StringUtils.isNotBlank(trainer.getSex())) SET("tsex=#{sex}");
                if (StringUtils.isNotBlank(trainer.getEmail())) SET("temail=#{email}");
                if (StringUtils.isNotBlank(trainer.getPosition())) SET("tposition=#{position}");
                if (StringUtils.isNotBlank(trainer.getMaxim())) SET("tmaxim=#{maxim}");
                if (StringUtils.isNotBlank(trainer.getIntro())) SET("tintro=#{intro}");
                WHERE("tid=#{id}");




            }}.toString();
        }

    }


}
