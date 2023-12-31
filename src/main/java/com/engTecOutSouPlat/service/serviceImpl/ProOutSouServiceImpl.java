package com.engTecOutSouPlat.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;
import com.engTecOutSouPlat.util.DateUtil;

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
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=proOutSouDao.deleteByIds(idList);
		return count;
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

	@Override
	public List<ProOutSou> getListByOpenId(String openId) {
		// TODO Auto-generated method stub
		List<ProOutSou> proOutSouList = proOutSouDao.getListByOpenId(openId);
		for (ProOutSou proOutSou : proOutSouList) {
			String startDate = proOutSou.getStartDate();
			String endDate = proOutSou.getEndDate();
			int dayCount = DateUtil.getTimeBetween(startDate, endDate, DateUtil.DAYS);
			proOutSou.setDayCount(dayCount);
		}
		return proOutSouList;
	}

	@Override
	public ProOutSou selectById(String id) {
		// TODO Auto-generated method stub
		return proOutSouDao.selectById(id);
	}

	@Override
	public int edit(ProOutSou pos) {
		// TODO Auto-generated method stub
		return proOutSouDao.edit(pos);
	}

}
