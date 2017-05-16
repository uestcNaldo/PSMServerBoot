package com.uestc.naldo.dao;

import com.uestc.naldo.domain.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component

public interface SchoolMapper {

    @Update("UPDATE school SET sname=#{name}, scontact=#{contact}, sintro=#{intro}, sphone=#{phone}, saddress=#{address} WHERE sid='1'")
    int update(School school);


    @Select("SELECT * FROM school WHERE sid='1'")
    School querySchool();


}
