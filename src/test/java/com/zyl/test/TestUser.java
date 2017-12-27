package com.zyl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyl.ssm.dao.UserMapper;
import com.zyl.ssm.pojo.User;
import com.zyl.ssm.service.inter.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TestUser {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserService userService;
    
    
    @Test
    public void testFindUserById(){
        User user = userMapper.findUserById(1);
        System.out.println(user.toString());
    }
    
    @Test
    public void testAddUser(){
        User user = userService.addUser("zyl",22);
        System.out.println(user.toString());
    }
    
}
