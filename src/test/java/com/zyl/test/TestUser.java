package com.zyl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyl.ssm.dao.UserMapper;
import com.zyl.ssm.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TestUser {
    
    @Autowired
    private UserMapper userMapper;
    
    
    @Test
    public void testFindUserById(){
        User user = userMapper.findUserById(1);
        System.out.println(user.toString());
    }
    
    @Test
    public void testAddUser(){
    	User user = new User();
    	user.setUserName("zyl");
    	user.setUserAge(22);
    	
        int userid = userMapper.addUser(user);
        System.out.println(userid+"-" + user.getId());
    }
    
}
