package com.zyl.ssm.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyl.ssm.pojo.User;
import com.zyl.ssm.pojo.UserParam;
import com.zyl.ssm.service.inter.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("user")
public class UserControllerBody {
	private Logger LOGGER = Logger.getLogger(getClass());
    @Autowired
    private UserService userService;
    
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
//    
//    @ApiOperation(value = "添加用户 Post", notes = "")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name="token",value="用户名",dataType="string",paramType="header"),
//        @ApiImplicitParam(name="userName",value="用户名",dataType="string",paramType="query"),
//        @ApiImplicitParam(name="userAge",value="年龄",dataType="integer",paramType="query")
//    })
//    @RequestMapping(value = "/addUserQuery.json")
//    public User addUserQuery(@RequestParam(value="userName") String userName,@RequestParam(value="userAge")int userAge){
////        User user = userService.addUser(userName, userAge);
////        System.out.println("添加用户成功:"+user.toString());
//        User user = new User(1,"123",123);
//        System.out.println("添加用户成功:"+user.toString());
//        return user;
//    }
    
    @ApiOperation(value = "添加用户 Post", notes = "")
//    @ApiImplicitParam(name="user",value="用户名",dataType="User",paramType="body",examples=@Example(value = { @ExampleProperty(value = "User") }))
    @RequestMapping(value = "/addUserBody.json",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUserBody(@ApiParam(value = "Updated user object", required = true) @RequestBody UserParam userParam){
//        User newUser = userService.addUser(user.getUserName(), user.getUserAge());
//        System.out.println("添加用户成功:"+newUser.toString());
        User user = new User(1,userParam.getUserName(),userParam.getUserAge());
        LOGGER.info("addUserBody 添加用户成功:"+user.toString());
        return user;
    }
    
    
    
    
    
}
