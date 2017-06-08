package com.uestc.naldo.dao;

import com.uestc.naldo.domain.Photo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoMapper {

    @Insert("INSERT INTO photo(phid, phname, phpath) VALUES(null, #{name}, #{path})")
    int add(Photo photo);

    @Delete("DELETE FROM photo WHERE phid=#{id}")
    int deleteById(Long id);

    @Select("SELECT * FROM photo WHERE phid=#{id}")
    @Results(id = "photoById", value = {
            @Result(property = "id", column = "phid", javaType = Long.class),
            @Result(property = "name", column = "phname", javaType = String.class),
            @Result(property = "path", column = "phpath", javaType = String.class)
    })
    Photo queryPhotoById(Long id);

    @Select("SELECT * FROM photo")
    @Results(id = "photoListAll", value = {
            @Result(property = "id", column = "phid", javaType = Long.class),
            @Result(property = "name", column = "phname", javaType = String.class),
            @Result(property = "path", column = "phpath", javaType = String.class)
    })
    List<Photo> queryPhotoListAll();

}
