package com.zyl.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.zyl.ssm.pojo.User;

public interface UserMapper {
    User findUserById(@Param(value = "id") int id);
}
