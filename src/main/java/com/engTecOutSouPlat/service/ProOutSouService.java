package com.engTecOutSouPlat.service;

import java.util.List;

import com.engTecOutSouPlat.entity.*;

public interface ProOutSouService {

	int add(ProOutSou pos);

	int queryForInt(String contactName, String phone, String area, String companyName, String enginName,
			String tradeName, String otherTrade, String specialityName, String otherSpeciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state);

	List<ProOutSou> queryList(String contactName, String phone, String area, String companyName, String enginName,
			String tradeName, String otherTrade, String specialityName, String otherSpeciality, String createTimeStart,
			String createTimeEnd, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd,
			Integer state, int page, int rows, String sort, String order);

	List<ProOutSou> getListByOpenId(String openId);
}
