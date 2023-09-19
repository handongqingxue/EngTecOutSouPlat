package com.engTecOutSouPlat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface CompanyMapper {

	int add(Company c);

	int editByOpenId(Company c);

	int queryForInt(@Param("companyName") String companyName, @Param("tradeId") Integer tradeId, @Param("contactName") String contactName, @Param("phone") String phone, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd);
	
	List<Company> queryList(@Param("companyName") String companyName, @Param("tradeId") Integer tradeId, @Param("contactName") String contactName, @Param("phone") String phone, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	int getCountByOpenId(@Param("openId") String openId);

	Company getByOpenId(@Param("openId") String openId);
}
