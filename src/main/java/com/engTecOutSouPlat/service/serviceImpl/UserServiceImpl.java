package com.engTecOutSouPlat.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userDao;

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}
	
	@Override
	public int queryForInt(String username) {
		// TODO Auto-generated method stub
		return userDao.queryForInt(username);
	}

	@Override
	public List<User> queryList(String username, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return userDao.queryList(username, (page-1)*rows, rows, sort, order);
	}

}
