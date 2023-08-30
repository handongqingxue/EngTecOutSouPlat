package com.engTecOutSouPlat.dao;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface UserMapper {

	//通过用户信息查询用户
	User get(@Param("username") String username, @Param("password") String password);
}
