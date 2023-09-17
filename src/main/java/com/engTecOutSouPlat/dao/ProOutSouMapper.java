package com.engTecOutSouPlat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface ProOutSouMapper {

	int add(ProOutSou pos);
	
	int queryForInt(@Param("contactName") String contactName, @Param("phone") String phone, @Param("area") String area, @Param("companyName") String companyName, @Param("enginName") String enginName,
			@Param("tradeName") String tradeName, @Param("otherTrade") String otherTrade, @Param("specialityName") String specialityName, @Param("otherSpeciality") String otherSpeciality, @Param("createTimeStart") String createTimeStart,
			@Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd, @Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd,
			@Param("state") Integer state);
	
	List<ProOutSou> queryList(@Param("contactName") String contactName, @Param("phone") String phone, @Param("area") String area, @Param("companyName") String companyName, @Param("enginName") String enginName,
			@Param("tradeName") String tradeName, @Param("otherTrade") String otherTrade, @Param("specialityName") String specialityName, @Param("otherSpeciality") String otherSpeciality, @Param("createTimeStart") String createTimeStart,
			@Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd, @Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd,
			@Param("state") Integer state, @Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	List<ProOutSou> getListByOpenId(@Param("openId") String openId);

	ProOutSou selectById(@Param("id") String id);

	int edit(ProOutSou pos);
}
