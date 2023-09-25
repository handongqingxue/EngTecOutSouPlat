package com.engTecOutSouPlat.service;

import java.util.List;

import com.engTecOutSouPlat.entity.*;

public interface NeedOutSouService {

	int add(NeedOutSou nos);

	int deleteByIds(String ids);

	int edit(NeedOutSou nos);

	int queryForInt(String contactName, String phone, String area, String companyName, String enginName,
			Integer tradeId, String otherTrade, String speciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state);

	List<NeedOutSou> queryList(String contactName, String phone, String area, String companyName, String enginName,
			Integer tradeId, String otherTrade, String speciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state, int page, int rows, String sort, String order);

	List<NeedOutSou> getListByOpenId(String openId);

	NeedOutSou selectById(String id);
}
