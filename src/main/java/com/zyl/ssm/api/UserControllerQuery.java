package com.zyl.ssm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyl.ssm.pojo.User;
import com.zyl.ssm.pojo.UserParam;
import com.zyl.ssm.service.inter.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserControllerQuery {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserParam userParam;
    
//    @ApiOperation(value = "添加用户", notes = "")
//    @ApiImplicitParams({
//    	@ApiImplicitParam(name="userName",value="用户名",dataType="string",paramType="path"),
//    	@ApiImplicitParam(name="userAge",value="年龄",dataType="integer",paramType="path")
//    })
//    @RequestMapping(value = "{userName}/{userAge}/addUser.json")
//    public User addUserPath(@PathVariable(value="userName") String userName,@PathVariable(value="userAge")int userAge){
////    	User user = userService.addUser(userName, userAge);
//        User user = new User(1,"123",123);
//        System.out.println("添加用户成功:"+user.toString());
//        return user;
//    }
    
    @ApiOperation(value = "添加用户 Post", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户名",dataType="string",paramType="header"),
        @ApiImplicitParam(name="userName",value="用户名",dataType="string",paramType="query"),
        @ApiImplicitParam(name="userAge",value="年龄",dataType="integer",paramType="query")
    })
    @RequestMapping(value = "/addUserQuery.json")
    public User addUserQuery(@RequestParam(value="userName") String userName,@RequestParam(value="userAge")int userAge){
//        User user = userService.addUser(userName, userAge);
//        System.out.println("添加用户成功:"+user.toString());
        User user = new User(1,"123",123);
        System.out.println(userParam.toString()+"添加用户成功:"+user.toString());
        return user;
    }
    
    
    @ApiOperation(value = "添加用户 Post", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户名",dataType="string",paramType="header"),
        @ApiImplicitParam(name="userName",value="用户名",dataType="string",paramType="query"),
        @ApiImplicitParam(name="userAge",value="年龄",dataType="integer",paramType="query")
    })
    @RequestMapping(value = "/addUserQuery2.json")
    public User addUserQuery2(@RequestParam(value="userName") String userName,@RequestParam(value="userAge")int userAge) throws Exception{
//        User user = userService.addUser(userName, userAge);
//        System.out.println("添加用户成功:"+user.toString());
        User user = new User(1,"123",123);
        System.out.println(userService.toString());
        System.out.println(userParam.toString()+"添加用户成功:"+user.toString());
        throw new Exception("自定义异常");
    }
    
    
//    @ApiOperation(value = "添加用户 Post", notes = "")
//    @ApiImplicitParam(name="user",value="用户名",dataType="user",paramType="body")
//    @RequestMapping(value = "/addUserBody.json")
//    public User addUserBody(@RequestBody User user){
////        User newUser = userService.addUser(user.getUserName(), user.getUserAge());
////        System.out.println("添加用户成功:"+newUser.toString());
//        user = new User(1,"123",123);
//        System.out.println("添加用户成功:"+user.toString());
//        return user;
//    }
    
    
    
}
