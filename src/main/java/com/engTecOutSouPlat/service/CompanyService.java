package com.engTecOutSouPlat.service;

import java.util.List;

import com.engTecOutSouPlat.entity.*;

public interface CompanyService {

	int add(Company c);

	int queryForInt(String companyName, String address, String industry, String createTimeStart, String createTimeEnd);

	List<Company> queryList(String companyName, String address, String industry, String createTimeStart, String createTimeEnd, 
			int page, int rows, String sort, String order);

	boolean checkIfExist(String openId);
}
