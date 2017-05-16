package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Pet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface PetMapper {

    @InsertProvider(type = PetSqlBuilder.class, method = "buildAddPet")
    int add(Pet pet);

    @Delete("DELETE FROM pet WHERE pid=#{id}")
    int deleteById(Long id);

    @UpdateProvider(type = PetSqlBuilder.class, method = "buildUpdatePet")
    int update(Pet pet);

    @Select("SELECT * FROM pet WHERE oid=#{id}")
    @Results(id = "PetById",value = {
            @Result(property = "id", column = "pid", javaType = Long.class),
            @Result(property = "name", column = "pname", javaType = String.class),
            @Result(property = "age", column = "page", javaType = Integer.class),
            @Result(property = "sex", column = "psex", javaType = String.class),
            @Result(property = "species", column = "pspecies", javaType = String.class),
            @Result(property = "hrStatus", column = "phr_status", javaType = String.class),
            @Result(property = "start", column = "start", javaType = Date.class),
            @Result(property = "end", column = "end", javaType = Date.class),
            @Result(property = "oid", column = "ownerId", javaType = Long.class),
            @Result(property = "cid", column = "courseId", javaType = Long.class)
    })
    Pet queryPetById(Long id);


    @Select("SELECT * FROM pet")
    @Results(id = "PetListAll",value = {
            @Result(property = "id", column = "pid", javaType = Long.class),
            @Result(property = "name", column = "pname", javaType = String.class),
            @Result(property = "age", column = "page", javaType = Integer.class),
            @Result(property = "sex", column = "psex", javaType = String.class),
            @Result(property = "species", column = "pspecies", javaType = String.class),
            @Result(property = "hrStatus", column = "phr_status", javaType = String.class),
            @Result(property = "start", column = "start", javaType = Date.class),
            @Result(property = "end", column = "end", javaType = Date.class),
            @Result(property = "oid", column = "ownerId", javaType = Long.class),
            @Result(property = "cid", column = "courseId", javaType = Long.class)
    })
    List<Pet> queryPetListAll();

    @Select("SELECT * FROM pet WHERE pname like '%#{name}%'")
    @Results(id = "PetListByName",value = {
            @Result(property = "id", column = "pid", javaType = Long.class),
            @Result(property = "name", column = "pname", javaType = String.class),
            @Result(property = "age", column = "page", javaType = Integer.class),
            @Result(property = "sex", column = "psex", javaType = String.class),
            @Result(property = "species", column = "pspecies", javaType = String.class),
            @Result(property = "hrStatus", column = "phr_status", javaType = String.class),
            @Result(property = "start", column = "start", javaType = Date.class),
            @Result(property = "end", column = "end", javaType = Date.class),
            @Result(property = "oid", column = "ownerId", javaType = Long.class),
            @Result(property = "cid", column = "courseId", javaType = Long.class)
    })
    List<Pet> queryPetListByName(String name);


    class PetSqlBuilder{
        public String buildAddPet(final Pet pet){
            return new SQL(){{
                INSERT_INTO("pet");
                VALUES("pid, pname, page, psex, pspecies", "null, #{name}, #{age}, #{sex}, #{species}");
                if (!(pet.getOid()==null)){
                    INTO_COLUMNS("ownerId");
                    INTO_VALUES("#{oid}");
                }
                if (!(pet.getCid()==null)){
                    INTO_COLUMNS("courseId");
                    INTO_VALUES("cid");
                }
                if (!(pet.getStart()==null)){
                    INTO_COLUMNS("start");
                    INTO_VALUES("#{start}");
                }
                if (!(pet.getEnd()==null)){
                    INTO_COLUMNS("end");
                    INTO_VALUES("#{end}");
                }

            }}.toString();
        }

        public String buildUpdatePet(final Pet pet){
            return new SQL(){{
                UPDATE("pet");
                if (!(pet.getName()==null)) SET("pname=#{name}");
                if (!(pet.getAge()==null)) SET("page=#{age}");
                if (!(pet.getSex()==null)) SET("psex=#{sex}");
                if (!(pet.getSpecies()==null)) SET("pspecies=#{species}");
                if (!(pet.getHrStatus()==null)) SET("phr_status=#{hrStatus}");
                if (!(pet.getStart()==null)) SET("start=#{start}");
                if (!(pet.getEnd()==null)) SET("end=#{end}");
                if (!(pet.getOid()==null)) SET("ownerId=#{oid}");
                if (!(pet.getCid()==null)) SET("courseId=#{cid}");
                WHERE("pid=#{id}");

            }}.toString();
        }
    }

}
