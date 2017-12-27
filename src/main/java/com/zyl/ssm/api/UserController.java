package com.zyl.ssm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zyl.ssm.pojo.User;
import com.zyl.ssm.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    
    public User addUser(@PathVariable(value="username") String username,@PathVariable(value="userAge")int age){
        
        
        return null;
    }
    
}
