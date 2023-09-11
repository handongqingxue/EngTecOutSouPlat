package com.engTecOutSouPlat.dao;

import org.apache.ibatis.annotations.Param;

import com.engTecOutSouPlat.entity.*;

public interface WXUserMapper {
	
	Integer getCountByOpenId(@Param("openId") String openId);

	int add(WXUser wxu);
	
	int edit(WXUser wxu);
}
