package com.zyl.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyl.ssm.dao.UserMapper;
import com.zyl.ssm.pojo.User;
import com.zyl.ssm.service.inter.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
		
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public User addUser(String userName, int userAge) {
		User user = new User();
		user.setUserName(userName);
		user.setUserAge(userAge);
		userMapper.addUser(user);
		return user;
	}

}
