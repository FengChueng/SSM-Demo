package com.zyl.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.zyl.ssm.pojo.User;

@MapperScan
public interface UserMapper {
    User findUserById(@Param(value = "id") int id);
    
    int addUser(User user);
    
}
