package com.engTecOutSouPlat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface UserMapper {

	//通过用户信息查询用户
	User get(@Param("username") String username, @Param("password") String password);

	int queryForInt(@Param("username") String username);

	List<User> queryList(@Param("username") String username, @Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);
}
