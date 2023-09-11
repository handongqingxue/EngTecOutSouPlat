package com.engTecOutSouPlat.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class WXUserServiceImpl implements WXUserService {

	@Autowired
	WXUserMapper wxUserDao;
	
	@Override
	public int addOrEdit(WXUser wxu) {
		// TODO Auto-generated method stub
		int count=0;
		if(wxUserDao.getCountByOpenId(wxu.getOpenId())==0)
			count=wxUserDao.add(wxu);
		else
			count=wxUserDao.edit(wxu);
		return count;
	}

}
