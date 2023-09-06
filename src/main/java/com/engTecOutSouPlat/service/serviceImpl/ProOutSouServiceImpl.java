package com.engTecOutSouPlat.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class ProOutSouServiceImpl implements ProOutSouService {

	@Autowired
	ProOutSouMapper proOutSouDao;
	
	@Override
	public int add(ProOutSou pos) {
		// TODO Auto-generated method stub
		return proOutSouDao.add(pos);
	}

	@Override
	public int queryForInt(String contactName, String phone, String area, String companyName, String enginName,
			String tradeName, String otherTrade, String specialityName, String otherSpeciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state) {
		// TODO Auto-generated method stub
		return proOutSouDao.queryForInt(contactName, phone, area, companyName, enginName, tradeName, otherTrade, specialityName, otherSpeciality, createTimeStart,
				createTimeEnd, startDateStart, startDateEnd, endDateStart, endDateEnd, state);
	}

	@Override
	public List<ProOutSou> queryList(String contactName, String phone, String area, String companyName,
			String enginName, String tradeName, String otherTrade, String specialityName, String otherSpeciality,
			String createTimeStart, String createTimeEnd, String startDateStart, String startDateEnd,
			String endDateStart, String endDateEnd, Integer state, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return proOutSouDao.queryList(contactName, phone, area, companyName, enginName, tradeName, otherTrade, specialityName, otherSpeciality,
				createTimeStart, createTimeEnd, startDateStart, startDateEnd,
				endDateStart, endDateEnd, state, (page-1)*rows, rows, sort, order);
	}

}
