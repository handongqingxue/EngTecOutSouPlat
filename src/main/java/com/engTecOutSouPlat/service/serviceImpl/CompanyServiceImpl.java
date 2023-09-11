package com.engTecOutSouPlat.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyMapper companyDao;

	@Override
	public int add(Company c) {
		// TODO Auto-generated method stub
		return companyDao.add(c);
	}

	@Override
	public int queryForInt(String companyName, String address, String industry, String createTimeStart,
			String createTimeEnd) {
		// TODO Auto-generated method stub
		return companyDao.queryForInt(companyName, address, industry, createTimeStart, createTimeEnd);
	}

	@Override
	public List<Company> queryList(String companyName, String address, String industry, String createTimeStart,
			String createTimeEnd, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return companyDao.queryList(companyName, address, industry, createTimeStart, createTimeEnd, 
				(page-1)*rows, rows, sort, order);
	}

	@Override
	public boolean checkIfExist(String openId) {
		// TODO Auto-generated method stub
		return companyDao.getCountByOpenId(openId)==0?false:true;
	}
}
