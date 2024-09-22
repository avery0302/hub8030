package com.example.hub3010.mapper;

import com.example.hub3010.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT name FROM yu LIMIT 1")
    String getFirstUser();
}