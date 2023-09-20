package com.engTecOutSouPlat.service;

import java.util.List;

import com.engTecOutSouPlat.entity.*;

public interface UserService {

	int add(User user);

	int edit(User user);

	int queryForInt(String username);

	List<User> queryList(String username, int page, int rows, String sort, String order);

	boolean checkUsernameIfExist(String username, String usernameOld);

}
