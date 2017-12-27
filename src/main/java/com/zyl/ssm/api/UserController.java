package com.zyl.ssm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zyl.ssm.pojo.User;
import com.zyl.ssm.service.inter.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "添加用户", notes = "", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="userName",value="用户名",dataType="string",paramType="path"),
    	@ApiImplicitParam(name="userAge",value="年龄",dataType="integer",paramType="path")
    })
    @RequestMapping(value = "{userName}/{userAge}/addUser", method = RequestMethod.GET)
    public User addUser(@PathVariable(value="userName") String userName,@PathVariable(value="userAge")int userAge){
    	User user = userService.addUser(userName, userAge);
        System.out.println("添加用户成功:"+user.toString());
        return user;
    }
    
}
