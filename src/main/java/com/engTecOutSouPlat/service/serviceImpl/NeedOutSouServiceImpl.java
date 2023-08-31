package com.engTecOutSouPlat.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class NeedOutSouServiceImpl implements NeedOutSouService {

	@Autowired
	NeedOutSouMapper needOutSouDao;

	@Override
	public int add(NeedOutSou nos) {
		// TODO Auto-generated method stub
		return needOutSouDao.add(nos);
	}
}
