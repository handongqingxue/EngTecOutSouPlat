package com.engTecOutSouPlat.service.serviceImpl;

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
}
