package com.engTecOutSouPlat.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;
import com.engTecOutSouPlat.util.DateUtil;

@Service
public class NeedOutSouServiceImpl implements NeedOutSouService {

	@Autowired
	NeedOutSouMapper needOutSouDao;

	@Override
	public int add(NeedOutSou nos) {
		// TODO Auto-generated method stub
		return needOutSouDao.add(nos);
	}

	@Override
	public int queryForInt(String contactName, String phone, String area, String companyName, String enginName,
			Integer tradeId, String otherTrade, String speciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state) {
		// TODO Auto-generated method stub
		return needOutSouDao.queryForInt(contactName, phone, area, companyName, enginName, tradeId, otherTrade, speciality, createTimeStart,
				createTimeEnd, startDateStart, startDateEnd, endDateStart, endDateEnd, state);
	}

	@Override
	public List<NeedOutSou> queryList(String contactName, String phone, String area, String companyName,
			String enginName, Integer tradeId, String otherTrade, String speciality, 
			String createTimeStart, String createTimeEnd, String startDateStart, String startDateEnd,
			String endDateStart, String endDateEnd, Integer state, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return needOutSouDao.queryList(contactName, phone, area, companyName, enginName, tradeId, otherTrade, speciality, 
				createTimeStart, createTimeEnd, startDateStart, startDateEnd,
				endDateStart, endDateEnd, state, (page-1)*rows, rows, sort, order);
	}

	@Override
	public List<NeedOutSou> getListByOpenId(String openId) {
		// TODO Auto-generated method stub
		List<NeedOutSou> needOutSouList = needOutSouDao.getListByOpenId(openId);
		for (NeedOutSou needOutSou : needOutSouList) {
			String startDate = needOutSou.getStartDate();
			String endDate = needOutSou.getEndDate();
			int dayCount = DateUtil.getTimeBetween(startDate, endDate, DateUtil.DAYS);
			needOutSou.setDayCount(dayCount);
		}
		return needOutSouList;
	}
}
