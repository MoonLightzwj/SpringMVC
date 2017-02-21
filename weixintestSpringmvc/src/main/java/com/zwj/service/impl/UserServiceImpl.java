package com.zwj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zwj.bean.User;
import com.zwj.dao.IUserDao;
import com.zwj.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource
	private IUserDao userDao=null;
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(userId);
	}

}
