package com.engTecOutSouPlat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface NeedOutSouMapper {

	int add(NeedOutSou nos);

	int queryForInt(@Param("contactName") String contactName, @Param("phone") String phone, @Param("area") String area, @Param("companyName") String companyName, @Param("enginName") String enginName,
			@Param("tradeId") Integer tradeId, @Param("otherTrade") String otherTrade, @Param("speciality") String speciality, @Param("createTimeStart") String createTimeStart,
			@Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd, @Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd,
			@Param("state") Integer state);

	List<NeedOutSou> queryList(@Param("contactName") String contactName, @Param("phone") String phone, @Param("area") String area, @Param("companyName") String companyName, @Param("enginName") String enginName,
			@Param("tradeId") Integer tradeId, @Param("otherTrade") String otherTrade, @Param("speciality") String speciality, @Param("createTimeStart") String createTimeStart,
			@Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd, @Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd,
			@Param("state") Integer state, @Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	int getCountByOpenId(@Param("enginName") String enginName, @Param("tradeId") Integer tradeId, @Param("otherTrade") String otherTrade, @Param("speciality") String speciality,
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd,
			@Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd, @Param("openId") String openId);

	List<NeedOutSou> getListByOpenId(@Param("enginName") String enginName, @Param("tradeId") Integer tradeId, @Param("otherTrade") String otherTrade, @Param("speciality") String speciality,
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, @Param("startDateStart") String startDateStart, @Param("startDateEnd") String startDateEnd,
			@Param("endDateStart") String endDateStart, @Param("endDateEnd") String endDateEnd, @Param("openId") String openId, @Param("rowNum") int rowNum, @Param("rows") int rows);
}
