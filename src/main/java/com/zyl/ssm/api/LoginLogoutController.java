package com.zyl.ssm.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginLogoutController {
	
//	@RequestMapping("/{flag}/login.do")
//	public String login(@PathVariable("flag") int flag){
////		if(flag == 1){
////			return "www.baidu.com";
////		}
//		return "login";
//	}
	
	@RequestMapping("/login.do")
	public String login(){
		return "login";
	}
}
