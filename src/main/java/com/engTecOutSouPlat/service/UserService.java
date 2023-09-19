package com.engTecOutSouPlat.service;

import java.util.List;

import com.engTecOutSouPlat.entity.*;

public interface UserService {

	int queryForInt(String username);

	List<User> queryList(String username, int page, int rows, String sort, String order);

}
